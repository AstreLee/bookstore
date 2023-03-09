package com.xin.bookstore.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xin.bookstore.dao.BookMapper;
import com.xin.bookstore.dao.CategoryMapper;
import com.xin.bookstore.entity.common.Book;
import com.xin.bookstore.entity.request.BookEditFormVO;
import com.xin.bookstore.entity.request.RegularAddFormVO;
import com.xin.bookstore.entity.response.BookInfoVO;
import com.xin.bookstore.entity.response.SelectionVO;
import com.xin.bookstore.service.BookService;
import com.xin.bookstore.utils.SnowFlake;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:33
 * @file : BookServiceImpl.java
 * @ide : IntelliJ IDEA
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SnowFlake snow;


    @Override
    public List<Book> getBookList() {
        return bookMapper.selectBookList();
    }

    @Override
    public List<SelectionVO> getAllPublisher() {
        // 获取所有的出版社封装在List集合中返回给前端
        List<String> lists = bookMapper.selectAllPublisher();
        List<SelectionVO> res = new ArrayList<>();
        res.add(new SelectionVO("所有", "所有"));
        for (String item : lists) {
            res.add(new SelectionVO(item, item));
        }
        return res;
    }

    @Override
    public List<Book> getAllByThree(String categoryId, String price, String publisher) {
        String sql1, sql2, sql3;
        if (categoryId.equals("所有")) {
            sql1 = "1 = 1";
        } else {
            sql1 = "categoryId = " + categoryId;
        }

        if (price.equals("所有")) {
            sql2 = "1 = 1";
        } else {
            String[] arr = price.split("-");
            sql2 = "price >= " + arr[0] + " AND price <= " + arr[1];
        }

        if (publisher.equals("所有")) {
            sql3 = "1 = 1";
        } else {
            sql3 = "publisher = '" + publisher + "'";
        }
        List<Book> res = bookMapper.selectByThree(sql1, sql2, sql3);
        return res;
    }

    @Override
    public Book getByBookId(Long bookId) {
        BookEditFormVO vo = new BookEditFormVO();
        Book book = bookMapper.selectOneByBookId(bookId);
        BeanUtils.copyProperties(book, vo);
        return book;
    }

    @Override
    public Integer saveOne(RegularAddFormVO vo) {
        // 新建立插入数据库的书籍对象
        Book book = new Book();
        // 对象属性复制
        BeanUtils.copyProperties(vo, book);
        // 生成书籍ID
        Long bookId = snow.nextId();
        book.setBookId(bookId);
        // 插入实体
        return bookMapper.insertOne(book.getBookId(), book.getName(), book.getDesc(),
                book.getNum(), book.getPrice(), book.getPublisher(),
                book.getCategoryId(), book.getImgUrl());
    }

    @Override
    public Integer removeByBookId(Long bookId) {
         return bookMapper.deleteByBookId(bookId);
    }

    @Override
    public Integer updateByBookId(BookEditFormVO vo) {
        return bookMapper.updateByBookId(vo.getBookId(), vo.getDesc(), vo.getNum(), vo.getPrice());
    }

    @Override
    public List<BookInfoVO> getBookInfo() {
        // 获取所有图书封装在List集合中
        List<Book> list = bookMapper.selectBookList();
        // 封装结果
        List<BookInfoVO> res = new ArrayList<>();
        // 遍历封装结果
        for (Book book : list) {
            BookInfoVO vo = new BookInfoVO();
            BeanUtils.copyProperties(book, vo);
            // 根据书籍分类ID查询分类名称
            String categoryName = categoryMapper.selectByCategoryId(book.getCategoryId()).getName();
            // 设置名称
            vo.setCategory(categoryName);
            // 添加到列表中
            res.add(vo);
        }
        return res;
    }
}
