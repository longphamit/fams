package com.fams.core.controllers;

import com.fams.core.dtos.request.CreatePostsRequest;
import com.fams.core.dtos.request.GetPostsRequest;
import com.fams.core.dtos.response.GetPostsResponse;
import org.springframework.data.domain.Page;

/**
 * Long PC
 * 20/03/2023| 15:41 | 2023
 **/
public interface PostsController {
    GetPostsResponse create(String performerId, CreatePostsRequest createPostsRequest);

    Page<GetPostsResponse> get(GetPostsRequest getPostsRequest);
}
