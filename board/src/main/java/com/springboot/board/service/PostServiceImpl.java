package com.springboot.board.service;

import com.springboot.board.dao.PostRepository;
import com.springboot.board.dao.UserRepository;
import com.springboot.board.entity.Post;
import com.springboot.board.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository thePostRepository, UserRepository theUserRepository) {
        postRepository = thePostRepository;
        userRepository = theUserRepository;
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAllByOrderByIdDesc(pageable);
    }

    @Override
    public Page<Post> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable) {
        return postRepository.findByTitleContainingOrContentContainingOrderByIdDesc(title, content, pageable);
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
    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    public void validateUser(Long theId, String username) {
        return;
    }

    @Override
    public void save(Post thePost) {
        postRepository.save(thePost);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #username == authentication.principal.username")
    public void deleteById(Long theId, String username) {
        postRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public int updateHits(Long theId) {
        return postRepository.updateHits(theId);
    }

}
