package com.example.UMC.domain.user.controller;


import com.example.UMC.domain.user.dto.request.UserReqDTO;
import com.example.UMC.domain.user.entity.User;
import com.example.UMC.domain.user.exception.code.UserErrorCode;
import com.example.UMC.domain.user.security.CustomUserDetails;
import com.example.UMC.domain.user.service.UserService;
import com.example.UMC.domain.user.util.JwtUtil;
import com.example.UMC.global.apiPayload.ApiResponse;
import com.example.UMC.global.apiPayload.code.GeneralSucessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 회원가입 API
    @PostMapping("/signup")
    public ApiResponse<String> signUp(@RequestBody @Valid UserReqDTO.JoinDTO dto) {
        // 1. 회원가입 처리
        User user = userService.signup(dto);

        // 2. 바로 CustomUserDetails 생성 + JWT 발급
        CustomUserDetails userDetails = new CustomUserDetails(user);
        String accessToken = jwtUtil.createAccessToken(userDetails);

        return ApiResponse.onSucess(GeneralSucessCode.OK, accessToken);
    }

    // 로그인 API
    @PostMapping("/login")
    public ApiResponse<String> login(@RequestParam String email,
                                     @RequestParam String password) {

        Optional<CustomUserDetails> userDetailsOpt = userService.loginForJwt(email, password);

        if (userDetailsOpt.isEmpty()) {
            return ApiResponse.onFailure(UserErrorCode.INVALID_CREDENTIALS, null);
        }

        String accessToken = jwtUtil.createAccessToken(userDetailsOpt.get());
        return ApiResponse.onSucess(GeneralSucessCode.OK, accessToken);
    }
}

