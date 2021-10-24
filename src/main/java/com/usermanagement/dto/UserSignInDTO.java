package com.usermanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@AllArgsConstructor
@Builder
@Data
public class UserSignInDTO {

    @NotEmpty(message = "İstifadəçi adı boş ola bilməz")
    private String username;
    @NotEmpty(message = "Şifrə boş ola bilməz")
    private String password;
}
