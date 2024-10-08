package com.lgran.billingjob.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lgran.billingjob.BillingJob;

@Configuration
public class BillingJobConfiguration {

  @Bean
  public Job job(JobRepository jobRepository) {
    return new BillingJob(jobRepository);
  }

}
