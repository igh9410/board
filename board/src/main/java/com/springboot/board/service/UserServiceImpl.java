package com.springboot.board.service;

import com.springboot.board.dao.PostRepository;
import com.springboot.board.dao.UserRepository;
import com.springboot.board.entity.Post;
import com.springboot.board.entity.Role;
import com.springboot.board.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository) {
        userRepository = theUserRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnabled(true);
        Role role = new Role();
        Integer defaultRole = 1;
        role.setId(defaultRole.longValue());
        user.getRoles().add(role);
        userRepository.save(user);
    }

}
