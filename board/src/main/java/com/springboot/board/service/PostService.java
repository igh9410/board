package com.springboot.board.service;

import com.springboot.board.entity.Post;


import java.util.List;

public interface PostService {

    public List<Post> findAll();

    public Post findById(Long theId);

    public void save(Post thePost);

    public void deleteById(Long theId);

    public int updateHits(Long theId);


}
