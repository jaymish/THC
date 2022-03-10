package com.org.THC.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class Location {

    @Id
    private String id;
    private String name;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private Integer zip;
    private String status;


    public Location(){
        this.id= UUID.randomUUID().toString();
        this.status="Active";
    }
}
