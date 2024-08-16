package com.fastcampus.toy2.dao.Order;

import com.fastcampus.toy2.dao.Product.ProductImageDao;
import com.fastcampus.toy2.dao.Product.ProductStockDao;
import com.fastcampus.toy2.domain.Product.ProductImageDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ProductImageDaoTest {

    @Autowired
    ProductImageDao productImageDao;

    @Test
    public void selectMainImageByStyleNum() throws Exception {
        String style_num = "7K17664_K01";
        ProductImageDto image = productImageDao.selectMainImageByStyleNum(style_num);

        System.out.println(image);
    }
}