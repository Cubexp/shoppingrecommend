package com.cuberxp.shoppingrecommend.controller;

import com.cuberxp.shoppingrecommend.domain.Commodity;
import com.cuberxp.shoppingrecommend.service.IRecommendService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.apache.mahout.cf.taste.common.TasteException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author cuberxp
 * @date 2020/11/29 16:01
 * @since 1.0.0
 */
@Api(tags = "推荐商品")
@RestController
@AllArgsConstructor
@RequestMapping("recommend")
public class RecommendController {

    private final IRecommendService iRecommendService;

    @ApiOperation(value = "基于用户的推荐")
    @GetMapping("user")
    public List<Commodity> getRecommendCommodityByUser(@RequestParam("userId") Long userId,
                                                       @RequestParam("count") Integer count) throws TasteException {
        return iRecommendService.getRecommendCommodityByUser(userId, count);
    }

    @ApiOperation(value = "基于商品的推荐")
    @GetMapping("commodity")
    public List<Commodity> getRecommendCommodityByCommodity(@RequestParam("userId") Long userId,
                                                            @RequestParam("commodityId") Long commodityId,
                                                            @RequestParam("count") Integer count) throws TasteException {
        return iRecommendService.getRecommendCommodityByCommodity(userId, commodityId, count);
    }
}
