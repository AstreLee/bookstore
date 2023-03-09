package com.xin.bookstore.controller;

import com.xin.bookstore.entity.common.Book;
import com.xin.bookstore.entity.request.BookEditFormVO;
import com.xin.bookstore.entity.request.RegularAddFormVO;
import com.xin.bookstore.entity.response.BookInfoVO;
import com.xin.bookstore.entity.response.ResultVO;
import com.xin.bookstore.entity.response.SelectionVO;
import com.xin.bookstore.service.BookService;
import com.xin.bookstore.service.CategoryService;
import com.xin.bookstore.utils.SnowFlake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author : joylxer
 * @date : 2022/11/24 - 16:48
 * @file : Book.java
 * @ide : IntelliJ IDEA
 */
@Slf4j
@RestController
@Api(tags =  "图书管理")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation("获取图书列表")
    @GetMapping("/home/book")
    public ResultVO getBookList() {
        // 获取所有图书封装在List集合中
        List<Book> list = bookService.getBookList();
        // 返回结果
        return new ResultVO(200, "获取图书列表成功！", list);
    }

    @ApiOperation("获取所有出版社")
    @GetMapping("/home/book/publisher")
    public ResultVO getAllPublisher() {
        // 获取所有的出版社封装在List集合中返回给前端
        List<SelectionVO> res = bookService.getAllPublisher();
        return new ResultVO(200, "获取出版社信息成功！", res);
    }

    @ApiOperation("商品ID、价格、出版社三个条件联查")
    @GetMapping("/home/book/{categoryId}/{price}/{publisher}")
    public ResultVO searchByCategory_price_publisher(@PathVariable String categoryId,
                                       @PathVariable String price,
                                       @PathVariable String publisher) {
        List<Book> res = bookService.getAllByThree(categoryId, price, publisher);
        return new ResultVO(200, "获取图书信息成功！", res);
    }

    @ApiOperation("图书图片上传")
    @PostMapping("/book/upload")
    public Map<String, Object> photoUpload(MultipartFile file) throws IOException {
        ApplicationHome home = new ApplicationHome(getClass());
        File jarFile = home.getSource();
        String basePath = jarFile.getParent();
        //获取上传的文件的文件名
        String fileName = file.getOriginalFilename();
        // 处理文件重名问题
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + hzName;
        // 获取服务器中photo目录的路径
        String photoPath = basePath + File.separator + "static";
        File file1 = new File(photoPath);
        if (!file1.exists()) {
            if (file1.mkdir()) {
                System.out.println("文件夹创建成功！");
            } else {
                System.out.println("文件夹创建失败！");
            }
        }
        String finalPath = photoPath + File.separator + fileName;
        //实现上传功能
        file.transferTo(new File(finalPath));
        // 封装返回参数
        List<Map<String, Object>> lists = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("url", "/static/" + fileName);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", fileName);
        map1.put("url", "http://localhost:8888/api/v1/static/" + fileName);
        lists.add(map1);
        map.put("fileList", lists);
        // 返回结果
        return map;
    }

    @ApiOperation("获取所有图书分类")
    @GetMapping("/book/category")
    public ResultVO getBookAllCategory() {
        // 定义返回结果
        List<SelectionVO> res = categoryService.getAllCategory();
        // 返回结果
        return new ResultVO(200, "获取分类信息成功！", res);
    }

    @ApiOperation("添加图书")
    @PostMapping("/book")
    public ResultVO addBook(@RequestBody RegularAddFormVO vo) {
        Integer count = bookService.saveOne(vo);
        if (count == 0) {
            return new ResultVO(204, "添加书籍实体失败！");
        } else {
            return new ResultVO(201, "添加书籍实体成功！");

        }
    }

    @ApiOperation("获取所有图书信息")
    @GetMapping("/book/info")
    public ResultVO getBookInfo() {
        List<BookInfoVO> res = bookService.getBookInfo();
        return new ResultVO(200, "获取图书列表成功！", res);
    }

    @ApiOperation("根据图书ID删除图书")
    @DeleteMapping("/book/{id}")
    public ResultVO deleteBookById(@PathVariable Long id) {
        Integer count = bookService.removeByBookId(id);
        if (count == 0) {
            return new ResultVO(204, "删除书籍失败！");
        } else {
            return new ResultVO(203, "删除书籍成功！");
        }
    }

    @ApiOperation("根据图书ID获取图书信息(编辑表单)")
    @GetMapping("/book/{id}")
    public ResultVO getByBookId(@PathVariable Long id) {
        Book book = bookService.getByBookId(id);
        return new ResultVO(200, "获取图书信息成功！", book);
    }

    @ApiOperation("修改图书信息")
    @PutMapping("book")
    public ResultVO updateByBookId(@RequestBody BookEditFormVO vo) {
        Integer count = bookService.updateByBookId(vo);
        if (count == 0) {
            return new ResultVO(204, "修改图书失败！");
        } else {
            return new ResultVO(202, "修改图书成功！");
        }
    }
}