package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.demo.entity.User;
import com.example.demo.entity.Wallet;
import com.example.demo.entity.WalletDetail;
import com.example.demo.service.UserService;
import com.example.demo.service.WalletDetailService;
import com.example.demo.service.WalletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 * 钱包表 前端控制器
 * </p>
 *
 * @author WangYufan
 * @since 2023-04-04
 */
@RestController
@RequestMapping("/walletService/wallet")
@Api(tags = "钱包管理")
public class WalletController {
    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;
    @Autowired
    private WalletDetailService walletDetailService;

    @ApiOperation(value = "用户消费100元", notes = "用户只能消费100元")
    @PostMapping("/consume/{id}")
    public ResponseEntity<Void> consume(@PathVariable String id) {
        BigDecimal amount = new BigDecimal(100);


        User user = userService.getById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        Wallet wallet = walletService.getWalletByUserId(id);
        BigDecimal balance = wallet.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new RuntimeException("余额不足");
        }
        wallet.setBalance(balance.subtract(amount));
        if (!walletService.updateById(wallet)) {
            throw new RuntimeException("扣款失败");
        }
        //给明细表添加消费记录
        WalletDetail walletDetail = new WalletDetail();
        walletDetail.setUserId(id);
        walletDetail.setWalletId(wallet.getId());
        walletDetail.setAmount( new BigDecimal(-100));
        walletDetail.setType("定额消费");
        walletDetailService.save(walletDetail);
        return ResponseEntity.ok().build();
    }


    @ApiOperation(value = "用户退款", notes = "用户退款")
    @PostMapping("/refund/{id}")
    public ResponseEntity<?> refund(@PathVariable String id) {
        BigDecimal redundNum = new BigDecimal(20);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        Wallet wallet = walletService.getWalletByUserId(id);
        wrapper.eq("id", id);
        User user = userService.getOne(wrapper);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        wallet.setBalance(wallet.getBalance().add(redundNum));
        if (!walletService.updateById(wallet)) {
            throw new RuntimeException("退款失败");
        }
        //给明细表添加退款记录
        WalletDetail refundDetail = new WalletDetail();
        refundDetail.setUserId(id);
        refundDetail.setWalletId(wallet.getId());
        refundDetail.setAmount( new BigDecimal(20));
        refundDetail.setType("定额退款");
        walletDetailService.save(refundDetail);
        return ResponseEntity.ok().build();
    }
}


