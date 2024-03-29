package com.org.THC.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Orders {

    @Id
    private String id;
    private String date;
    @ManyToMany(cascade = CascadeType.MERGE)
    @Fetch(FetchMode.SELECT)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<OrderItem> orderItemList;
    private Double subtotal;
    private Double tax;
    private Double total;
    public Orders(){
        this.id= UUID.randomUUID().toString();
        Date date= Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.date=dateFormat.format(date);
    }
}
