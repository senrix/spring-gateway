package com.allianz.auth.tokenconverter.filters;

import com.allianz.auth.tokenconverter.converter.EpacToItmpTokenConverter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import static com.allianz.auth.tokenconverter.configuration.RouterConfiguration.AUTHORIZATION;

@Component
@Log
public class EpacToItmpTokenGatewayFilterFactory extends AbstractGatewayFilterFactory<EpacToItmpTokenGatewayFilterFactory.Config> {

@Autowired
  private EpacToItmpTokenConverter converter;

  public EpacToItmpTokenGatewayFilterFactory() {
    super(Config.class);
  }

  @Override
  public GatewayFilter apply(Config config) {
    // grab configuration from Config object
    return (exchange, chain) -> {
      ServerHttpRequest originalRequest = exchange.getRequest();
      String authenticationHeader = originalRequest.getHeaders().get(AUTHORIZATION).get(0);
      log.info("Translating token to itmp.. trying cache");
      String translatedToken = converter.convert(authenticationHeader);
      ServerHttpRequest request = originalRequest.mutate().header(AUTHORIZATION, translatedToken).build();
      return chain.filter(exchange.mutate().request(request).build());
    };
  }

  public static class Config {
    //Put the configuration properties for your filter here
  }

}
