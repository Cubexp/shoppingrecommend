package com.cuberxp.shoppingrecommend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 商品表实体类
 * @author cuberxp
 * @date 2020/11/28 21:34
 * @since 1.0.0
 */
@Data
@TableName("commodity")
public class Commodity {
    /**
     * 商品id
     */
    @TableId(type = IdType.AUTO)
    private Long pid;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品类型
     */
    private String types;
}
