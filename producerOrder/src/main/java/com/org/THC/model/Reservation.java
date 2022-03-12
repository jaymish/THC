package com.org.THC.model;

import lombok.Data;

import javax.persistence.*;
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
    private String date;
    @OneToOne(cascade = CascadeType.ALL)
    private TimeModel time;
    private String status;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Location location;



    public Reservation(){
        this.id= UUID.randomUUID().toString();
        this.status="Active";
    }
}
