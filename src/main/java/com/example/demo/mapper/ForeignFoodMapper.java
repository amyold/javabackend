package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ChineseFoods;
import com.example.demo.entity.ForeignFoods;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface ForeignFoodMapper extends BaseMapper<ForeignFoods> {
}
