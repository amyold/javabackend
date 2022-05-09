package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.Result;
import com.example.demo.entity.Goods;
import com.example.demo.mapper.goodsMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@RestController
//定义路由
@RequestMapping("/goods")
@CrossOrigin(origins = "*")
public class GoodsController {
        @Resource
        goodsMapper goodsMapper;
//        根据商品id获取商品
        @GetMapping("/{id}")
        public Result<Goods> findindex(@PathVariable Integer id) {
            LambdaQueryWrapper<Goods> wrapper = Wrappers.<Goods>lambdaQuery();
            wrapper.eq(Goods::getId,id);
            Goods good = goodsMapper.selectOne(wrapper);
            if (good!=null){
                return Result.success(good);
            }
            return Result.error("1","该商品不存在");
        };
        @GetMapping ("/all")
        public Result<?> findallPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {
            LambdaQueryWrapper<Goods> wrapper = Wrappers.<Goods>lambdaQuery();
            if (StrUtil.isNotBlank(search)) {
                wrapper.like(Goods::getWarename, search);
            }
            Page<Goods> goodPage = goodsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
            return Result.success(goodPage);
        };
        @GetMapping("/palace")
        public Result<?> findPalacePage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {
            LambdaQueryWrapper<Goods> wrapper = Wrappers.<Goods>lambdaQuery();
            if (StrUtil.isNotBlank(search)) {
                wrapper.like(Goods::getWarename, search);
            }
            else {
            search="故宫文创";
            wrapper.like(Goods::getSortname, search);
            }
            Page<Goods> goodPage = goodsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
            return Result.success(goodPage);
        };


    @GetMapping("/Tibet")
    public Result<?> findTibetPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Goods> wrapper = Wrappers.<Goods>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Goods::getWarename, search);
        }
        else {
            search="西藏文创";
            wrapper.like(Goods::getSortname, search);
        }
        Page<Goods> goodPage = goodsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(goodPage);
    };
    @GetMapping("/BritishMuseum")
    public Result<?> findBritishMuseumPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Goods> wrapper = Wrappers.<Goods>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Goods::getWarename, search);
        }
        else {
            search="大英博物馆";
            wrapper.like(Goods::getSortname, search);
        }
        Page<Goods> goodPage = goodsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(goodPage);
    };
    @GetMapping("/SuzhouEmbroidery")
    public Result<?> findSuzhouEmbroideryPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Goods> wrapper = Wrappers.<Goods>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Goods::getWarename, search);
        }
        else {
            search="苏绣";
            wrapper.like(Goods::getSortname, search);
        }
        Page<Goods> goodPage = goodsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(goodPage);
    };
    @GetMapping("/SuzhouMuseum")
    public Result<?> findSuzhouMuseumPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Goods> wrapper = Wrappers.<Goods>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            wrapper.like(Goods::getWarename, search);
        }
        else {
            search="苏州博物馆";
            wrapper.like(Goods::getSortname, search);
        }
        Page<Goods> goodPage = goodsMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(goodPage);
    };

    }


