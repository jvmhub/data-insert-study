package com.jvmhub.datainsertstudy.web;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

public record ResultDto(float processingTimeInSeconds, LocalTime finishedAt) {

  public static ResultDto startedAt(Instant startTime) {
    return new ResultDto((float) Duration.between(startTime, Instant.now()).toMillis() / 1000, LocalTime.now());
  }

}
