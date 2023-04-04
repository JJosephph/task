package com.example.demo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.WalletDetail;

import java.util.List;

/**
 * <p>
 * 钱包变动明细表 服务类
 * </p>
 *
 * @author WangYufan
 * @since 2023-04-04
 */
public interface WalletDetailService extends IService<WalletDetail> {

    List<WalletDetail> queryWalletDetail(Long id);
}
