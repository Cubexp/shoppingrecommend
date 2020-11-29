package com.cuberxp.shoppingrecommend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户偏好表实体类
 * @author cuberxp
 * @date 2020/11/28 21:36
 * @since 1.0.0
 */
@Data
@TableName("user_preference")
public class UserPreference {

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 商品id
     */
    private Long pid;

    /**
     * 偏好值 最大值5
     */
    private Long val;

    private LocalDateTime time;
}
