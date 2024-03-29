package com.example.mocu.Service;

import com.example.mocu.Dao.CouponDao;
import com.example.mocu.Dao.MissionDao;
import com.example.mocu.Dao.StampDao;
import com.example.mocu.Dao.UserDao;
import com.example.mocu.Dto.coupon.*;
import com.example.mocu.Dto.mission.IsTodayMission;
import com.example.mocu.Dto.stamp.StampInfoAfterCouponUse;
import com.example.mocu.Exception.CouponException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.example.mocu.Common.response.status.BaseResponseStatus.IS_NOT_ENOUGH_NUMBER_OF_COUPON;

@Slf4j
@RestController
@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponDao couponDao;
    private final StampDao stampDao;
    private final MissionDao missionDao;
    private final UserDao userDao;

    public PostCouponResponse couponRequestRegister(PostCouponRequest postCouponRequest) {
        log.info("[CouponService.couponRequestRegister]");

        return couponDao.couponRequestRegister(postCouponRequest);
    }


    public PostCouponAcceptResponse couponRequestAccept(PostCouponAcceptRequest postCouponAcceptRequest) {
        log.info("[CouponService.couponRequestAccept]");

        // TODO 1. numOfCouponAvailable 의 개수가 1 이상인지 체크
        int numOfCouponAvailable = couponDao.getNumOfCouponAvailable(postCouponAcceptRequest.getUserId(), postCouponAcceptRequest.getStoreId());
        if(numOfCouponAvailable < 1){
            throw new CouponException(IS_NOT_ENOUGH_NUMBER_OF_COUPON);
        }

        // TODO 2. CouponsRequest table의 tuple에서 status 값을 'accept'로 변경
        couponDao.updateCouponsRequestStatusToAccept(postCouponAcceptRequest.getCouponRequestId());

        // TODO 3. 해당 가게의 maxStamp 값 찾기
        int maxStamp = stampDao.getMaxStampValue(postCouponAcceptRequest.getStoreId());

        // TODO 4. Stamps table의 tuple에서 numOfStamp, numOfCouponAvailable, useCount 값 update
        // numOfStamp -= maxStamp
        // numOfCouponAvailable -= 1
        // useCount += 1
        StampInfoAfterCouponUse stampInfoAfterCouponUse = couponDao.updateStampsTable(postCouponAcceptRequest, maxStamp);

        // TODO 5. 쿠폰 사용에 대한 보상 찾기
        String reward = couponDao.getStoreReward(postCouponAcceptRequest.getStoreId());

        // TODO 6. 쿠폰 사용 후 쿠폰 사용 임박 여부 체크
        boolean isCouponImminent = checkCouponImminent(stampInfoAfterCouponUse, postCouponAcceptRequest.getStoreId());

        // TODO 7. '단골 등록' 팝업창 띄울지 말지 체크
        boolean regularPopUp;
        // 1. regularId 존재하는지 체크
        if(userDao.isExistRegularId(postCouponAcceptRequest.getUserId(), postCouponAcceptRequest.getStoreId())){
            // 존재하면 status 체크
            String status = userDao.getRegularStatus(postCouponAcceptRequest.getUserId(), postCouponAcceptRequest.getStoreId());
            switch (status){
                case "request" :
                    regularPopUp = true;
                    break;
                case "not-accept" :
                case "accept" :
                    regularPopUp = false;
                    break;
                // Handle unexpected status value (optional)
                default:
                    regularPopUp = false;
                    break;
            }
        }
        else{
            // 존재하지 않으면 regularId 생성
            // status -> 'request'
            userDao.createRegularId(postCouponAcceptRequest.getUserId(), postCouponAcceptRequest.getStoreId());
            regularPopUp = true;
        }

        // TODO 8. '쿠폰 사용하기' 가 오늘의 미션에 해당하는지 체크
        // 오늘의 미션 중 '쿠폰 사용하기' 가 있는지 체크
        List<IsTodayMission> todayMissionList = new ArrayList<>();

        if(missionDao.isTodayMissionAssigned(postCouponAcceptRequest.getUserId(), "쿠폰 사용하기")){
            IsTodayMission todayMission = new IsTodayMission("쿠폰 사용하기", true);
            todayMissionList.add(todayMission);

            // 1. get '쿠폰 사용하기' 의 todayMissionId
            long todayMissionId = missionDao.getTodayMissionId(postCouponAcceptRequest.getUserId(), "쿠폰 사용하기");
            // 2. 해당 todayMissionId 를 '미션 완료' 처리
            missionDao.updateTodayMissionToDone(todayMissionId);
        }

        // TODO 9. RETURN 형식 맞추기
        return buildPostCouponAcceptResponse(postCouponAcceptRequest, stampInfoAfterCouponUse, maxStamp, isCouponImminent, reward, todayMissionList, regularPopUp);
    }

    private PostCouponAcceptResponse buildPostCouponAcceptResponse(PostCouponAcceptRequest postCouponAcceptRequest, StampInfoAfterCouponUse stampInfoAfterCouponUse, int maxStamp, boolean isCouponImminent, String reward, List<IsTodayMission> todayMissionList, boolean regularPopUp) {
        String storeName = stampDao.getStoreName(postCouponAcceptRequest.getStoreId());

        return new PostCouponAcceptResponse(
                stampInfoAfterCouponUse.getStampId(),
                stampInfoAfterCouponUse.getNumOfStamp(),
                maxStamp,
                storeName,
                reward,
                isCouponImminent,
                stampInfoAfterCouponUse.getNumOfCouponAvailable(),
                todayMissionList,
                regularPopUp
        );
    }

    private boolean checkCouponImminent(StampInfoAfterCouponUse stampInfoAfterCouponUse, long storeId) {
        // maxStamp value 조회
        int maxStampValue = stampDao.getMaxStampValue(storeId);
        // 적립 임박 요건을 충족하는지 체크
        boolean isDueDateTrue = stampInfoAfterCouponUse.getNumOfStamp() >= 0.8 * maxStampValue;
        // 적립 임박 상태 update
        stampDao.updateDueDate(stampInfoAfterCouponUse.getStampId(), isDueDateTrue);

        return isDueDateTrue;
    }


    public List<GetMyCouponList> myCouponList(long userId, String category, String sort, boolean isEventTrue, boolean isCouponUsable, boolean isStoreRegular, boolean isCouponCloseToCompletion) {
        log.info("[CouponService.myCouponList]");

        return couponDao.myCouponList(userId, category, sort, isEventTrue, isCouponUsable, isStoreRegular, isCouponCloseToCompletion);
    }
}
