package com.allianz.auth.tokenconverter.filters;

import com.allianz.auth.tokenconverter.converter.ItmpToEpacTokenConverter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import static com.allianz.auth.tokenconverter.configuration.RouterConfiguration.AUTHORIZATION;

@Component
@Log
public class ItmpToEpacTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<ItmpToEpacTokenGatewayFilterFactory.Config> {

  @Autowired
  private ItmpToEpacTokenConverter converter;

  public ItmpToEpacTokenGatewayFilterFactory() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {

    return (exchange, chain) -> {

      ServerHttpRequest originalRequest = exchange.getRequest();
      String authenticationHeader = originalRequest.getHeaders().get(AUTHORIZATION).get(0);

      log.info("Translating token to epac.. trying cache");
      String translatedToken = converter.convert(authenticationHeader);

      ServerHttpRequest request = originalRequest.mutate().header(AUTHORIZATION, translatedToken).build();
      return chain.filter(exchange.mutate().request(request).build());
    };
  }

  public static class Config {
    //Put the configuration properties for your filter here
  }

}
