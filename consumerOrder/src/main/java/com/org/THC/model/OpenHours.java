package com.org.THC.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.UUID;

@Entity
@Data
public class OpenHours {
    @Id
    private String id;
    private String day;
    private Time startTime;
    private Time endTime;
    private String status;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Location location;

    public OpenHours(){
        this.id= UUID.randomUUID().toString();
        this.status="Active";
    }
}
