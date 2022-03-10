package com.order.producerorder.repo;


import com.order.producerorder.model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, String> {
}
