package com.taiji.webfluxcurd.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "user")
public class User {
    @Id
    private String id;
    private String name;
    private Integer age;
    private byte[] content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
//    private Role role;

}
