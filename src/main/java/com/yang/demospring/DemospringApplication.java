package com.yang.demospring;

import com.yang.demospring.entity.Coffee;
import io.lettuce.core.ReadFrom;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
@SpringBootApplication
@EnableScheduling
@MapperScan("com.yang.demospring.mapper")
@EnableCaching(proxyTargetClass = true)
public class DemospringApplication
{

    @Bean
    public RedisTemplate<String, Coffee> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Coffee> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }

    @Bean
    public LettuceClientConfigurationBuilderCustomizer customizer()
    {
        return builder -> builder.readFrom(ReadFrom.MASTER_PREFERRED);
    }

	public static void main(String[] args)
    {
		SpringApplication.run(DemospringApplication.class, args);
	}

}
