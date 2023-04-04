package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.Wallet;

import java.math.BigDecimal;

/**
 * <p>
 * 钱包表 服务类
 * </p>
 *
 * @author WangYufan
 * @since 2023-04-04
 */
public interface WalletService extends IService<Wallet> {




    Wallet getWalletByUserId(String id);
}
