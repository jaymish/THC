package com.org.THC.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

@Entity
@Data
public class Reservation {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailId;
    private int noOfPeople;
    private Date date;
    private Time time;
    private String status;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Location location;



    public Reservation(){
        this.id= UUID.randomUUID().toString();
        this.status="Active";
    }
}
