package com.foodshop.user_service.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.foodshop.user_service.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(nullable = false,name = "first_name")
    @JsonProperty("first_name")
    private String firstName;

    @Column(nullable = false,name = "last_name")
    @JsonProperty("last_name")
    private String lastName;

    @Column(nullable = false,name = "created_at")
    @CreationTimestamp
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Column(nullable = false,name = "updated_at")
    @UpdateTimestamp
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false,name = "role")
    private UserRole role = UserRole.CUSTOMER;


}
