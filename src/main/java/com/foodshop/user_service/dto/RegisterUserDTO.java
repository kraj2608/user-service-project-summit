package com.foodshop.user_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.user_service.models.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
public class RegisterUserDTO {
    @NotEmpty(message = "The email address is required.")
    @Email(message = "Email is required", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    @NotEmpty(message = "The password is required.")
    @Size(min = 8, max = 15, message = "The length of the password must be between 8 to 15 characters.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "Password should contain at lease one number, one symbol and one upper case letter and one lower case letter")
    private String password;

    @NotEmpty(message = "The first_name is required.")
    @JsonProperty("first_name")
    @Size(min = 2, max = 100, message = "Length of first_name must be between 2 and 100 characters.")
    private String firstName;

    @NotEmpty(message = "The last_name is required.")
    @Size(min = 2, max = 100, message = "Length of last_name must be between 2 and 100 characters.")
    @JsonProperty("last_name")
    private String lastName;

    public User toUser(){
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }


}
