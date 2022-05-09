package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface goodsMapper extends BaseMapper<Goods> {
}
