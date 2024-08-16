package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductStockDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class ProductStockDaoImpl implements ProductStockDao {
    @Autowired
    private SqlSession session;
    private String namespace = "com.fastcampus.toy2.dao.Product.ProductStockMapper.";

    @Override
    public int changeStock(String style_num, int stock) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("style_num", style_num);
        map.put("p_stock", stock);  // XML에서 사용하는 키로 변경
        return session.update(namespace + "changeStock", map);
    }

    @Override
    public List<ProductStockDto> getStockList(String style_num) throws Exception {
        System.out.println("ProductStockDaoImpl called()");
        return session.selectList(namespace + "selectByStyleNum", style_num);
    }
}
