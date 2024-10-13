package com.lgran.billingjob;

import java.util.Random;

import org.springframework.stereotype.Service;

//@slf4j
@Service
public class PricingService {

  Random random = new Random();

  public void generateTransientException() {
    if (this.random.nextInt(1000) % 3 == 0) {
      throw new PricingException("Error while retrieving data pricing");
    }
  }
}
