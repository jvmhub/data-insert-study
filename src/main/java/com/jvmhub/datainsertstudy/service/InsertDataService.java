package com.jvmhub.datainsertstudy.service;

import com.jvmhub.datainsertstudy.repository.ProductEntity;
import com.jvmhub.datainsertstudy.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class InsertDataService {

  public static final int BATCH_SIZE = 20000;
  private final DataGenerator dataGenerator;
  private final ProductRepository productRepository;
  private final EntityManager entityManager;

  public void singleInsert(int count) {
    log.info("Single insert started for {} elements", count);
    for (int i = 0; i < count; i++) {
      productRepository.save(dataGenerator.randomProduct());
    }
    log.info("Single insert finished");
  }

  public void multiInsert(int count) {
    log.info("Multi insert started for {} elements", count);
    List<ProductEntity> products = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      products.add(dataGenerator.randomProduct());
    }
    productRepository.saveAll(products);
    log.info("Multi insert finished");
  }

  @Transactional
  public void singleTransactionalInsert(int count) {
    log.info("Single transactional insert started for {} elements", count);
    for (int i = 0; i < count; i++) {
      productRepository.save(dataGenerator.randomProduct());
    }
    log.info("Single transactional insert finished");
  }

  public void batchInsert(int count) {
    log.info("Batch insert started for {} elements, batch size: ", count, BATCH_SIZE);
    List<ProductEntity> products = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      products.add(dataGenerator.randomProduct());
      if (i % BATCH_SIZE == 0) {
        productRepository.saveAll(products);
        log.info("Chunk of {} elements saved", products.size());
        products = new ArrayList<>();
      }
    }
    if (products.size() > 0) {
      productRepository.saveAll(products);
      log.info("Last chunk of {} elements saved", products.size());
    }
    log.info("Batch insert finished");
  }

  @Transactional
  public void batchTransactionalInsert(int count) {
    log.info("Batch transactional insert started for {} elements, batch size: ", count, BATCH_SIZE);
    List<ProductEntity> products = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      products.add(dataGenerator.randomProduct());
      if (i % BATCH_SIZE == 0) {
        productRepository.saveAll(products);
        log.info("Chunk of {} elements saved", products.size());
        products = new ArrayList<>();
      }
    }
    if (products.size() > 0) {
      productRepository.saveAll(products);
      log.info("Last chunk of {} elements saved", products.size());
    }
    log.info("Batch transactional insert finished");
  }

  public void batchEmClearInsert(int count) {
    log.info("Batch insert with EM clear started for {} elements, batch size: ", count, BATCH_SIZE);
    List<ProductEntity> products = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      products.add(dataGenerator.randomProduct());
      if (i % BATCH_SIZE == 0) {
        productRepository.saveAll(products);
        entityManager.clear();
        log.info("Chunk of {} elements saved", products.size());
        products = new ArrayList<>();
      }
    }
    if (products.size() > 0) {
      productRepository.saveAll(products);
      log.info("Last chunk of {} elements saved", products.size());
    }
    log.info("Batch insert with EM clear finished");
  }

}
