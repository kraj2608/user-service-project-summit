package com.foodshop.user_service.respositories;
import com.foodshop.user_service.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    boolean existsByEmail(String email);
    UserModel getUserByEmail(String email);
}
