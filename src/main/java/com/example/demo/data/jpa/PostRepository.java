package com.example.demo.data.jpa;

import com.example.demo.data.model.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, UUID> {
}
