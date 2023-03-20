package com.fams.core.controllers.impl;

import com.fams.core.controllers.PostsController;
import com.fams.core.dtos.request.CreatePostsRequest;
import com.fams.core.dtos.request.GetPostsRequest;
import com.fams.core.dtos.response.GetPostsResponse;
import com.fams.core.entities.PostsEntity;
import com.fams.core.enums.TypeEnum;
import com.fams.core.repositories.PostsManager;
import com.fams.core.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Long PC
 * 20/03/2023| 14:40 | 2023
 **/
@Service
public class PostsServiceImpl implements PostsController {
    @Autowired
    private PostsManager postsRepository;

    public GetPostsResponse create(String performerId, CreatePostsRequest createPostsRequest) {
        PostsEntity posts = PostsEntity.builder()
                .createdBy(performerId)
                .author(performerId)
                .status(TypeEnum.Status.OPEN.getCode())
                .statusLabel(TypeEnum.Status.OPEN.name())
                .process(TypeEnum.Process.NEW.getCode())
                .processLabel(TypeEnum.Process.NEW.name())
                .createdStamp(Calendar.getInstance().getTime())
                .content(createPostsRequest.getContent())
                .title(createPostsRequest.getTitle())
                .build();
        return MapperUtil.map(postsRepository.create(posts),GetPostsResponse.class);
    }

    public Page<GetPostsResponse> get(GetPostsRequest getPostsRequest) {
        return postsRepository.get(getPostsRequest);
    }
}
