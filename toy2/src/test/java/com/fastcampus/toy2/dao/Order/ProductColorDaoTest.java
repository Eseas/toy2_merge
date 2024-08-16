package com.fastcampus.toy2.dao.Order;

import com.fastcampus.toy2.dao.Product.ProductColorDao;
import com.fastcampus.toy2.domain.Product.ProductColorDto;
import com.fastcampus.toy2.domain.Product.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class ProductColorDaoTest {

    @Autowired
    ProductColorDao productColorDao;

    @Test
    public void selectByColorCode() throws Exception {
        String style_num = "7K17664_K01";
        String[] product_id_color = style_num.split("_");
        String product_id = product_id_color[0]; // id
        String color_code = product_id_color[1]; // color

        ProductColorDto productColorDto = productColorDao.selectByColorCode(color_code);

        assertTrue(productColorDto.getColor_code().equals(color_code));
    }
}