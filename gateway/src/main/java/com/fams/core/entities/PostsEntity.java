package com.fams.core.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * Long PC
 * 04/03/2023| 12:54 | 2023
 **/
@Data
@Document
@Builder
public class PostsEntity {
    @Id
    private String id;
    private String title;
    private String content;
    private Date createdStamp;
    private Date updatedStamp;
    private String createdBy;
    private String updatedBy;
    private String author;
    private int status;
    private String statusLabel;
    private int process;
    private String processLabel;
    private List<String> contentTypes;
}
