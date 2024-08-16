package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductImageDto;
import com.fastcampus.toy2.domain.Product.ProductKindDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductImageDaoImpl implements ProductImageDao {
    @Autowired
    private SqlSession sqlSession;
    private static String namespace = "com.fastcampus.toy2.dao.Product.ProductImageMapper.";
//    selectMainImageByStyleNum

    @Override
    public ProductImageDto selectMainImageByStyleNum(String style_num) throws Exception {
        return sqlSession.selectOne(namespace + "selectMainImageByStyleNum", style_num);
    }
}
