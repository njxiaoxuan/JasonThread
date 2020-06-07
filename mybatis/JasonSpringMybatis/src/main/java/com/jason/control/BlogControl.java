package com.jason.control;

/**
 * @program: JasonSpringMybatis
 * @description
 * @author: 大龄程序猿
 * @create: 2020-05-27 00:12
 **/

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jason.bean.Blog;
import com.jason.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogControl {

    @Autowired
    public BlogService blogService;

    /**
     * 查询员工数据 分页
     *
     * @param pn
     * @param model
     * @return
     * @RequestMapping("/emps")
     */
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
        PageHelper.startPage(pn, 10);
        List<Blog> emps = blogService.queryByPage();
        PageInfo page = new PageInfo(emps, 10);
        //连续显示的页数是10页
        //包装查出来的结果，只需要将pageInfo交给页面，封装了详细的分页信息
        //包括查询出来的数据
        model.addAttribute("pageInfo", page);

        return "list";
    }

}
