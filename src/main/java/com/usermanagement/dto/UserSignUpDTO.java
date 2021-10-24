package com.usermanagement.dto;

import com.usermanagement.validation.UsernameConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@Builder
@Data
public class UserSignUpDTO {

    @NotEmpty(message = "İstifadəçi adı boş ola bilməz")
    @UsernameConstraint
    private String username;
    @NotEmpty(message = "Şifrə boş ola bilməz")
    private String password;
}
