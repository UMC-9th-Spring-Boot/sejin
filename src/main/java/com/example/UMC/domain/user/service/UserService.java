package com.example.UMC.domain.user.service;

import com.example.UMC.domain.enums.entity.Role;
import com.example.UMC.domain.user.converter.UserConverter;
import com.example.UMC.domain.user.dto.request.UserReqDTO;
import com.example.UMC.domain.user.entity.User;
import com.example.UMC.domain.user.repository.UserRepository;
import com.example.UMC.domain.user.security.CustomUserDetails;
import com.example.UMC.domain.user.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 회원가입
    public User signup(UserReqDTO.JoinDTO dto) {
        String salt = passwordEncoder.encode(dto.password());
        User user = UserConverter.toUser(dto, salt, Role.ROLE_USER);
        return userRepository.save(user);
    }

    // 로그인(세션)
    public Optional<User> login(String email, String password) {
        // 이메일로 유저 조회
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // 비밀번호 확인
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty(); // 로그인 실패
    }

    // JWT 발급용: CustomUserDetails까지 만들어 주는 로그인
    public Optional<CustomUserDetails> loginForJwt(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> passwordEncoder.matches(password, user.getPassword()))
                .map(CustomUserDetails::new);
    }
}
