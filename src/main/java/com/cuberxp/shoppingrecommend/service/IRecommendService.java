package com.cuberxp.shoppingrecommend.service;

import com.cuberxp.shoppingrecommend.domain.Commodity;
import org.apache.mahout.cf.taste.common.TasteException;

import java.util.List;

/**
 * 推荐服务
 * @author cuberxp
 * @date 2020/11/28 21:43
 * @since 1.0.0
 */
public interface IRecommendService {
    /**
     * 基于用户的商品推荐
     * @param userId 用户id
     * @param count 推荐数量
     * @return 推荐的商品
     * @throws TasteException Taste引擎内部发生错误时引发的异常
     */
    List<Commodity> getRecommendCommodityByUser(Long userId, int count) throws TasteException;

    /**
     * 基于内容的商品推荐
     * @param userId 用户id
     * @param commodityId 商品id
     * @param count 推荐数量
     * @return 推荐的商品
     * @throws TasteException Taste引擎内部发生错误时引发的异常
     */
    List<Commodity> getRecommendCommodityByCommodity(Long userId, Long commodityId, int count) throws TasteException;
}
