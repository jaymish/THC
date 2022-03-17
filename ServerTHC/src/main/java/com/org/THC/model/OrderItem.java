package com.org.THC.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class OrderItem {
    @Id
    private String id;
    private String item_name;
    private String item_qty;
    public OrderItem(){
        this.id= UUID.randomUUID().toString();
    }
}
