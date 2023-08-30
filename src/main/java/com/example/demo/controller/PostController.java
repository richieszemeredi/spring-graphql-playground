package com.example.demo.controller;

import com.example.demo.data.model.Post;
import com.example.demo.data.jpa.PostRepository;
import com.example.demo.data.jpa.UserRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class PostController {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostController(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @MutationMapping
    public Post createPost(@Argument String title, @Argument String content, @Argument  UUID userId) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setUser(userRepository.findById(userId).orElseThrow());
        postRepository.save(post);
        return post;
    }

    @QueryMapping
    public Iterable<Post> findAllPosts() {
        return postRepository.findAll();
    }
}
