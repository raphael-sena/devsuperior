package com.raphaelsena.challenge_dependency_injection.services;

import com.raphaelsena.challenge_dependency_injection.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private ShippingService shippingService;

    private Double total = 0.0;

    public Double total(Order order) {
        this.total =  shippingService.shippment(order) + (order.getBasic() - ((order.getDiscount() / 100.0) * order.getBasic()));
        return this.total;
    }
}
