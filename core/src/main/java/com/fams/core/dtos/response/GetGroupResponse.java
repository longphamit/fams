package com.fams.core.dtos.response;

import com.fams.core.entities.EventElementEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetGroupResponse {
    private String id;
    private String name;
    private GetAccountResponse creator;
    private List<EventElementEntity> eventElements;
}
