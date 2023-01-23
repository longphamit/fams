package com.fams.core.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetEventElementRequest {
    private String id;
    private String name;
    private String description;
    private String type;
}
