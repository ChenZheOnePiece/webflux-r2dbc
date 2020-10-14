package com.taiji.webfluxcurd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "role")
public class Role {
    @Id
    private String id;
    private LocalDateTime createdDate;
    private String roleName;


}
