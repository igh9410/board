package com.springboot.board.service;


import com.springboot.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {

    public Page<Post> findAll(Pageable pageable);

    public Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

    public Post findById(Long theId);

    public void validateUser(Long theId, String username);

    public void save(Post thePost);

    public void deleteById(Long theId, String username);

    public int updateHits(Long theId);


}
