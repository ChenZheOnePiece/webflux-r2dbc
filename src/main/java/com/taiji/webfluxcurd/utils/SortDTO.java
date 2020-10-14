package com.taiji.webfluxcurd.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public final class SortDTO implements Serializable {
    private static final long serialVersionUID = 11L;

    private String fieldName;

    private String direction;

}
