package com.foodshop.user_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.user_service.models.UserModel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
public class SignUpUserRequestDTO {
    @NotEmpty(message = "Email is required.")
    @Email(message = "Invalid email address", flags = { Pattern.Flag.CASE_INSENSITIVE })
    private String email;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, max = 15, message = "Length of the password must be between 8 to 15 characters")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "Password should contain at lease one number, one symbol and one upper case letter and one lower case letter")
    private String password;

    @NotEmpty(message = "First Name is required")
    @JsonProperty("first_name")
    @Size(min = 2, max = 100, message = "Length of First Name must be between 2 and 100 characters")
    private String firstName;

    @NotEmpty(message = "Last name is required.")
    @Size(min = 2, max = 100, message = "Length of Last Name must be between 2 and 100 characters.")
    @JsonProperty("last_name")
    private String lastName;

    public UserModel toUser(){
        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }


}
