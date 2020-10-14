package com.taiji.webfluxcurd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    private String id;
    private String name;
    private Integer age;
    private byte[] content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String roleName;

}
