package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;



@TableName("goods")
@Data
public class Goods {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String imageurl;
    private String warename;
    private String sortname;
    private Integer price;
}
