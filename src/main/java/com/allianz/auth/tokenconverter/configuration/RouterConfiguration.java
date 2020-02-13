package com.allianz.auth.tokenconverter.configuration;

import com.allianz.auth.tokenconverter.filters.EpacToItmpTokenGatewayFilterFactory;
import com.allianz.auth.tokenconverter.filters.ItmpToEpacTokenGatewayFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RouterConfiguration {

  public static final String AUTHORIZATION = "Authorization";

  @Autowired
  private EpacToItmpTokenGatewayFilterFactory toItmp;

  @Autowired
  private ItmpToEpacTokenGatewayFilterFactory toEpac;

  @Bean
  public RouteLocator myRoutes(RouteLocatorBuilder builder) {
    return builder.routes()
      .route(p -> p
        .header(AUTHORIZATION, "Bearer .*").and()
        .host("epac.allianz.com")
        .filters(f -> f.filter(toEpac.apply(c -> {
        })))
        .uri("http://httpbin.org:80"))
      .route(p -> p
        .header(AUTHORIZATION, "Bearer .*").and()
        .host("itmp.allianz.com")
        .filters(f -> f.filter(toItmp.apply(c -> {
        })))
        .uri("http://httpbin.org:80"))
      .route(p -> p.alwaysTrue()
        .uri("http://httpbin.org:80"))
      .build();
  }


}
