package com.foodshop.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignInUserDTO {
    @NotEmpty(message = "Email is required")
    @Email(message = "Email is invalid", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, max = 15, message = "Length of the password must be between 8 to 15 characters.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "Password should contain at lease one number, one symbol and one upper case letter and one lower case letter")
    private String password;
}
