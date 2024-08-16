package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductKindDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ProductKindDaoImpl implements ProductKindDao {
    @Autowired
    private SqlSession sqlSession;
    private static String namespace = "com.fastcampus.toy2.dao.Product.ProductKindMapper.";

    @Override
    public int count() throws Exception {
        return sqlSession.selectOne(namespace + "count");
    }

    @Override
    public ProductKindDto selectById(String style_num) throws Exception {
        return sqlSession.selectOne(namespace + "selectById", style_num);
    }

    @Override
    public List<ProductKindDto> selectListByProductId(String product_id) throws Exception {
        return sqlSession.selectList(namespace + "selectByProductId", product_id);
    }

    @Override
    public List<ProductKindDto> selectAll() throws Exception {
        return sqlSession.selectList(namespace + "selectAll");
    }

    @Override
    public int insert(ProductKindDto productKindDto) throws Exception {
        return sqlSession.insert(namespace + "insert", productKindDto);
    }

    @Override
    public int insertAll(List<ProductKindDto> productKindDtos) throws Exception {
        int size = productKindDtos.size();
        for (int i = 0; i < size; i++) {
            int result = insert(productKindDtos.get(i));
            if(result == 0) {
                return 0;
            }
        }
        return 1;
    }

    @Override
    public int update(ProductKindDto productKindDto) throws Exception {
        return sqlSession.update(namespace + "update", productKindDto);
    }

    @Override
    public int updateSaleCount(String style_num, char reason) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("style_num", style_num);
        map.put("reason", reason);
        return sqlSession.update(namespace + "updateSaleCount", map);
    }

    @Override
    public int delete(ProductKindDto productKindDto) throws Exception {
        return sqlSession.delete(namespace + "delete", productKindDto);
    }

    @Override
    public int deleteAll() throws Exception {
        return sqlSession.delete(namespace + "deleteAll");
    }
}
