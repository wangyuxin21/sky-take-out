package com.sky.controller.user;

import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Slf4j
@Api("店铺相关接口")
public class ShopController {
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping("/status")
    @ApiOperation("查询营业状态")
    public Result<Integer> getStatus(){
        String shopStatusStr = (String) redisTemplate.opsForValue().get("SHOP_STATUS");
        Integer shopStatus = shopStatusStr != null ? Integer.parseInt(shopStatusStr) : null;
        log.info("查询营业状态:{}",shopStatus == 1 ? "营业中" : "打烊中");
        return Result.success(shopStatus);
    }

}