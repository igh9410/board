package com.springboot.board.dao;


import com.springboot.board.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying
    @Query("update Post p set p.hits = p.hits + 1 where p.id = :id")
    public int updateHits(Long id);

    // add a method to sort by id
    public Page<Post> findAllByOrderByIdDesc(Pageable pageable);

}
