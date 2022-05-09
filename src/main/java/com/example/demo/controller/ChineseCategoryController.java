package com.example.demo.controller;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.*;
import com.example.demo.mapper.ChineseCategoryMapper;
import com.example.demo.mapper.ChineseFoodMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//定义路由
@RequestMapping("/ChineseFood")
@CrossOrigin(origins = "*")
public class ChineseCategoryController {
    @Resource
    ChineseCategoryMapper chineseCategorymapper;
    @Resource
    ChineseFoodMapper chineseFoodMapper;
    @GetMapping("/")
    public Result<?> findcharts() {
        LambdaQueryWrapper<Chinesecategory> wrapper = Wrappers.<Chinesecategory>lambdaQuery();
        List<Chinesecategory> categories=chineseCategorymapper.selectList(wrapper);
//        返回前端的数据
        List<Map<String, String>> newlist = new ArrayList();
//        第一个for循环用来遍历
        for (Chinesecategory good : categories) {
//            第二个for循环用来查询每个菜系对应的数量
            Map<String,String> map=new HashMap<String, String>();
            map.put("name", good.getCategory());
            LambdaQueryWrapper<ChineseFoods> Foodswrapper = Wrappers.<ChineseFoods>lambdaQuery();
            List<ChineseFoods> foodslength=chineseFoodMapper.selectList(Foodswrapper.eq(ChineseFoods::getType,good.getCategory()));
            map.put("value",foodslength.size()+"");
            newlist.add(map);
            }
        return Result.success(newlist);
    }
    @GetMapping("/category")
    public Result<?> findcategory() {
        LambdaQueryWrapper<Chinesecategory> wrapper = Wrappers.<Chinesecategory>lambdaQuery();
        List<Chinesecategory> categories=chineseCategorymapper.selectList(wrapper);
        return Result.success(categories);
    }
    @GetMapping("/detail")
    public Result<?> finddata(@RequestParam(defaultValue = "") String category,@RequestParam(defaultValue = "1") Integer pageNum,@RequestParam(defaultValue = "20") Integer pageSize) {
        System.out.println(pageSize+"pagesize");
        LambdaQueryWrapper<ChineseFoods> wrapper = Wrappers.<ChineseFoods>lambdaQuery();
        if (StrUtil.isNotBlank(category)) {
            wrapper.eq(ChineseFoods::getType,category);
        }
        else {
            Page<ChineseFoods> allFoodsPage = chineseFoodMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
            return Result.success(allFoodsPage);
        }
        Page<ChineseFoods> FoodsPage = chineseFoodMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(FoodsPage);
    }

    @PutMapping ("/changeData")
    public Result<?> changeData(@RequestBody ChineseFoods food) {
        LambdaQueryWrapper<ChineseFoods> wrapper = Wrappers.<ChineseFoods>lambdaQuery();
        wrapper.eq(ChineseFoods::getId,food.getId());
        if (wrapper==null){
            return Result.error("400","不存在该美食");
        }
        chineseFoodMapper.updateById(food);
        ChineseFoods updatedData=chineseFoodMapper.selectOne(wrapper);
        return Result.success(updatedData);
    }
    @PostMapping("/deleteFood")
    public Result<?> deleteData(@RequestBody ChineseFoods food) {
        LambdaQueryWrapper<ChineseFoods> wrapper = Wrappers.<ChineseFoods>lambdaQuery();
        wrapper.eq(ChineseFoods::getId,food.getId());
        if (wrapper==null){
            return Result.error("400","不存在该美食");
        }
        chineseFoodMapper.deleteById(food.getId());
        return Result.success("删除成功");

    };
    @PostMapping("/addChineseFoods")
    public Result<?> addChineseFoods(@RequestBody ChineseFoods food) {
        chineseFoodMapper.insert(food);
        return Result.success(food);
    };
}
