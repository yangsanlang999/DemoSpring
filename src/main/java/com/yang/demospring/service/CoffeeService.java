package com.yang.demospring.service;

import com.yang.demospring.entity.Coffee;

import java.util.List;
import java.util.Optional;

public interface CoffeeService {

    List<Coffee> findAllCoffee();

    Optional<Coffee> findOneCoffee(String name);

//    List<Coffee> testCache();
//
//    void reloadCoffee();
}
