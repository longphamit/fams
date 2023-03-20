package com.fams.core.dtos.response;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * Long PC
 * 04/03/2023| 13:09 | 2023
 **/
@Data
@Builder
public class GetPostsResponse {
    private String id;
    private String title;
    private GetAccountResponse author;
    private GetAccountResponse createBy;
    private String content;
    private Date createdStamp;
    private String status;
}
