package com.yang.demospring.service;

import com.yang.demospring.entity.Coffee;
import com.yang.demospring.entity.CoffeeOrder;
import com.yang.demospring.entity.OrderState;

public interface CoffeeOrderService {

    CoffeeOrder createOrder(String customer, Coffee...coffee);

    boolean updateState(CoffeeOrder order, OrderState state);
}
