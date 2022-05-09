package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ChineseFoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChineseFoodMapper extends BaseMapper<ChineseFoods> {
}
