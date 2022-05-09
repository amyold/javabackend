package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("chinese_foods")
@Data
public class ChineseFoods {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String foodname;
    private String components;
    private String score;
    private String imgurl;
    private String type;

}
