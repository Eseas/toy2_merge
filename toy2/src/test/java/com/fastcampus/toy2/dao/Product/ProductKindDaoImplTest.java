package com.fastcampus.toy2.dao.Product;


import com.fastcampus.toy2.domain.Product.ProductKindDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductKindDaoImplTest {
    @Autowired
    private ProductKindDao productKindDao;

    @Test
    public void 테스트_데이터_집어넣기() throws Exception {
        List<ProductKindDto> productKindDtoList = new ArrayList<ProductKindDto>();

        productKindDtoList.add(new ProductKindDto.Builder()
                .style_num("7K00000_C01")
                .product_id("7K00000")
                .color_code("B01")
                .sale_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build());
        productKindDtoList.add(new ProductKindDto.Builder()
                .style_num("7K00000_B01")
                .product_id("7K00000")
                .color_code("B01")
                .sale_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build());
        productKindDtoList.add(new ProductKindDto.Builder()
                .style_num("7K00000_J08")
                .product_id("7K00000")
                .color_code("J08")
                .sale_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build());

        for (ProductKindDto productKindDto : productKindDtoList) {
            productKindDao.insert(productKindDto);
        }
    }
}
