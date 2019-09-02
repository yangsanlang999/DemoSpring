package com.yang.demospring.mapper;

import com.yang.demospring.entity.Coffee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CoffeeMapper {

    List<Coffee> findAll();

    Optional<Coffee> findByName();
}
