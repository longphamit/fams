package com.fams.manager.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Actions")
public class EventActionEntity {
    @Id
    private String id;
    private String type;
    private String value;
    private Date createdAt;
    private String creator;
    private String eventId;
    private String referId;
}
