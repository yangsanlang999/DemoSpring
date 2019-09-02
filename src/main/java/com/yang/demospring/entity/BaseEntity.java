package com.yang.demospring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {

    private Long id;

    private Date createTime;

    private Date updateTime;
}
