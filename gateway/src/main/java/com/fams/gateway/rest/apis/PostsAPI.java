package com.fams.gateway.rest.apis;

import com.fams.core.controllers.PostsController;
import com.fams.core.dtos.request.CreatePostsRequest;
import com.fams.core.dtos.request.GetPostsRequest;
import com.fams.core.dtos.response.GetPostsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Long PC
 * 04/03/2023| 13:02 | 2023
 **/
@RestController
@RequestMapping
public class PostsAPI {
    @Autowired
    private PostsController postsService;

    @GetMapping
    public GetPostsResponse get(GetPostsRequest getPostsRequest) {
        return GetPostsResponse.builder().build();
    }

    @PostMapping
    public void add(CreatePostsRequest createPostsRequest) {
        postsService.create("", createPostsRequest);
    }

    @DeleteMapping
    public void delete(String id) {

    }
}
