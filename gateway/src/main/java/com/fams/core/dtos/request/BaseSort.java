package com.fams.core.dtos.request;

import lombok.Data;

/**
 * Long PC
 * 20/03/2023| 15:04 | 2023
 **/
@Data
public class BaseSort {
    private String key;
    private Boolean asc = true;
}
