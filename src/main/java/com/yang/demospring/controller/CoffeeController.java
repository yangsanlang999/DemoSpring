package com.yang.demospring.controller;

import com.yang.demospring.entity.Coffee;
import com.yang.demospring.mapper.CoffeeMapper;
import com.yang.demospring.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@ResponseBody
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    private CoffeeMapper coffeeMapper;

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    @Autowired
    private CoffeeService coffeeService;

    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(@Value("${redis.host}") String host) {
        return new JedisPool(jedisPoolConfig(), host);
    }


    @PostMapping("findAll")
    public String findAll()
    {
        log.info(jedisPoolConfig.toString());

        List<Coffee> coffeeList = coffeeMapper.findAll();

        try (Jedis jedis = jedisPool.getResource()) {
            coffeeList.forEach(c -> {
                jedis.hset("springbucks-menu",
                        c.getName(),
                        Long.toString(c.getPrice().getAmountMinorLong()));
            });

            Map<String, String> menu = jedis.hgetAll("springbucks-menu");
            log.info("Menu: {}", menu);

            String price = jedis.hget("springbucks-menu", "espresso");
            log.info("espresso - {}",
                    Money.ofMinor(CurrencyUnit.of("CNY"), Long.parseLong(price)));
        }

        return  coffeeList.toString();
    }


//    @PostMapping("/testCache")
//    public void testCache()
//    {
//        log.info("result={}",coffeeService.testCache());
//        for (int i = 0; i < 3; i++)
//        {
//            log.info("reading from cache");
//            log.info("result={}",coffeeService.testCache());
//        }
//        try
//        {
//            Thread.sleep(5000);
//        }
//        catch (InterruptedException e)
//        {
//            e.printStackTrace();
//        }
//        log.info("result={}",coffeeService.testCache());
//    }

    @PostMapping("findOneCoffee")
    public String findOneCoffee(@RequestParam("name") String name)
    {
        Optional<Coffee> c = coffeeService.findOneCoffee(name);

        return c.toString();
    }

}
