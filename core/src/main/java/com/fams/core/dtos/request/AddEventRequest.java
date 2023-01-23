package com.fams.core.dtos.request;

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
public class AddEventRequest {
    private String name;
    private String description;
    private Date fromDate;
    private Date toDate;
    private String type;
    private BigDecimal fee;
    private String groupId;
    private List<String> elements;
}
