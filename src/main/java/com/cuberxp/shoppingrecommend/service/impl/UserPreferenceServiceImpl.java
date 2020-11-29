package com.cuberxp.shoppingrecommend.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.cuberxp.shoppingrecommend.dao.IUserPreferenceMapper;
import com.cuberxp.shoppingrecommend.domain.UserPreference;
import com.cuberxp.shoppingrecommend.service.IUserPreferenceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cuberxp
 * @date 2020/11/29 15:14
 * @since 1.0.0
 */
@Service
@AllArgsConstructor
public class UserPreferenceServiceImpl implements IUserPreferenceService {

    private final IUserPreferenceMapper iUserPreferenceMapper;

    @Override
    public List<UserPreference> getAllUserPreference() {
        return iUserPreferenceMapper.selectList(Wrappers.emptyWrapper());
    }
}
