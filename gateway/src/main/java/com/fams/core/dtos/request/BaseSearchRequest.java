package com.fams.core.dtos.request;

import lombok.Data;

import java.util.List;

/**
 * Long PC
 * 20/03/2023| 15:03 | 2023
 **/
@Data
public class BaseSearchRequest {
    private Integer pageNumber = 0;
    private Integer pageSize = 10;
    private boolean isPagination;
    private List<BaseSort> sort;
}
