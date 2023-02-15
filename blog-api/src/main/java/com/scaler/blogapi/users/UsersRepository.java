package com.scaler.blogapi.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);
}
