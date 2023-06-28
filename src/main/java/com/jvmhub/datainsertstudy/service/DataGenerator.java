package com.jvmhub.datainsertstudy.service;

import com.jvmhub.datainsertstudy.repository.ProductEntity;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DataGenerator {

  private static Random random = new Random();

  public ProductEntity randomProduct() {
    return ProductEntity.builder()
        .sku(UUID.randomUUID().toString())
        .name(randomName())
        .description(randomDescription())
        .build();
  }

  private String randomName() {
    StringBuilder name = new StringBuilder();
    name.append(brand[random.nextInt(brand.length)]);
    name.append(" ");
    name.append(adjective[random.nextInt(adjective.length)]);
    name.append(" ");
    name.append(productType[random.nextInt(productType.length)]);
    return name.toString();
  }

  private String randomDescription() {
    Set descSet = new HashSet<String>();
    for (int i = 0; i < 3; i++) {
      descSet.add(description[random.nextInt(description.length)]);
    }
    StringBuilder description = new StringBuilder();
    descSet.forEach(d -> description.append(d).append(" "));
    description.deleteCharAt(description.length() - 1);
    return description.toString();
  }

  private static String[] brand = {"sony", "bose", "samsung", "jbl", "yamaha", "dyson", "miele", "lenovo", "microsoft", "acer", "asus", "dell", "msi"};
  private static String[] adjective = {"nice", "cheap", "big", "small", "expensive", "popular", "fast", "slow", "quiet", "black", "white", "red", "green"};
  private static String[] productType = {"phone", "notebook", "tablet", "monitor", "console", "headphones", "speakers", "soundbar", "subwoofer", "vacuum"};

  private static String[] description = {
      "This is very nice product.",
      "Everyone want to have it.",
      "The product is of very high quality.",
      "It is recommended for adults.",
      "Easy to use.",
      "It is new generation.",
      "It will change your life.",
      "Recommended by lots of bloggers.",
      "You can return it within 30 days.",
      "It has replaceable parts.",
      "Has a 2-year warranty."
  };

}
