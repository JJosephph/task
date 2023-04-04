package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Wallet;
import com.example.demo.service.WalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author WangYufan
 * @since 2023-04-04
 */
@RestController
@RequestMapping("/walletService/user")
@Api(tags = "用户管理")
public class UserController {
    private WalletService walletService;

    @Autowired
    public UserController(WalletService walletService) {
        this.walletService = walletService;
    }

    @ApiOperation(value = "获取用户钱包余额", notes = "获取用户钱包余额")
    @GetMapping("/balance/{id}")
    public ResponseEntity<Map<String, Object>> getUserBalance(@PathVariable String id) {
        Map<String, Object> resultMap = new HashMap<>();
        QueryWrapper<Wallet> walletQueryWrapper = new QueryWrapper<>();
        walletQueryWrapper.eq("user_id", id);
        Wallet one = walletService.getOne(walletQueryWrapper);
        resultMap.put("balance", one.getBalance());
        return ResponseEntity.ok(resultMap);
    }
}

