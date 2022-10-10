package com.fams.manager.dtos.response;

import com.fams.manager.entities.AccountEntity;
import com.fams.manager.entities.EventActionEntity;
import com.fams.manager.entities.EventElementEntity;
import com.fams.manager.entities.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetEventResponse {
    private String id;
    private String name;
    private Date createdAt;
    private Date fromDate;
    private Date toDate;
    private String type;
    private BigDecimal fee;
    private List<EventActionEntity> actions;
    private List<EventElementEntity> elements;
    private List<AccountEntity> members;
    private GroupEntity group;
    private AccountEntity creator;
    private String status;
    private String result;
    private boolean isJoined;
}
