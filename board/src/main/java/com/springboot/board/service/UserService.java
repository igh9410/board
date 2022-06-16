package com.springboot.board.service;


import com.springboot.board.entity.User;

public interface UserService {

    public User findByUsername(String username);
    public void save(User user);

}
