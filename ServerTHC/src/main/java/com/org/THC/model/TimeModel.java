package com.org.THC.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class TimeModel {
    @Id
    private String id;
    private int hours;
    private int minutes;
    private int seconds;
    private String amPm;

    public TimeModel(){
        this.id= UUID.randomUUID().toString();
    }

}
