package com.example.UMC.domain.user.controller;


import com.example.UMC.domain.user.dto.UserReqDTO;
import com.example.UMC.domain.user.entity.User;
import com.example.UMC.domain.user.exception.code.UserErrorCode;
import com.example.UMC.domain.user.service.UserService;
import com.example.UMC.global.apiPayload.ApiResponse;
import com.example.UMC.global.apiPayload.code.GeneralSucessCode;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 API
    @PostMapping("/signup")
    public ApiResponse<User> signUp(@RequestBody @Valid UserReqDTO.JoinDTO dto) {
        System.out.println("dto email = " + dto.email());
        User user = userService.signup(dto);
        return ApiResponse.onSucess(GeneralSucessCode.OK, user);
    }

    // 로그인 API
    @PostMapping("/login")
    public ApiResponse<String> login(@RequestParam String email, @RequestParam String password) {
        Optional<User> userOpt = userService.login(email, password);

        if (userOpt.isPresent()) {
            return ApiResponse.onSucess(GeneralSucessCode.OK, userOpt.get().getEmail());
        } else {
            return ApiResponse.onFailure(UserErrorCode.INVALID_CREDENTIALS, null);
        }
    }
}

