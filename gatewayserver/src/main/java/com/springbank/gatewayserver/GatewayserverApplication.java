package com.springbank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator springBankRouteConfig(RouteLocatorBuilder routeLocatorBuilder){
		return routeLocatorBuilder.routes()
				.route(p -> p
						.path("/springbank/accounts/**") // predicate
						.filters(f -> f.rewritePath("/springbank/accounts/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()) // adding custom response header
								.circuitBreaker(config -> config.setName("accountsCircuitBreaker")))
						.uri("lb://ACCOUNTS"))
				.route(p -> p
						.path("/springbank/loans/**")
						.filters(f -> f.rewritePath("/springbank/loans/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://LOANS"))
				.route(p -> p
						.path("/springbank/cards/**")
						.filters(f -> f.rewritePath("/springbank/cards/(?<segment>.*)","/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CARDS")).build();
	}

}
