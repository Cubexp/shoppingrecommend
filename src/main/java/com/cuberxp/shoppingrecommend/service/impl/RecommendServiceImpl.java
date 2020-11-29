package com.cuberxp.shoppingrecommend.service.impl;

import com.cuberxp.shoppingrecommend.domain.Commodity;
import com.cuberxp.shoppingrecommend.domain.UserPreference;
import com.cuberxp.shoppingrecommend.service.ICommodityService;
import com.cuberxp.shoppingrecommend.service.IRecommendService;
import com.cuberxp.shoppingrecommend.service.IUserPreferenceService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.model.GenericDataModel;
import org.apache.mahout.cf.taste.impl.model.GenericPreference;
import org.apache.mahout.cf.taste.impl.model.GenericUserPreferenceArray;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cuberxp
 * @date 2020/11/28 22:15
 * @since 1.0.0
 */
@Service
@Slf4j
@AllArgsConstructor
public class RecommendServiceImpl implements IRecommendService {

    private final ICommodityService iCommodityService;
    private final IUserPreferenceService iUserPreferenceService;

    @Override
    public List<Commodity> getRecommendCommodityByUser(Long userId, int count) throws TasteException {
        DataModel dataModel = this.createDataModel();

        // 计算相拟度，相拟度算法很多种，采用基于皮尔逊相关性的相拟度
        UserSimilarity similarity = new PearsonCorrelationSimilarity(dataModel);

        // 计算最近邻域，邻居有两种算法，基于固定数量的邻居和基于相拟度的邻居，这里使用基于固定数量的邻居
        UserNeighborhood userNeighborhood = new NearestNUserNeighborhood(100, similarity, dataModel);

        // 构建推荐器，基于用户的协同过滤推荐
        Recommender recommender = new GenericUserBasedRecommender(dataModel, userNeighborhood, similarity);

        // 根据用户 推荐商品
        List<Long> commodityIds = recommender.recommend(userId, count).stream()
                .map(RecommendedItem::getItemID)
                .collect(Collectors.toList());

        // 防止sql 出错
        return commodityIds.size() > 0 ? iCommodityService.findCommodityByIds(commodityIds) : new ArrayList<>();
    }

    @Override
    public List<Commodity> getRecommendCommodityByCommodity(Long userId, Long itemId, int count) throws TasteException {
        DataModel dataModel = this.createDataModel();

        // 采用基于皮尔逊相关性的相拟度
        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);

        // 基于物品的协同过滤推荐
        GenericItemBasedRecommender genericItemBasedRecommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);

        // 物品推荐相拟度，计算两个物品同时出现的次数，次数越多任务的相拟度越高
        List<Long> commodityIds = genericItemBasedRecommender.recommendedBecause(userId, itemId, count)
                .stream()
                .map(RecommendedItem::getItemID)
                .collect(Collectors.toList());

        return commodityIds.size() > 0 ? iCommodityService.findCommodityByIds(commodityIds) : new ArrayList<>();
    }

    private DataModel createDataModel() {
        FastByIDMap<PreferenceArray> fastByIdMap = new FastByIDMap<>();

        // 这里可以通过sql 查询简化下
        iUserPreferenceService.getAllUserPreference().stream()
                .collect(Collectors.groupingBy(UserPreference::getUid))
                .values()
                .stream()
                .map(userPreferences -> userPreferences.stream()
                        .map(item -> new GenericPreference(item.getUid(), item.getPid(), item.getVal()))
                        .toArray(GenericPreference[]::new)
                )
                .forEach(genericArrayPreference -> fastByIdMap.put(genericArrayPreference[0].getUserID(), new GenericUserPreferenceArray(Arrays.asList(genericArrayPreference))));

        return new GenericDataModel(fastByIdMap);
    }
}
