package com.org.THC.model;

import lombok.Data;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Order {

    @Id
    private String id;
    private String date;
    @ManyToMany(cascade = CascadeType.MERGE)
    private List<OrderItem> orderItemList;
    private Double subtotal;
    private Double tax;
    private Double total;
    public Order(){
        this.id= UUID.randomUUID().toString();
        Date date= Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.date=dateFormat.format(date);
    }
}
