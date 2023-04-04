package com.example.demo.controller;


import com.example.demo.entity.WalletDetail;
import com.example.demo.service.WalletDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 钱包变动明细表 前端控制器
 * </p>
 *
 * @author WangYufan
 * @since 2023-04-04
 */
@RestController
@RequestMapping("/walletService/walletDetail")
@Api(tags = "钱包明细管理")
public class WalletDetailController {
    @Autowired
    private WalletDetailService walletDetailService;



    @ApiOperation(value = "获取用户钱包金额变动明细", notes = "获取用户钱包金额变动明细")
    @GetMapping("/{id}")
    public ResponseEntity<List<WalletDetail>> queryWalletDetail(@PathVariable Long id) {
        List<WalletDetail> detailList = walletDetailService.queryWalletDetail(id);
        return ResponseEntity.ok(detailList);
    }
}


