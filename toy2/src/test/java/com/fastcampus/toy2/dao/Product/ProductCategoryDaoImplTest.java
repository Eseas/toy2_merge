package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductCategoryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductCategoryDaoImplTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void 테스트_데이터_집어넣기() throws Exception {
        productCategoryDao.deleteAll();
        List<ProductCategoryDto> productCategoryDtoList = new ArrayList<ProductCategoryDto>();

        productCategoryDtoList.add(new ProductCategoryDto.Builder()
                .category_id(1)
                .category("신발")
                .using_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build()
        );
        productCategoryDtoList.add(new ProductCategoryDto.Builder()
                .category_id(2)
                .category("남성")
                .upper_category_id(1)
                .using_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build()
        );
        productCategoryDtoList.add(new ProductCategoryDto.Builder()
                .category_id(3)
                .category("여성")
                .upper_category_id(1)
                .using_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build()
        );
        productCategoryDtoList.add(new ProductCategoryDto.Builder()
                .category_id(4)
                .category("트래킹")
                .upper_category_id(2)
                .using_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build()
        );
        productCategoryDtoList.add(new ProductCategoryDto.Builder()
                .category_id(5)
                .category("하이킹")
                .upper_category_id(2)
                .using_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build()
        );
        productCategoryDtoList.add(new ProductCategoryDto.Builder()
                .category_id(6)
                .category("워킹")
                .upper_category_id(2)
                .using_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build()
        );
        productCategoryDtoList.add(new ProductCategoryDto.Builder()
                .category_id(7)
                .category("시즌화")
                .upper_category_id(2)
                .using_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build()
        );

        productCategoryDtoList.add(new ProductCategoryDto.Builder()
                .category_id(8)
                .category("트래킹")
                .upper_category_id(3)
                .using_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build()
        );
        productCategoryDtoList.add(new ProductCategoryDto.Builder()
                .category_id(9)
                .category("하이킹")
                .upper_category_id(3)
                .using_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build()
        );
        productCategoryDtoList.add(new ProductCategoryDto.Builder()
                .category_id(10)
                .category("워킹")
                .upper_category_id(3)
                .using_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build()
        );
        productCategoryDtoList.add(new ProductCategoryDto.Builder()
                .category_id(11)
                .category("시즌화")
                .upper_category_id(3)
                .using_state("Y")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build()
        );

        for (int i = 0; i < productCategoryDtoList.size(); i++) {
            productCategoryDao.insertProductCategory(productCategoryDtoList.get(i));
        }
    }
}
