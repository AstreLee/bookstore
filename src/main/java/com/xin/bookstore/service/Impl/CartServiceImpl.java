package com.xin.bookstore.service.Impl;

import com.xin.bookstore.dao.BookMapper;
import com.xin.bookstore.dao.CartItemMapper;
import com.xin.bookstore.dao.UserCartMapper;
import com.xin.bookstore.entity.common.Book;
import com.xin.bookstore.entity.common.CartItem;
import com.xin.bookstore.entity.response.CartInfoVO;
import com.xin.bookstore.entity.response.CartItemVO;
import com.xin.bookstore.service.CartService;
import com.xin.bookstore.utils.GetUserInfoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/12/6 - 18:51
 * @file : CartServiceImpl.java
 * @ide : IntelliJ IDEA
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserCartMapper userCartMapper;
    @Autowired
    private CartItemMapper cartItemMapper;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Integer addCartItem(Long bookId) {
        Long userId = GetUserInfoUtils.getUserIdRegular();
        // 根据用户ID从user_cart表中查出该用户所拥有的购物车ID
        Long cartId = userCartMapper.selectCartIdByUserId(userId);
        // 根据购物车ID和书籍ID查询cart_item表中是否有这一项
        CartItem item = cartItemMapper.selectByCartIdAndBookId(cartId, bookId);
        // 查询单价
        Double price = bookMapper.selectPriceByBookId(bookId);
        // 如果item为空表示该用户购物车中没有这一项目，则直接添加
        // 否则的话就修改这一项目的num
        if (item == null) {
            cartItemMapper.insertByCartIdAndBookId(cartId, bookId, price);
            // 更新购物车商品总量以及总价值
            userCartMapper.updateNumAndPriceByUserId(userId, 1, price);
        } else {
            // 查询书籍总库存量
            int totalNum = bookMapper.selectNumByBookId(bookId);
            // 查询购物车中添加的书籍数量currentNum
            int currentNum = cartItemMapper.selectNumByCartIdAndBookId(cartId, bookId);
            // 比较目标书籍量和总库存量
            if (currentNum >= totalNum)
                return 0;
            // 否则的话更新单项商品
            cartItemMapper.updateByCartIdAndBookId(cartId, bookId, currentNum + 1, (currentNum + 1) * price);
            // 更新购物车商品总量以及总价值
            userCartMapper.updateNumAndPriceByUserId(userId, 1, price);
        }
        return 1;
    }

    @Override
    public Integer updateCartItem(Long bookId, Integer targetNum) {
        // 获取用户ID
        Long userId = GetUserInfoUtils.getUserIdRegular();
        // 获取购物车ID
        Long cartId = userCartMapper.selectCartIdByUserId(userId);
        // 查询书籍单价
        Double price = bookMapper.selectPriceByBookId(bookId);
        // 查询修改前该项图书数量
        Integer beforeNum = cartItemMapper.selectNumByCartIdAndBookId(cartId, bookId);
        // 查询书籍总库存量
        int totalNum = bookMapper.selectNumByBookId(bookId);
        if (targetNum > totalNum)
            return 0;
        // 修改购物车单项
        cartItemMapper.updateByCartIdAndBookId(cartId, bookId, targetNum, targetNum * price);
        // 修改总购物车订单
        return userCartMapper.updateNumAndPriceByUserId(userId, targetNum - beforeNum, (targetNum - beforeNum) * price);
    }

    @Override
    public CartInfoVO getNumAndPrice() {
        Long userId = GetUserInfoUtils.getUserIdRegular();
        return userCartMapper.selectNumAndPriceByUserId(userId);
    }

    @Override
    public Integer deleteCartItem(Long bookId) {
        // 获取用户ID
        Long userId = GetUserInfoUtils.getUserIdRegular();
        // 获取购物车ID
        Long cartId = userCartMapper.selectCartIdByUserId(userId);
        // 获取当前书籍数量和总金额
        Integer currentNum = cartItemMapper.selectNumByCartIdAndBookId(cartId, bookId);
        Double price = bookMapper.selectPriceByBookId(bookId);
        // 删除购物车单项
        cartItemMapper.deleteByCartIdAndBookId(cartId, bookId);
        // 修改购物车总商品量和总金额
        return userCartMapper.updateNumAndPriceByUserId(userId, -currentNum, -currentNum * price);
    }

    @Override
    public List<CartItemVO> getAllCartItem() {
        // 获取用户ID
        Long userId = GetUserInfoUtils.getUserIdRegular();
        // 通过用户ID获取购物车ID
        Long cartId = userCartMapper.selectCartIdByUserId(userId);
        // 通过cartId获取当前用户所有购物车项目
        List<CartItem> lists = cartItemMapper.selectAll(cartId);
        // 封装返回类对象
        List<CartItemVO> res = new ArrayList<>();
        // 遍历封装对象
        for (CartItem item : lists) {
            // 根据item.bookId查询书籍名称和描述和零售价
            Book book = bookMapper.selectOneByBookId(item.getBookId());
            String name = book.getName();
            String desc = book.getDesc();
            Double retail = book.getPrice();
            String imgUrl = book.getImgUrl();
            res.add(new CartItemVO(item.getBookId(), name, desc, retail, item.getNum(), item.getPrice(), imgUrl));
        }
        return res;
    }
}
