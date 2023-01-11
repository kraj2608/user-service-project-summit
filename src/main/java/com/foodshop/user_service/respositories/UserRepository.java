package com.foodshop.user_service.respositories;
import com.foodshop.user_service.models.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDTO, UUID> {
    boolean existsByEmail(String email);
}
