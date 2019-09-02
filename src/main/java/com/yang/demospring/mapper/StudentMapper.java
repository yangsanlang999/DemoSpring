package com.yang.demospring.mapper;

import com.yang.demospring.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {

    List<Student> queryAll();

}
