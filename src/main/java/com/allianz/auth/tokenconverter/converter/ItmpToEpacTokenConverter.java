package com.allianz.auth.tokenconverter.converter;

import lombok.extern.java.Log;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Log
public class ItmpToEpacTokenConverter {


  @Cacheable("epactoken")
  public String convert(String itmpToken) {


    log.info("Converting Itmp Token to Epac token. Cache NOT used");

    String epacToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    return epacToken;

  }


}
