package com.fastcampus.toy2.dao.Order;

import com.fastcampus.toy2.dao.Product.ProductDao;
import com.fastcampus.toy2.domain.Product.ProductDto;
import com.fastcampus.toy2.service.Order.CartService;
import com.fastcampus.toy2.service.Order.CartServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ProductDaoTest {

    @Autowired
    ProductDao productDao;
    
    @Autowired
    CartService cartService;

    @Test
    public void selectById() throws Exception {
        String style_num = "7K17664_H01";
        String product_id = style_num.split("_")[0];
        System.out.println("product_id = " + product_id);
        ProductDto productDto = productDao.selectById(product_id);
        System.out.println("productDto = " + productDto);
        
        assertTrue(productDto.getProduct_id().equals(product_id));

    }

    @Test
    public void test() throws Exception {
        System.out.println("cartService = " + cartService);
    }
}