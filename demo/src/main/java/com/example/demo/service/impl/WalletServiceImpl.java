package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.Wallet;
import com.example.demo.mapper.WalletMapper;
import com.example.demo.service.WalletService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 钱包表 服务实现类
 * </p>
 *
 * @author WangYufan
 * @since 2023-04-04
 */
@Service
public class WalletServiceImpl extends ServiceImpl<WalletMapper, Wallet> implements WalletService {




    @Override
    public Wallet getWalletByUserId(String id) {
        QueryWrapper<Wallet> walletQueryWrapper = new QueryWrapper<>();
        walletQueryWrapper.eq("user_id",id);
        Wallet wallet = baseMapper.selectOne(walletQueryWrapper);
        return wallet;
    }
}
