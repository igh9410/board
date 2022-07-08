package com.springboot.board.controller;

import com.springboot.board.entity.Post;
import com.springboot.board.entity.User;
import com.springboot.board.service.PostService;
import com.springboot.board.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostService postService;
    private UserService userService;

    public PostController(PostService thePostService, UserService theUserService) {
        postService = thePostService;
        userService = theUserService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listPosts(Model theModel, @PageableDefault(size = 10) Pageable pageable, @RequestParam(required = false, defaultValue = "")
            String searchText) {

        // get posts from db
        // Page<Post> thePosts =  postService.findAll(pageable);
        Page<Post> thePosts = postService.findByTitleContainingOrContentContaining(searchText, searchText, pageable);


        int startPage = Math.max(1, thePosts.getPageable().getPageNumber() - 4);
        int endPage = Math.min(thePosts.getTotalPages(), thePosts.getPageable().getPageNumber() + 4);
        theModel.addAttribute("startPage", startPage);
        theModel.addAttribute("endPage", endPage);

        // add to the spring model
        theModel.addAttribute("posts", thePosts);

        return "posts/list-posts";
    }

    // add mapping for adding new articles
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel, Authentication authentication) {

        // create model attribute to bind form data;
        Post thePost = new Post();
        String username = authentication.getName();
        User theUser = userService.findByUsername(username);
        thePost.setUser(theUser);
        theModel.addAttribute("post", thePost);

        return "posts/post-form";
    }

    // add mapping for update form of the article
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("postId") Long theId,
                                    Model theModel, Authentication authentication) {


        // Validate if the user is authorized then get the post from the service
        Post thePost = postService.findById(theId);

        String username = thePost.getUser().getUsername();

        postService.validateUser(theId, username);
        // set post as a model attribute to pre-populate the form
        theModel.addAttribute("post", thePost);

        // send over to our form
        return "posts/post-form";
    }

    // add mapping for saving the article
    @PostMapping("/save")
    public String savePost(@ModelAttribute("post") @Valid Post thePost, BindingResult result, Authentication authentication) {

        String username = authentication.getName();

        User theUser = userService.findByUsername(username);
        if (result.hasErrors()) {
            return "posts/post-form";
        }


        // save the post and prevent changing post username when logged in as Admin
        postService.save(thePost);
        // use a redirect to prevent duplicate submissions
        return "redirect:/posts/list";
    }

    // add mapping for deleting the article
    @GetMapping("/delete")
    public String delete(@RequestParam("postId") Long theId) {
        Post thePost = postService.findById(theId);
        String username = thePost.getUser().getUsername();
        // delete the post
        postService.deleteById(theId, username);

        // redirect to /posts/list;
        return "redirect:/posts/list";
    }

    // add mapping for viewing the article
    @GetMapping("/view")
    public String view(@RequestParam("postId") Long theId,
                                    Model theModel) {

        // get the post from the service
        Post thePost = postService.findById(theId);
        postService.updateHits(theId);

        // set post as a model attribute to pre-populate the form
        theModel.addAttribute("post", thePost);

        // send over to our form
        return "posts/view-post";
    }



}
