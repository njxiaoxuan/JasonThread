package com.jason.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jason.bean.Blog;
import com.jason.dao.BlogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: JasonSpringMybatis
 * @description
 * @author: 大龄程序猿
 * @create: 2020-05-26 23:52
 **/
@Service
public class BlogService {

    @Resource
    private BlogMapper blogMapper;

    public List<Blog> queryByPage() {
        PageHelper.startPage(1, 10); //pageNumber, pageSize，第几页，每页几条
        List<Blog> emps = blogMapper.selectByMap(null);
        PageInfo page = new PageInfo(emps, 10);
        return null;
    }
}
