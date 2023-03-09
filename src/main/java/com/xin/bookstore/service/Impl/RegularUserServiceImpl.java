package com.xin.bookstore.service.Impl;

import com.xin.bookstore.dao.RegularUserMapper;
import com.xin.bookstore.dao.UserCartMapper;
import com.xin.bookstore.entity.common.RegularUser;
import com.xin.bookstore.entity.common.UserCart;
import com.xin.bookstore.entity.request.RegularUserRegisterVO;
import com.xin.bookstore.entity.response.RegularInfoVO;
import com.xin.bookstore.service.RegularUserService;
import com.xin.bookstore.utils.SnowFlake;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : joylxer
 * @date : 2022/11/22 - 19:02
 * @file : RegularUserServiceImpl.java
 * @ide : IntelliJ IDEA
 */
@Service
public class RegularUserServiceImpl implements RegularUserService {
    @Autowired
    private RegularUserMapper regularUserMapper;
    @Autowired
    private SnowFlake snow;
    @Autowired
    private UserCartMapper userCartMapper;

    @Override
    public Integer removeByUserId(Long userId) {
        regularUserMapper.deleteByUserId(userId);
        regularUserMapper.deleteRoleByUserId(userId);
        return 1;
    }

    @Override
    public Map<String, Object> getAllUserInfo(Integer pageNum, Integer pageSize) {
        Integer totalCount = regularUserMapper.selectList(null).size();
        // 选出所有的管理员用户
        Integer left = (pageNum - 1) * pageSize;
        Integer right = pageSize;
        List<RegularUser> lists = regularUserMapper.selectByLimit(left, right);
        List<RegularInfoVO> res = new ArrayList<>();
        for (RegularUser item : lists) {
            RegularInfoVO obj = new RegularInfoVO();
            BeanUtils.copyProperties(item, obj);
            // 拼接地址
            StringBuilder sb = new StringBuilder();
            sb.append(item.getProvince()).append(" ").append(item.getCity()).append(" ").append(item.getRegion());
            obj.setAddress(String.valueOf(sb));
            // 设置状态
            if (item.getStatus().equals("NORMAL")) {
                obj.setState(true);
            } else {
                obj.setState(false);
            }
            res.add(obj);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("total", totalCount);
        map.put("userlist", res);
        return map;
    }

    @Override
    public Integer putRegularInfo(Long id, String state) {
        if (state.equals("false")) {
            return regularUserMapper.updateStatusByUserId(id, "PROHIBIT");
        } else {
            return regularUserMapper.updateStatusByUserId(id, "NORMAL");
        }
    }

    @Override
    public Integer registerUser(RegularUserRegisterVO vo) {
        RegularUser regularUser = new RegularUser();
        BeanUtils.copyProperties(vo, regularUser);
        regularUser.setStatus("NORMAL");
        // 密码需要加密后再进行存储
        String passwd = new BCryptPasswordEncoder().encode(regularUser.getPassword());
        regularUser.setPassword(passwd);
        // 用户ID自己设置
        Long userId = snow.nextId();
        regularUser.setUserId(userId);
        // 保存用户
        regularUserMapper.insert(regularUser);

        // 根据用户ID创建对应的购物车实体
        UserCart userCart = new UserCart();
        userCart.setUserId(userId);
        userCart.setCartId(snow.nextId());
        userCart.setNum(0);
        userCart.setPrice((double) 0);
        // 注册的同时就要创建购物车
        userCartMapper.insert(userCart);
        // 由于普通用户没有什么角色，所以直接插入roleId为1001的角色权限
        return regularUserMapper.insertRoleByUserId(userId);
    }
}
