package com.yang.demospring.controller;

import com.yang.demospring.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@ResponseBody
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("queryAll")
    public String queryAll()
    {
        return studentService.queryAll();
    }
}
