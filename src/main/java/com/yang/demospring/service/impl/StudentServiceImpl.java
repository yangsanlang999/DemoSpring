package com.yang.demospring.service.impl;

import com.yang.demospring.mapper.StudentMapper;
import com.yang.demospring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService
{
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public String queryAll() {

        List stuList = studentMapper.queryAll();

        return stuList.toString();
    }
}
