package com.example.mocu.Controller;

import com.example.mocu.Common.response.BaseResponse;
import com.example.mocu.Dto.review.GetAvailableReviewCountResponse;
import com.example.mocu.Dto.review.PatchReviewReportToTrueRequest;
import com.example.mocu.Dto.review.PostReviewResponse;
import com.example.mocu.Dto.review.PostReviewRequest;
import com.example.mocu.Service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    /**
     * 리뷰 등록
     */
    @PostMapping("")
    public BaseResponse<PostReviewResponse> register(@Validated @RequestBody PostReviewRequest postReviewReqeust) {
        log.info("[ReviewController.signUp]");

        return new BaseResponse<>(reviewService.register(postReviewReqeust));
    }

    /**
     * 작성 가능 리뷰
     */
    @GetMapping("/available-count/{userId}")
    public BaseResponse<GetAvailableReviewCountResponse> getAvailableReviewCount(@PathVariable Long userId) {
        log.info("[ReviewController.getAvailabilityReviewCount");

        return new BaseResponse<>(reviewService.getAvailableReviewCount(userId));
    }

    /**
     * 리뷰 신고 처리
     */
    @PatchMapping("/update/report-true")
    public BaseResponse<String> updateReviewReportToTrue(@Validated @RequestBody PatchReviewReportToTrueRequest patchReviewReportToTrueRequest){
        log.info("[ReviewController.updateReviewReportToTrue]");

        try {
            reviewService.updateReviewReportToTrue(patchReviewReportToTrueRequest);
            return new BaseResponse<>("신고처리 되었습니다.");
        } catch (RuntimeException e){
            return new BaseResponse<>("신고처리 과정 중 오류 발생");
        }
    }


}