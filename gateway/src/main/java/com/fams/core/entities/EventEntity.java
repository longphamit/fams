package com.fams.core.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Event")
public class EventEntity {
    @Id
    private String id;
    private String name;
    private Date createdAt;
    private Date fromDate;
    private String description;
    private Date toDate;
    private String type;
    private BigDecimal fee;
    private List<EventActionEntity> actions;
    private List<String> elements;
    private List<String> members;
    private String creator;
    private String status;
    private String result;
    private String groupId;
}
