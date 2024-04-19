package com.therawbit.MM.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO {
    @NotBlank(message="Username Cannot be blank")
    private String username;
    @NotBlank(message = "Enter your full name")
    private String fullName;
    @Length(min = 8,message = "Password should contains at least 8 characters.")
    private String password;
}
