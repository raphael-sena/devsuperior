package com.raphaelsena.challenge_dependency_injection.services;

import com.raphaelsena.challenge_dependency_injection.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    private Double shippment;

    public Double shippment(Order order) {
        if (order.getBasic() < 100.00) {
            this.shippment = 20.00;
        } else if (order.getBasic() > 100.00 && order.getBasic() < 200.00) {
            this.shippment = 12.00;
        } else {
            this.shippment = 0.00;
        }
        return shippment;
    }
}
