package com.cuberxp.shoppingrecommend.service;

import com.cuberxp.shoppingrecommend.domain.UserPreference;

import java.util.List;

/**
 * @author cuberxp
 * @date 2020/11/29 15:14
 * @since 1.0.0
 */
public interface IUserPreferenceService {
    /**
     * 获取所有用户偏好数据
     * @return 用户偏好数据
     */
    List<UserPreference> getAllUserPreference();
}
