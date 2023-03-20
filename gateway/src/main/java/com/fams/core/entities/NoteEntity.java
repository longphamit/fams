package com.fams.core.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Long PC
 * 20/03/2023| 15:46 | 2023
 **/
@Data
@Document
public class NoteEntity {
    @Id
    private String id;
    private String content;
    private String refId;
    private String createdBy;
    private String createdStamp;
    private String type;
}
