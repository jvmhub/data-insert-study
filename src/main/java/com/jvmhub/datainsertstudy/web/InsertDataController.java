package com.jvmhub.datainsertstudy.web;

import com.jvmhub.datainsertstudy.service.InsertDataService;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/insert-data")
@RequiredArgsConstructor
public class InsertDataController {

  private final InsertDataService insertDataService;

  @GetMapping("/single/{count}")
  public ResultDto singleInsert(@PathVariable int count) {
    Instant startTime = Instant.now();
    insertDataService.singleInsert(count);
    return ResultDto.startedAt(startTime);
  }

  @GetMapping("/multi/{count}")
  public ResultDto multiInsert(@PathVariable int count) {
    Instant startTime = Instant.now();
    insertDataService.multiInsert(count);
    return ResultDto.startedAt(startTime);
  }

  @GetMapping("/single-transactional/{count}")
  public ResultDto singleTransactionalInsert(@PathVariable int count) {
    Instant startTime = Instant.now();
    insertDataService.singleTransactionalInsert(count);
    return ResultDto.startedAt(startTime);
  }

  @GetMapping("/batch/{count}")
  public ResultDto batchInsert(@PathVariable int count) {
    Instant startTime = Instant.now();
    insertDataService.batchInsert(count);
    return ResultDto.startedAt(startTime);
  }

  @GetMapping("/batch-transactional/{count}")
  public ResultDto batchTransactionalInsert(@PathVariable int count) {
    Instant startTime = Instant.now();
    insertDataService.batchTransactionalInsert(count);
    return ResultDto.startedAt(startTime);
  }

  @GetMapping("/batch-em-clear/{count}")
  public ResultDto batchEmClearInsert(@PathVariable int count) {
    Instant startTime = Instant.now();
    insertDataService.batchEmClearInsert(count);
    return ResultDto.startedAt(startTime);
  }

}
