package com.org.THC.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class APIName {
    @Id
    private String id;
    private String name;

    public APIName(){
        this.id= UUID.randomUUID().toString();
    }
}
