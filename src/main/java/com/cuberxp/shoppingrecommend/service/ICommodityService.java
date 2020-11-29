package com.cuberxp.shoppingrecommend.service;

import com.cuberxp.shoppingrecommend.domain.Commodity;

import java.util.List;

/**
 * @author cuberxp
 * @date 2020/11/29 14:54
 * @since 1.0.0
 */
public interface ICommodityService {

    /**
     * 获取商品数据通过商品ids
     * @param ids 商品id集合
     * @return 商品数据
     */
    List<Commodity> findCommodityByIds(List<Long> ids);

}
