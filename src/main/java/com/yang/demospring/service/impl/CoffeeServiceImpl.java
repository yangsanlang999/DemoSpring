package com.yang.demospring.service.impl;

import com.yang.demospring.entity.Coffee;
import com.yang.demospring.mapper.CoffeeMapper;
import com.yang.demospring.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@CacheConfig(cacheNames = "coffee")
public class CoffeeServiceImpl implements CoffeeService {

    private static final String CACHE = "springbucks-menu";

    @Autowired
    private RedisTemplate<String, Coffee> redisTemplate;

    @Autowired
    private CoffeeMapper coffeeMapper;


    @Override
    public List<Coffee> findAllCoffee() {
        List<Coffee> coffeeList = coffeeMapper.findAll();
        return coffeeList;
    }

    @Override
    public Optional<Coffee> findOneCoffee(String name)
    {
        HashOperations<String, String, Coffee> hashOperations = redisTemplate.opsForHash();
        if (redisTemplate.hasKey(CACHE) && hashOperations.hasKey(CACHE, name))
        {
            log.info("get coffee {} from redis", name);
            return Optional.of(hashOperations.get(CACHE, name));
        }


        return Optional.empty();
    }

//    @Override
//    @Cacheable
//    public List<Coffee> testCache() {
//        List<Coffee> coffeeList = coffeeMapper.findAll();
//        return coffeeList;
//    }
//
//    @Override
//    @CacheEvict
//    public void reloadCoffee() {
//
//    }


}
