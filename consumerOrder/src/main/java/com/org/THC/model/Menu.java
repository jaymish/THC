package com.org.THC.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Data
public class Menu {
    @Id
    private String id;
    private String itemName;
    private String description;
    private String price;
    private String category;
    private String status;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Location location;


    public Menu(){
        this.id= UUID.randomUUID().toString();
        this.status="Active";
    }
}
