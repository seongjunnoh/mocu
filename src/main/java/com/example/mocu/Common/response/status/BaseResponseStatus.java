package com.example.mocu.Common.response.status;

import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum BaseResponseStatus implements ResponseStatus {

    /**
     * 1000: 요청 성공 (OK)
     */
    SUCCESS(1000, HttpStatus.OK.value(), "요청에 성공하였습니다."),

    /**
     * 2000: Request 오류 (BAD_REQUEST)
     */
    BAD_REQUEST(2000, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 요청입니다."),
    URL_NOT_FOUND(2001, HttpStatus.BAD_REQUEST.value(), "유효하지 않은 URL 입니다."),
    METHOD_NOT_ALLOWED(2002, HttpStatus.METHOD_NOT_ALLOWED.value(), "해당 URL에서는 지원하지 않는 HTTP Method 입니다."),

    /**
     * 3000: Server, Database 오류 (INTERNAL_SERVER_ERROR)
     */
    SERVER_ERROR(3000, HttpStatus.INTERNAL_SERVER_ERROR.value(), "서버에서 오류가 발생하였습니다."),
    DATABASE_ERROR(3001, HttpStatus.INTERNAL_SERVER_ERROR.value(), "데이터베이스에서 오류가 발생하였습니다."),
    BAD_SQL_GRAMMAR(3002, HttpStatus.INTERNAL_SERVER_ERROR.value(), "SQL에 오류가 있습니다."),

    /**
     * 4000: Authorization 오류
     */
    JWT_ERROR(4000, HttpStatus.UNAUTHORIZED.value(), "JWT에서 오류가 발생하였습니다."),
    TOKEN_NOT_FOUND(4001, HttpStatus.BAD_REQUEST.value(), "토큰이 HTTP Header에 없습니다."),
    UNSUPPORTED_TOKEN_TYPE(4002, HttpStatus.BAD_REQUEST.value(), "지원되지 않는 토큰 형식입니다."),
    INVALID_TOKEN(4003, HttpStatus.UNAUTHORIZED.value(), "유효하지 않은 토큰입니다."),
    MALFORMED_TOKEN(4004, HttpStatus.UNAUTHORIZED.value(), "토큰이 올바르게 구성되지 않았습니다."),
    EXPIRED_TOKEN(4005, HttpStatus.UNAUTHORIZED.value(), "만료된 토큰입니다."),
    TOKEN_MISMATCH(4006, HttpStatus.UNAUTHORIZED.value(), "로그인 정보가 토큰 정보와 일치하지 않습니다."),

    /**
     * 5000: User 오류
     */
    INVALID_USER_VALUE(5000, HttpStatus.BAD_REQUEST.value(), "회원가입 요청에서 잘못된 값이 존재합니다."),
    DUPLICATE_EMAIL(5001, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 이메일입니다."),
    DUPLICATE_NICKNAME(5002, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 닉네임입니다."),
    USER_NOT_FOUND(4003, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 회원입니다."),
    PASSWORD_NO_MATCH(4004, HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다."),
    INVALID_USER_STATUS(4005, HttpStatus.BAD_REQUEST.value(), "잘못된 회원 status 값입니다."),
    EMAIL_NOT_FOUND(4006, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 이메일입니다."),

    /**
     * 6000 : Review 오류
     */
    INVALID_(6000, HttpStatus.BAD_REQUEST.value(), "가게 등록 요청에서 잘못된 값이 존재합니다."),

    IS_NOT_STAMPED(6001, HttpStatus.BAD_REQUEST.value(), "스탬프 적립하지 않은 가게에는 리뷰를 등록할 수 없습니다."),
    INVALID_REVIEW_LENGTH(6002, HttpStatus.BAD_REQUEST.value(), "리뷰 글자수가 부족합니다."),
    IS_NOT_VALIDATED_1(6003, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 가게 이름입니다."),
    IS_NOT_VALIDATED_2(6004, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 가게 이름입니다."),
    IS_NOT_VALIDATED_3(6005, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 가게 이름입니다."),

    INVALID_STORE_STATUS(6006, HttpStatus.BAD_REQUEST.value(), "잘못된 가게 status 값입니다."),

    /**
     * 7000: Store 오류
     */
    INVALID_STORE_REVIEW_REQUEST_VALUE(7000, HttpStatus.BAD_REQUEST.value(), "가게의 리뷰 조회 요청에서 잘못된 값이 존재합니다."),
    DUPLICATE_EMAIL1(7001, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 이메일입니다."),
    DUPLICATE_NICKNAME1(7002, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 닉네임입니다."),
    STORE_NOT_FOUND(7003, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 가게입니다."),
    STORE_NOT_FOUND1(7004, HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다."),
    INVALID_USER_STATUS1(7005, HttpStatus.BAD_REQUEST.value(), "잘못된 회원 status 값입니다."),
    EMAIL_NOT_FOUND1(7006, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 이메일입니다."),

    /**
     * 8000: Mission 오류
     */
    IS_NOT_DONE(8000, HttpStatus.BAD_REQUEST.value(), "오늘의 미션이 완료되지 않은 상태입니다."),
    sfeDUPLICATE_EMAIL1(8001, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 이메일입니다."),
    afeDUPLICATE_NICKNAME1(8002, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 닉네임입니다."),
    njSTORE_NOT_FOUND(8003, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 가게입니다."),
    ymgSTORE_NOT_FOUND1(8004, HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다."),
    tfhINVALID_USER_STATUS1(8005, HttpStatus.BAD_REQUEST.value(), "잘못된 회원 status 값입니다."),
    hsEMAIL_NOT_FOUND1(8006, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 이메일입니다."),

    /**
     * 9000: Owner 오류
     */
    INVALID_OWNER_USER_REQUEST_VALUE(9000, HttpStatus.BAD_REQUEST.value(), "가게의 UsersRequest 목록 요청에서 잘못된 값이 존재합니다."),
    dfsDUPLICATE_EMAIL1(9001, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 이메일입니다."),
    dfsDUPLICATE_NICKNAME1(9002, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 닉네임입니다."),
    dfsSTORE_NOT_FOUND(9003, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 가게입니다."),
    sdfSTORE_NOT_FOUND1(9004, HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다."),
    dfINVALID_USER_STATUS1(9005, HttpStatus.BAD_REQUEST.value(), "잘못된 회원 status 값입니다."),
    aeEMAIL_NOT_FOUND1(9006, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 이메일입니다."),

    /**
     * 10000: Coupon 오류
     */
    IS_NOT_ENOUGH_NUMBER_OF_COUPON(10000, HttpStatus.BAD_REQUEST.value(), "사용가능한 쿠폰이 존재하지 않습니다."),
    fsDUPLICATE_EMAIL1(10001, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 이메일입니다."),
    fsDUPLICATE_NICKNAME1(10002, HttpStatus.BAD_REQUEST.value(), "이미 존재하는 닉네임입니다."),
    fsSTORE_NOT_FOUND(10003, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 가게입니다."),
    dfSTORE_NOT_FOUND1(10004, HttpStatus.BAD_REQUEST.value(), "비밀번호가 일치하지 않습니다."),
    fINVALID_USER_STATUS1(10005, HttpStatus.BAD_REQUEST.value(), "잘못된 회원 status 값입니다."),
    eEMAIL_NOT_FOUND1(10006, HttpStatus.BAD_REQUEST.value(), "존재하지 않는 이메일입니다.");




    private final int code;
    private final int status;
    private final String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
