package com.fams.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Group")
public class GroupEntity {
    @Id
    private String id;
    private String name;
    private List<String> members;
    private List<EventElementEntity> eventElements;
    private String admin;
    private Date createdAt;
}
