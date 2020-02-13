package com.allianz.auth.tokenconverter.converter;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.netty.handler.codec.base64.Base64;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Log
public class EpacToItmpTokenConverter {


  @Cacheable("itmptoken")
  public String convert(String epacToken) {

    log.info("Converting Epac Token to Itmp token. Cache NOT used");

    String itmpToken = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + epacToken + "-itmp";

    return itmpToken;

  }

}
