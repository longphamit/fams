package com.fams.core.dtos.request;

import lombok.Data;

import java.util.List;

/**
 * Long PC
 * 04/03/2023| 13:08 | 2023
 **/
@Data
public class BaseGetRequest {
    private int pageNumber;
    private int pageSize;
    private boolean isPagination;
    private List<BaseSort> sort;
}
