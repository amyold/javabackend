package com.example.demo.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("shopcart")
@Data
public class Shopcart {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private Integer goodId;
    private Integer num;
}
