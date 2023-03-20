package com.fams.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

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
    private String accountId;
    private String groupId;
    private List<String> elementIds;
}
