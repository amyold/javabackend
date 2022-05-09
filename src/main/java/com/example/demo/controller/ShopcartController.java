package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Goods;
import com.example.demo.entity.Shopcart;
import com.example.demo.entity.User;
import com.example.demo.mapper.ShopcartMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.mapper.goodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopcartController {
    @Resource
    UserMapper userMapper;
    @Resource
    goodsMapper goodsMapper;
    @Resource
    ShopcartMapper shopcartMapper;
    @GetMapping("/getShopcart")
    public Result<?> getShopcart(@RequestParam (defaultValue = "") String username ) {
//  获得购物车流程
//        判断用户名是否合法
//        合法的话根据用户名找到所有数据
//        然后根据id返回数据
        LambdaQueryWrapper<Shopcart> wrapper = Wrappers.<Shopcart>lambdaQuery().eq(Shopcart::getUsername,username);
        List<Shopcart> shopcarts=shopcartMapper.selectList(wrapper);
        System.out.println(shopcarts);
        List<Map<String, Object>> newlist = new ArrayList();
        for(Shopcart s:shopcarts){
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("shopcart",s);
            Integer goodId=s.getGoodId();
            LambdaQueryWrapper<Goods> wrapperGoods = Wrappers.<Goods>lambdaQuery().eq(Goods::getId,goodId);
            Goods good = goodsMapper.selectOne(wrapperGoods);
            map.put("good",good);
            if (good==null){
                return Result.error("0","商品不存在");
            }
            newlist.add(map);
        }
        return Result.success(newlist);
    };
    @PostMapping("/addToShopcart")
    public Result<?> addToShopcart(@RequestBody Shopcart shopcart) {
        User user = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername,shopcart.getUsername()));
        if (user == null) {
            return Result.error("1", "该用户不存在");
        };
        Goods good= goodsMapper.selectOne(Wrappers.<Goods>lambdaQuery().eq(Goods::getId,shopcart.getGoodId()));
        if (good==null){
            return Result.error("1", "该商品不存在");
        }
        Shopcart res=shopcartMapper.selectOne(Wrappers.<Shopcart>lambdaQuery().eq(Shopcart::getUsername,shopcart.getUsername()).eq(Shopcart::getGoodId,shopcart.getGoodId()));
        if (res!=null){
            return Result.error("1","该数据已存在");
        }
        shopcartMapper.insert(shopcart);
        return Result.success();
    };
    @PostMapping("/deleteShopcart")
    public Result<?> deletShopcart(@RequestBody Shopcart shopcart) {
        Shopcart res=shopcartMapper.selectOne(Wrappers.<Shopcart>lambdaQuery().eq(Shopcart::getUsername,shopcart.getUsername()).eq(Shopcart::getGoodId,shopcart.getGoodId()));
        if (res==null){
            return Result.error("1","该数据不存在");
        }
        System.out.println();
        shopcartMapper.deleteById(res.getId());
        return Result.success();
    };

}
