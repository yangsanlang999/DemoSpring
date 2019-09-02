package com.yang.demospring.service.impl;

import com.yang.demospring.entity.Coffee;
import com.yang.demospring.entity.CoffeeOrder;
import com.yang.demospring.entity.OrderState;
import com.yang.demospring.service.CoffeeOrderService;
import org.springframework.stereotype.Service;

@Service
public class CoffeeOrderServiceImpl implements CoffeeOrderService
{

    @Override
    public CoffeeOrder createOrder(String customer, Coffee... coffee) {
        return null;
    }

    @Override
    public boolean updateState(CoffeeOrder order, OrderState state) {
        return false;
    }
}
