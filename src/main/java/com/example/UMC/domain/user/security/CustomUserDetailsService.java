package com.example.UMC.domain.user.security;

import com.example.UMC.domain.user.entity.User;
import com.example.UMC.domain.user.exception.UserException;
import com.example.UMC.domain.user.exception.code.UserErrorCode;
import com.example.UMC.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(
            String username
    ) throws UsernameNotFoundException {
        // 검증할 Member 조회
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        // CustomUserDetails 반환
        return new CustomUserDetails(user);
    }
}
