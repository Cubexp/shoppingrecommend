package com.cuberxp.shoppingrecommend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cuberxp.shoppingrecommend.dao.ICommodityMapper;
import com.cuberxp.shoppingrecommend.domain.Commodity;
import com.cuberxp.shoppingrecommend.service.ICommodityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cuberxp
 * @date 2020/11/29 14:55
 * @since 1.0.0
 */
@Service
@AllArgsConstructor
public class CommodityServiceImpl implements ICommodityService {

    private final ICommodityMapper ICommodityMapper;

    @Override
    public List<Commodity> findCommodityByIds(List<Long> ids) {
        LambdaQueryWrapper<Commodity> commodityWrapper = Wrappers.lambdaQuery();
        commodityWrapper.in(Commodity::getPid, ids);

        return ICommodityMapper.selectList(commodityWrapper);
    }
}
