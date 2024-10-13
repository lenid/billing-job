package com.lgran.billingjob;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lgran.billingjob.model.BillingData;
import com.lgran.billingjob.model.ReportingData;

@Service
public class BillingDataProcessor implements ItemProcessor<BillingData, ReportingData> {

  @Autowired
  private PricingService pricingService;

  @Value("${spring.cellular.pricing.data:0.9}")
  private float dataPricing;

  @Value("${spring.cellular.pricing.call:1.0}")
  private float callPricing;

  @Value("${spring.cellular.pricing.sms:0.1}")
  private float smsPricing;

  @Value("${spring.cellular.spending.threshold:15}")
  private float spendingThreshold;

  @Override
  public ReportingData process(BillingData item) {
    pricingService.generateTransientException();

    double billingTotal = item.dataUsage() * dataPricing + item.callDuration() * callPricing + item.smsCount() * smsPricing;
    if (billingTotal < spendingThreshold) {
      return null;
    }

    return new ReportingData(item, billingTotal);
  }
}
