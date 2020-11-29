package com.cuberxp.shoppingrecommend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cuberxp.shoppingrecommend.domain.UserPreference;
import org.springframework.stereotype.Repository;

/**
 * @author cuberxp
 * @date 2020/11/29 15:11
 * @since 1.0.0
 */
@Repository
public interface IUserPreferenceMapper extends BaseMapper<UserPreference> {
}
