package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductCategoryDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductCategoryDaoImpl implements ProductCategoryDao {
    @Autowired
    private SqlSession session;
    private String namespace = "com.fastcampus.toy2.dao.Product.ProductCategoryMapper.";

    @Override
    public List<ProductCategoryDto> selectList() throws Exception {
        return List.of();
    }

    @Override
    public ProductCategoryDto selectById(Integer id) throws Exception {
        return session.selectOne(namespace + "selectById", id);
    }

    @Override
    public List<ProductCategoryDto> selectUpperEquals(Integer upper_category_id) throws Exception {
        return session.selectList(namespace + "selectUpperEquals", upper_category_id);
    }
}
