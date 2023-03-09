package com.xin.bookstore.service.Impl;

import com.xin.bookstore.dao.*;
import com.xin.bookstore.entity.common.*;
import com.xin.bookstore.entity.request.AdminOrderInfoEachItemVO;
import com.xin.bookstore.entity.request.AdminOrderInfoVO;
import com.xin.bookstore.entity.response.CartInfoVO;
import com.xin.bookstore.entity.response.OrderInfoVO;
import com.xin.bookstore.entity.response.OrderItemVO;
import com.xin.bookstore.entity.response.ReceiveInfoVO;
import com.xin.bookstore.service.OrderService;
import com.xin.bookstore.utils.GetUserInfoUtils;
import com.xin.bookstore.utils.SnowFlake;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/12/6 - 19:18
 * @file : OrderServiceImpl.java
 * @ide : IntelliJ IDEA
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private UserCartMapper userCartMapper;
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private RegularUserMapper regularUserMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SnowFlake snow;

    @Override
    public Long postOrder() {
        // 获取用户ID
        Long userId = GetUserInfoUtils.getUserIdRegular();
        // 获取用户对应的购物车ID
        Long cartId = userCartMapper.selectCartIdByUserId(userId);
        // 根据用户ID查询购物车信息(购物车商品总量和总价值)
        CartInfoVO cartInfoVO = userCartMapper.selectNumAndPriceByUserId(userId);
        // 产生订单号
        Long orderId = snow.nextId();
        // 插入数据
        userOrderMapper.insertByUserIdAndOrderId(userId, orderId, cartInfoVO.getNum(), cartInfoVO.getPrice());
        // 然后将购物车中的单项复制到order_item表中
        // 首先当然是需要查出购物车中所有的项目，通过cartId, 返回List集合，泛型为CartItem
        List<CartItem> lists = cartItemMapper.selectAll(cartId);
        // 然后就是将list集合中的所有内容全部添加到订单信息中，主要是书籍ID、数量和总价
        for (CartItem item : lists) {
            orderItemMapper.insertByOrderIdAndBookId(orderId, item.getBookId(), item.getNum(), item.getPrice());
            // 首先查出现有库存量
            Integer num = bookMapper.selectNumByBookId(item.getBookId());
            // 更新库存量为num - item.getNum()
            bookMapper.updateNumByBooId(item.getBookId(), num - item.getNum());
        }
        // 订单信息添加完成后就要清除购物车中的所有项，购物车中的项目需要清除同时user_cart也要清除
        cartItemMapper.deleteAll(cartId);
        userCartMapper.clearCart(userId);
        return orderId;
    }

    @Override
    public OrderInfoVO getOrderInfo(Long orderId) {
        return userOrderMapper.selectOrderInfoByOrderId(orderId);
    }

    @Override
    public List<UserOrder> getAllOrder(Integer state) {
        // 获取用户ID
        Long userId = GetUserInfoUtils.getUserIdRegular();
        List<UserOrder> allOrders = new ArrayList<>();
        // 如果state == 0表示获取所有状态的订单
        if (state == 0) {
            allOrders = userOrderMapper.selectByUserId(userId);
        } else {
            allOrders = userOrderMapper.selectByUserIdAndState(userId, state);
        }
        return allOrders;
    }

    @Override
    public List<OrderItemVO> getAllOrderItem(Long orderId) {
        // 首先获取所有的订单项
        List<OrderItem> lists = orderItemMapper.selectByOrderId(orderId);
        List<OrderItemVO> res = new ArrayList<>();
        // 遍历
        for (OrderItem item : lists) {
            // 通过item.bookId获取图书并查询图书的URL路径以及单价
            Book book = bookMapper.selectOneByBookId(item.getBookId());
            // 组装参数
            OrderItemVO obj = new OrderItemVO();
            obj.setImgUrl(book.getImgUrl());
            obj.setName(book.getName());
            obj.setDesc(book.getDesc());
            obj.setNum(item.getNum());
            obj.setPrice(item.getPrice());
            obj.setRetail(book.getPrice());
            // 添加到列表中
            res.add(obj);
        }
        return res;
    }

    @Override
    public Integer updateOrder(Long orderId, Integer state) {
        if (state == -2) {
            // state状态为-2表示未付款状态下直接取消订单，那么就需要更新我们书籍的库存
            // 首先根据orderId查出所有的订单项
            List<OrderItem> list = orderItemMapper.selectByOrderId(orderId);
            // 遍历
            for (OrderItem item : list) {
                // 查出现有库存
                Integer num = bookMapper.selectNumByBookId(item.getBookId());
                // 更新库存为num + item.getNum()
                bookMapper.updateNumByBooId(item.getBookId(), num + item.getNum());
            }
        }
        return userOrderMapper.updateStateByOrderId(orderId, state);
    }

    @Override
    public ReceiveInfoVO getUserInfo() {
        // 获取用户ID
        Long userId = GetUserInfoUtils.getUserIdRegular();
        // 通过用户ID查询用户信息
        RegularUser user = regularUserMapper.selectByUserId(userId);
        // 创建返回类对象
        ReceiveInfoVO res = new ReceiveInfoVO();
        // Bean复制
        BeanUtils.copyProperties(user, res);
        return res;
    }

    @Override
    public List<AdminOrderInfoVO> getOrderByState(Integer state) {
        List<AdminOrderInfoVO> adminOrderInfoVOList = new ArrayList<>();
        // 首先通过state获取所有订单
        List<UserOrder> userOrderList = userOrderMapper.selectByState(state);
        // 遍历，封装每一个订单
        for (UserOrder item : userOrderList) {
            AdminOrderInfoVO adminOrderInfoVO = new AdminOrderInfoVO();
            BeanUtils.copyProperties(item, adminOrderInfoVO);
            // 获取用户ID，并查询用户名
            String username = regularUserMapper.selectByUserId(item.getUserId()).getUsername();
            adminOrderInfoVO.setUsername(username);
            // 封装图书信息，根据item的orderId查询所有项目
            List<AdminOrderInfoEachItemVO> adminOrderInfoEachItemVOList = new ArrayList<>();
            List<OrderItem> orderItemList = orderItemMapper.selectByOrderId(item.getOrderId());
            // 遍历
            for (OrderItem orderItem : orderItemList) {
                AdminOrderInfoEachItemVO adminOrderInfoEachItemVO = new AdminOrderInfoEachItemVO();
                BeanUtils.copyProperties(orderItem, adminOrderInfoEachItemVO);
                // 获取图书ID查询图书名称，描述，分类类别，出版社
                Book book = bookMapper.selectOneByBookId(orderItem.getBookId());
                // 根据book.getCategoryId()获取到的图书分类ID查询图书分类名称
                String category = categoryMapper.selectByCategoryId(book.getCategoryId()).getName();
                // 封装对象
                adminOrderInfoEachItemVO.setCategory(category);
                adminOrderInfoEachItemVO.setDesc(book.getDesc());
                adminOrderInfoEachItemVO.setName(book.getName());
                adminOrderInfoEachItemVO.setPublisher(book.getPublisher());
                // 添加到列表当中
                adminOrderInfoEachItemVOList.add(adminOrderInfoEachItemVO);
            }
            adminOrderInfoVO.setEachItem(adminOrderInfoEachItemVOList);
            adminOrderInfoVOList.add(adminOrderInfoVO);
        }
        return adminOrderInfoVOList;
    }
}
