package com.org.THC.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class APIExecutionTime {
    @Id
    private String id;
    @ManyToOne(cascade = CascadeType.MERGE)
    private APIName apiName;
    private String executionDate;
    private long executionTime;

    public APIExecutionTime(){
        this.id= UUID.randomUUID().toString();
    }
}
