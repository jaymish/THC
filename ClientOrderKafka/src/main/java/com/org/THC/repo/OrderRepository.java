package com.org.THC.repo;

import com.org.THC.model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Orders, String> {
    void deleteAllById(String id);
}
