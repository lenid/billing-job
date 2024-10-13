package com.lgran.billingjob;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.stereotype.Service;

@Service
public class BillingDataRetryListener implements RetryListener {

  @Override
  public <T, E extends Throwable> void onError(final RetryContext context, final RetryCallback<T, E> callback, final Throwable throwable) {
    System.err.println(throwable.getMessage());
  }
}
