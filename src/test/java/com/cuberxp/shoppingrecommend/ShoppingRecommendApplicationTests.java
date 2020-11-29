package com.cuberxp.shoppingrecommend;

import com.cuberxp.shoppingrecommend.dao.ICommodityMapper;
import com.cuberxp.shoppingrecommend.service.ICommodityService;
import com.cuberxp.shoppingrecommend.service.IRecommendService;
import com.cuberxp.shoppingrecommend.service.IUserPreferenceService;
import org.apache.mahout.cf.taste.common.TasteException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShoppingRecommendApplicationTests {

    @Autowired
    private ICommodityMapper ICommodityMapper;

    @Autowired
    private ICommodityService iCommodityService;

    @Autowired
    private IUserPreferenceService iUserPreferenceService;

    @Autowired
    private IRecommendService iRecommendService;

    @Test
    void contextLoads() {
    }

    @Test
    void testCommodityMapper() {
        System.out.println(ICommodityMapper.selectById(1));
    }

    @Test
    void testCommodityList() {
        System.out.println(iUserPreferenceService.getAllUserPreference().size());
    }

    @Test
    void testRecommendService() throws TasteException {

    }
}
