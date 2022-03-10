package com.order.consumerorder.service.impl;


import com.order.consumerorder.model.Orders;
import com.order.consumerorder.repo.OrderRepo;
import com.order.consumerorder.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultOrderService implements OrderService {

    private OrderRepo orderRepo;
    private EmailServiceImpl emailService;

    public DefaultOrderService(OrderRepo orderRepo, EmailServiceImpl emailService){
        this.orderRepo = orderRepo;
        this.emailService = emailService;
    }
    @Override
    public boolean createOrder(Orders orders) {
        System.out.println(orders);
        orderRepo.saveOrder(orders);
        //emailService.sendSimpleMessage(orders.getCustomer().getEmail_id(),"Order Created","Thank you your order was created. Here is the detail of your order \n"+ orders.toString());
        return true;
    }

    @Override
    public List<Orders> getAllOrders() {
        List<Orders> ordersList = orderRepo.getAllOrders();
        return ordersList;
    }

    @Override
    public Orders getOrdersById(String id){
        Orders orders = orderRepo.getOrderById(id);
        return orders;
    }

    @Override
    public List<Orders> getOrdersByZip(int zip){
        List<Orders> ordersList = orderRepo.getOrderByZip(zip);
        return ordersList;
    }

    @Override
    public Orders cancelOrder(String id) {
        //logic to check if cancel is possible
        return orderRepo.orderCancel(id);
    }

}
