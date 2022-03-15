package com.org.THC.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Role {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private ERole name;

    public Role(ERole name){
        this.name=name;
    }

    public Role() {
        this.id= UUID.randomUUID().toString();
    }
}
