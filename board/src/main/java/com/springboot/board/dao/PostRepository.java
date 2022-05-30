package com.springboot.board.dao;

import com.springboot.board.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Modifying
    @Query("update Post p set p.hits = p.hits + 1 where p.id = :id")
    public int updateHits(Long id);
}
