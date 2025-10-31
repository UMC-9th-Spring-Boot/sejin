package com.example.UMC.domain.test.controller;

import com.example.UMC.domain.user.exception.code.UserErrorCode;
import com.example.UMC.global.apiPayload.ApiResponse;
import com.example.UMC.global.apiPayload.code.GeneralSucessCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    //성공
    @GetMapping("sucess")
    public ApiResponse<String> sucessTest() {
        return ApiResponse.onSucess(GeneralSucessCode.OK, "요청 정상 처리");
    }

    //실패
    @GetMapping("fail")
    public ApiResponse<Void> failTest() {
        return ApiResponse.onFailure(UserErrorCode.USER_NOT_FOUND, null);
    }
}
