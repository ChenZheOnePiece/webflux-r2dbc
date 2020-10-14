package com.taiji.webfluxcurd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleUserDto {
    private String id;
    private LocalDateTime createdDate;
    private String roleName;
    private String name;
    private Integer age;
    private byte[] content;

}
