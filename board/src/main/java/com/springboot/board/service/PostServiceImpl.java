package com.springboot.board.service;

import com.springboot.board.dao.PostRepository;
import com.springboot.board.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository thePostRepository) {
        postRepository = thePostRepository;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long theId) {
        Optional<Post> result = postRepository.findById(theId);

        Post thePost = null;

        if (result.isPresent()) {
            thePost = result.get();
        }
        else {
            // we didn't find the post
            throw new RuntimeException("Did not find post id - " + theId);
        }
        return thePost;
    }

    @Override
    public void save(Post thePost) {
        postRepository.save(thePost);
    }

    @Override
    public void deleteById(Long theId) {
        postRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public int updateHits(Long theId) {
        return postRepository.updateHits(theId);
    }

}
