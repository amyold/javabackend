package com.example.demo.controller;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.ChineseFoods;
import com.example.demo.entity.Chinesecategory;
import com.example.demo.entity.ForeignFoods;
import com.example.demo.entity.Foreigncategory;
import com.example.demo.mapper.*;
import com.example.demo.mapper.ForeignCategoryMapper;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//定义路由
@RequestMapping("/ForeignFood")
@CrossOrigin(origins = "*")
public class ForeignCategoryController {
    @Resource
    ForeignCategoryMapper foreignCategoryMapper;
    @Resource
    ForeignFoodMapper foreignFoodMapper;
    @Resource
    ChineseFoodMapper chineseFoodMapper;
    @GetMapping("/")
    public Result<?> findcharts() {
        LambdaQueryWrapper<Foreigncategory> wrapper = Wrappers.<Foreigncategory>lambdaQuery();
        List<Foreigncategory> categories=foreignCategoryMapper.selectList(wrapper);
//        返回前端的数据
        List<Map<String, String>> newlist = new ArrayList();
//        第一个for循环用来遍历
        for (Foreigncategory good : categories) {
//            第二个for循环用来查询每个菜系对应的数量
            Map<String,String> map=new HashMap<String, String>();
            map.put("name", good.getCategory());
            LambdaQueryWrapper<ForeignFoods> Foodswrapper = Wrappers.<ForeignFoods>lambdaQuery();
            List<ForeignFoods> foodslength=foreignFoodMapper.selectList(Foodswrapper.eq(ForeignFoods::getType,good.getCategory()));
            map.put("value",foodslength.size()+"");
            newlist.add(map);
            }
        return Result.success(newlist);}
    @GetMapping("/category")
    public Result<?> findcategory() {
        LambdaQueryWrapper<Foreigncategory> wrapper = Wrappers.<Foreigncategory>lambdaQuery();
        List<Foreigncategory> categories=foreignCategoryMapper.selectList(wrapper);
        return Result.success(categories);
    }
    @GetMapping("/detail")
    public Result<?> finddata(@RequestParam(defaultValue = "") String category,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "20") Integer pageSize) {
        LambdaQueryWrapper<ForeignFoods> wrapper = Wrappers.<ForeignFoods>lambdaQuery();
        if (StrUtil.isNotBlank(category)) {
            wrapper.eq(ForeignFoods::getType,category);
        }
        else {
            Page<ForeignFoods> allFoodsPage = foreignFoodMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
            return Result.success(allFoodsPage);
        }
        Page<ForeignFoods> FoodsPage = foreignFoodMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(FoodsPage);
    }

    @PutMapping ("/changeData")
    public Result<?> changeData(@RequestBody ForeignFoods food) {
        LambdaQueryWrapper<ForeignFoods> wrapper = Wrappers.<ForeignFoods>lambdaQuery();
        wrapper.eq(ForeignFoods::getId,food.getId());
        if (wrapper==null){
            return Result.error("400","不存在该美食");
        }
        foreignFoodMapper.updateById(food);
        ForeignFoods updatedData=foreignFoodMapper.selectOne(wrapper);
        return Result.success(updatedData);
    }
    @PostMapping("/deleteFood")
    public Result<?> deleteData(@RequestBody ForeignFoods food) {
        LambdaQueryWrapper<ForeignFoods> wrapper = Wrappers.<ForeignFoods>lambdaQuery();
        wrapper.eq(ForeignFoods::getId,food.getId());
        if (wrapper==null){
            return Result.error("400","不存在该美食");
        }
        foreignFoodMapper.deleteById(food.getId());
        return Result.success("删除成功");

    };
    @PostMapping("/addForeignFoods")
    public Result<?> addForeignFoods(@RequestBody ForeignFoods food) {
        foreignFoodMapper.insert(food);
        return Result.success(food);
    };
}
