package com.springboot.board.dao;

import com.springboot.board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);

    public boolean existsByUsername(String username);

}
