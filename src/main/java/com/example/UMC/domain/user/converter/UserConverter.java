package com.example.UMC.domain.user.converter;

import com.example.UMC.domain.enums.entity.Role;
import com.example.UMC.domain.user.dto.UserReqDTO;
import com.example.UMC.domain.user.entity.User;

public class UserConverter {
    public static User toUser(
            UserReqDTO.JoinDTO dto,
            String password,
            Role role
    ) {
        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .role(role)
                .birth(dto.birth())
                .address(dto.address())
                .gender(dto.gender())
                .loginId(dto.email())
                .build();
    }

}
