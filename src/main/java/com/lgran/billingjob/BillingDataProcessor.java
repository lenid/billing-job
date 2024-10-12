package com.lgran.billingjob;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import com.lgran.billingjob.model.BillingData;
import com.lgran.billingjob.model.ReportingData;

public class BillingDataProcessor implements ItemProcessor<BillingData, ReportingData> {

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
    double billingTotal = item.dataUsage() * dataPricing + item.callDuration() * callPricing + item.smsCount() * smsPricing;
    if (billingTotal < spendingThreshold) {
      return null;
    }

    return new ReportingData(item, billingTotal);
  }
}
