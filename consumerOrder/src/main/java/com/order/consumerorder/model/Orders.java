package com.order.consumerorder.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Orders {

    @Id
    private String id;
    private String name;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private Integer zip;
    private String status;


    public Orders(){
        this.id= UUID.randomUUID().toString();
        this.status="Active";
    }
}
