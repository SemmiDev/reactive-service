package com.sammidev.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static java.util.Collections.singletonMap;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;


@SpringBootApplication
public class DemoApplication {


	@Bean
	public RouterFunction<ServerResponse> router() {
		return
				route(
								GET("/"), request -> ok().body(Mono.just("holaaaa!"), String.class))
						.andRoute(
								GET("/{name}"), request -> ok().body(Mono.just("hello, " + request.pathVariable("name") + "!"), String.class))
						.andRoute(
								GET("/**"), request -> ok().body(Mono.just("fallback"), String.class));
	}


	public static void main(String[] args) {
		new SpringApplicationBuilder(DemoApplication.class)
				.properties(singletonMap("server.port", "3000"))
				.run(args);
	}
}