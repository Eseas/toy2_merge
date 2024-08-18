package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductStockDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductStockDaoImplTest {

    @Autowired
    private ProductStockDao productStockDao;

    @Test
    public void 테스트_데이터_집어넣기() throws Exception {
        productStockDao.deleteAll();
        List<ProductStockDto> productStockDtoList = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            ProductStockDto productStockDto = new ProductStockDto.Builder()
                    .style_num("7K00000_C01")
                    .p_size(240 + (i * 5))
                    .p_stock(i)
                    .created_id("등록자1")
                    .updated_id("등록자1")
                    .build();
            productStockDtoList.add(productStockDto);
        }
        for (int i = 0; i < 6; i++) {
            ProductStockDto productStockDto = new ProductStockDto.Builder()
                    .style_num("7K00000_B01")
                    .p_size(240 + (i * 5))
                    .p_stock(i)
                    .created_id("등록자1")
                    .updated_id("등록자1")
                    .build();
            productStockDtoList.add(productStockDto);
        }
        for (int i = 0; i < 6; i++) {
            ProductStockDto productStockDto = new ProductStockDto.Builder()
                    .style_num("7K00000_J08")
                    .p_size(240 + (i * 5))
                    .p_stock(i)
                    .created_id("등록자1")
                    .updated_id("등록자1")
                    .build();
            productStockDtoList.add(productStockDto);
        }
    }
}
