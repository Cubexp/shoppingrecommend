package com.cuberxp.shoppingrecommend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuberxp.shoppingrecommend.domain.Commodity;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cuberxp
 * @date 2020/11/29 14:05
 * @since 1.0.0
 */
@Repository
public interface ICommodityMapper extends BaseMapper<Commodity> {

    /**
     * 查询所有商品数据
     * @return 所有商品数据
     */
    @SelectProvider(type = CommoditySql.class, method = "findAllByIds")
    List<Commodity> findAllByIds();

    final class CommoditySql {
        public String findAllByIds() {
            return new SQL() {{
                SELECT("c.pid", "c.name", "c.types");
                FROM("commodity as c");
            }}.toString();
        }
    }
}
