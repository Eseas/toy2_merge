package com.fastcampus.toy2.dao.Order;


import com.fastcampus.toy2.dao.Product.ProductStockDao;
import com.fastcampus.toy2.domain.Product.ProductStockDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ProductStockDaoTest {
    
    @Autowired
    ProductStockDao productStockDao;
    
    @Test
    public void selectByStyleNum() throws Exception {
        String product_id = "7K17664";
        product_id += "%";
        System.out.println("product_id = " + product_id);
        List<ProductStockDto>  productStockDtoList = productStockDao.selectByProductId(product_id);
        System.out.println("productStockDtoList = " + productStockDtoList.size());
    }
}