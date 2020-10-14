package com.taiji.webfluxcurd.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(value = "role_user")
public class RoleUser {
	
    private String id;
    private String roleId;
    private String userId;
    
}
