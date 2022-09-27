package com.fams.manager.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {
    private String id;
    private String name;
    private Date createdAt;
    private Date fromDate;
    private Date toDate;
    private List<AccountEntity> member;
}
