package com.raphaelsena.challenge_dependency_injection;

import com.raphaelsena.challenge_dependency_injection.entities.Order;
import com.raphaelsena.challenge_dependency_injection.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeDependencyInjectionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeDependencyInjectionApplication.class, args);
	}

	@Autowired
	private OrderService orderService;

	@Override
	public void run(String... args) throws Exception {

		Order order1 = new Order(1034, 150.00, 20.0);
		Order order2 = new Order(2282, 800.00, 10.0);
		Order order3 = new Order(1034, 95.90, 0.0);

		orderService.total(order2);
		orderService.total(order3);

		System.out.println("\n############# SAÍDA 1 #############");
		System.out.println("Pedido código " + order1.getCode());
		System.out.printf("Valor total: R$ %.2f", orderService.total(order1));

		System.out.println("\n\n############# SAÍDA 2 #############");
		System.out.println("Pedido código " + order2.getCode());
		System.out.printf("Valor total: R$ %.2f", orderService.total(order2));

		System.out.println("\n\n############# SAÍDA 3 #############");
		System.out.println("Pedido código " + order3.getCode());
		System.out.printf("Valor total: R$ %.2f", orderService.total(order3));
	}
}
