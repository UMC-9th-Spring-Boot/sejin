package com.example.UMC.domain.user.dto;

import com.example.UMC.domain.enums.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public class UserReqDTO {

    public record JoinDTO(
            @NotBlank
            String name,
            @Email
            String email,
            @NotBlank
            String password,
            @NotNull
            Gender gender,
            @NotNull
            String birth,
            @NotNull
            String address
    ) {}
}
