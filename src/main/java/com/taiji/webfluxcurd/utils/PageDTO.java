package com.taiji.webfluxcurd.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class PageDTO implements Serializable {

    private int page = 0;
    private int pageSize = 10;

    private List<SortDTO> sorts;

    private Map filters;

    private Long total;

    private List list;
}
