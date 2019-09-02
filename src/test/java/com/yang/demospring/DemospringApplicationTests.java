package com.yang.demospring;

import com.yang.demospring.entity.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemospringApplicationTests {

	@Test
	public void contextLoads() {
		log.info("aaaa");
	}

    @Test
    public void optionalTest()
    {
        Optional<Coffee> optional = Optional.ofNullable(Coffee.builder().name("mocha").build());

        log.info("result={}", optional.get());
    }


}
