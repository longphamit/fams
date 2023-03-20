package com.fams.core.dtos.request;

import lombok.Data;

import java.util.Date;

/**
 * Long PC
 * 04/03/2023| 12:54 | 2023
 **/
@Data
public class GetPostsRequest extends BaseGetRequest {
    private String title;
    private String author;
    private String status;
}
