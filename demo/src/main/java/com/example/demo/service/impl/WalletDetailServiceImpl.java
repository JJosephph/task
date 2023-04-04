package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.WalletDetail;
import com.example.demo.mapper.WalletDetailMapper;
import com.example.demo.service.WalletDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 钱包变动明细表 服务实现类
 * </p>
 *
 * @author WangYufan
 * @since 2023-04-04
 */
@Service
public class WalletDetailServiceImpl extends ServiceImpl<WalletDetailMapper, WalletDetail> implements WalletDetailService {

    @Override
    public List<WalletDetail> queryWalletDetail(Long id) {
        QueryWrapper<WalletDetail> walletDetailQueryWrapper = new QueryWrapper<>();
        walletDetailQueryWrapper.eq("user_id",id);
        List<WalletDetail> walletDetails = baseMapper.selectList(walletDetailQueryWrapper);
        return walletDetails;
    }
}
