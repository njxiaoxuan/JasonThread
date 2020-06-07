package com.jason.daosupport;
import com.jason.bean.Blog;
import com.jason.dao.BlogMapper;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: qingshan
 */

@Repository
public class BlogDaoImpl extends BaseDao implements BlogMapper {

    /**
     * 暂时只实现了这一个方法
     * @param empId
     * @return
     */
    @Override
    public Blog selectByPrimaryKey(Integer empId) {
        Blog emp = (Blog) this.selectOne("com.jason.dao.BlogMapper.selectByPrimaryKey",empId);
        return emp;
    }

    @Override
    public int deleteByPrimaryKey(Integer empId) {
        return 0;
    }

    @Override
    public int insert(Blog record) {
        return 0;
    }



    @Override
    public int insertSelective(Blog record) {
        return 0;
    }


    public int batchInsert(List<Blog> list) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Blog record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Blog record) {
        return 0;
    }

    @Override
    public List<Blog> selectByMap(HashMap<String, Object> map) {
        return (List<Blog>)this.selectMap("com.jason.dao.BlogMapper.selectByMap",null);
    }


    public void deleteByList(List<Integer> ids) {

    }
}
