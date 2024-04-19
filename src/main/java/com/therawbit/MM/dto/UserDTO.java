package com.therawbit.MM.dto;

import com.therawbit.MM.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String fullName;
    private Status status;
}
