package com.springboot.board.controller;

import com.springboot.board.entity.Post;
import com.springboot.board.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService thePostService) {
        postService = thePostService;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listPosts(Model theModel) {

        // get posts from db
        List<Post> thePosts = postService.findAll();

        // add to the spring model
        theModel.addAttribute("posts", thePosts);

        return "posts/list-posts";
    }

    // add mapping for adding new articles
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data;
        Post thePost = new Post();

        theModel.addAttribute("post", thePost);

        return "posts/post-form";
    }

    // add mapping for updating the article
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("postId") Long theId,
                                    Model theModel) {

        // get the post from the service
        Post thePost = postService.findById(theId);

        // set post as a model attribute to pre-populate the form
        theModel.addAttribute("post", thePost);

        // send over to our form
        return "posts/post-form";
    }

    // add mapping for saving the article
    @PostMapping("/save")
    public String savePost(@ModelAttribute("post") Post thePost) {

        // save the post
        postService.save(thePost);

        // use a redirect to prevent duplicate submissions
        return "redirect:/posts/list";
    }

    // add mapping for deleting the article
    @GetMapping("/delete")
    public String delete(@RequestParam("postId") Long theId) {

        // delete the post
        postService.deleteById(theId);

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
