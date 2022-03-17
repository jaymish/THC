package com.org.THC.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;
import java.util.UUID;

@Entity
@Data
public class OpenHours {
    @Id
    private String id;
    private String day;
    @OneToOne(cascade = CascadeType.ALL)
    private TimeModel startTime;
    @OneToOne(cascade = CascadeType.ALL)
    private TimeModel endTime;
    private String status;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Location location;

    public OpenHours(){
        this.id= UUID.randomUUID().toString();
        this.status="Active";
    }
}
