package com.fastcampus.toy2.Service.Product;

import com.fastcampus.toy2.domain.Product.ProductDto;
import com.fastcampus.toy2.domain.Product.ProductKindDto;
import com.fastcampus.toy2.domain.Product.ProductStockDto;
import com.fastcampus.toy2.service.Product.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    static int PAGE = 1;
    static int PAGE_SIZE = 12;

    @Test
    public void 상품_찾기_페이지_테스트() throws Exception {
        // productService.deleteAll();
        /*
        List<ProductDto> productDtos = new ArrayList<>();
        for (int i = 101; i < 999; i++) {
            int origin_price = (int) (Math.random() * 100000);
            int discount_per = (int) (Math.random() * 90) + 1;
            productDtos.add(new ProductDto.Builder()
                    .product_id("7K00" + i)
                    .p_name("공용 CLARA T 24 클라라 트래블" + i)
                    .p_gender("C")
                    .p_origin_price(origin_price)
                    .p_sale_price((int) (origin_price * (1 - discount_per / 100.0f)))
                    .p_discount_per(discount_per)
                    .member_benefit_price((int) (origin_price * (1 - discount_per / 100.0f)))
                    .p_average_grade((int) (Math.random() * 50) * 0.1f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .sale_count((long) (Math.random() * 10000))
                    .main_image_url("이미지" + ((int) (Math.random() * 10) + 1))
                    .p_season("A")
                    .category_id((int) (Math.random() * 8) + 5)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build());
            productDao.insert(productDto);
        }
        */
        int sort = 2;
        List<Integer> categories = productService.getCategories(3);
        List<ProductDto> productDtos = productService.findProductCategoryPage(categories, sort, PAGE, PAGE_SIZE);
        int count = productService.findProductCategoryPageCount(categories);

        for (int i = 0; i < productDtos.size(); i++) {
            System.out.println("productDtos[" + i + "] = " + productDtos.get(i));
        }
        assertTrue(count <= PAGE_SIZE);
        assertEquals(productDtos.size(), count);
    }

    @Test
    public void 하위_카테고리들_찾기_테스트() throws Exception {
        /**
         * 신발
         *  ┣ 남성용
         *  │ ┣ 트래킹
         *  │ ┣ 하이킹
         *  │ ┣ 워킹
         *  │ └ 샌들/슬라이드
         *  └ 여성용
         *    ┣ 트래킹
         *    ┣ 하이킹
         *    ┣ 워킹
         *    └ 샌들/슬라이드
         *  전체 - 11개
         *  남성용 5개
         *  여성용 5개
         *  남성용 > 트래킹 1개
         */
        // 그룹핑. 스트림.
        List<Integer> Allcategories = productService.getCategories(1);
        assertEquals(Allcategories.size(), 11);
        List<Integer> categories = productService.getCategories(3);
        assertEquals(categories.size(), 5);
        List<Integer> categories2 = productService.getCategories(4);
        assertEquals(categories2.size(), 5);
        List<Integer> categories3 = productService.getCategories(6);
        assertEquals(categories3.size(), 1);
    }

    @Test
    public void 상품_판매수량_정렬_테스트() throws Exception {
        // productService.deleteAll();
        /*
        List<ProductDto> productDtos = new ArrayList<>();
        for (int i = 101; i < 999; i++) {
            int origin_price = (int) (Math.random() * 100000);
            int discount_per = (int) (Math.random() * 90) + 1;
            productDtos.add(new ProductDto.Builder()
                    .product_id("7K00" + i)
                    .p_name("공용 CLARA T 24 클라라 트래블" + i)
                    .p_gender("C")
                    .p_origin_price(origin_price)
                    .p_sale_price((int) (origin_price * (1 - discount_per / 100.0f)))
                    .p_discount_per(discount_per)
                    .member_benefit_price((int) (origin_price * (1 - discount_per / 100.0f)))
                    .p_average_grade((int) (Math.random() * 50) * 0.1f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .sale_count((long) (Math.random() * 10000))
                    .main_image_url("이미지" + ((int) (Math.random() * 10) + 1))
                    .p_season("A")
                    .category_id((int) (Math.random() * 8) + 5)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build());
            productDao.insert(productDto);
        }
        */
        /**
         * 앞에 오는 productDto가 뒤에 오는 productDto보다 sale_count가 높은지 확인
         * sort 기준 = 2 : 판매 수 높은 순
         */
        int sort = 2;
        List<Integer> categories = productService.getCategories(4);
        List<ProductDto> productDtos = productService.findProductCategoryPage(categories, sort, PAGE, PAGE_SIZE);
        int count = productService.findProductCategoryPageCount(categories);

        for (int i = 0; i < productDtos.size(); i++) {
            System.out.println(productDtos.get(i));
        }
        for (int i = 0; i < productDtos.size() - 1; i++) {
            assertTrue(productDtos.get(i).getSale_count() >= productDtos.get(i+1).getSale_count());
        }
    }

    @Test
    public void 상품_낮은_가격_정렬_테스트() throws Exception {
        // productService.deleteAll();
        /*
        List<ProductDto> productDtos = new ArrayList<>();
        for (int i = 101; i < 999; i++) {
            int origin_price = (int) (Math.random() * 100000);
            int discount_per = (int) (Math.random() * 90) + 1;
            productDtos.add(new ProductDto.Builder()
                    .product_id("7K00" + i)
                    .p_name("공용 CLARA T 24 클라라 트래블" + i)
                    .p_gender("C")
                    .p_origin_price(origin_price)
                    .p_sale_price((int) (origin_price * (1 - discount_per / 100.0f)))
                    .p_discount_per(discount_per)
                    .member_benefit_price((int) (origin_price * (1 - discount_per / 100.0f)))
                    .p_average_grade((int) (Math.random() * 50) * 0.1f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .sale_count((long) (Math.random() * 10000))
                    .main_image_url("이미지" + ((int) (Math.random() * 10) + 1))
                    .p_season("A")
                    .category_id((int) (Math.random() * 8) + 5)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build());
            productDao.insert(productDto);
        }
        */
        /**
         * 앞에 오는 product_Dto가 뒤에 오는 productDto보다 sale_price가 낮은지 확인
         * sort 기준 = 4 : 낮은 가격 순
         */
        int sort = 4;
        List<Integer> categories = productService.getCategories(3);
        List<ProductDto> productDtos = productService.findProductCategoryPage(categories, sort, PAGE, PAGE_SIZE);
        int count = productService.findProductCategoryPageCount(categories);

        System.out.println(count);
        for (int i = 0; i < productDtos.size() - 1; i++) {
            assertTrue(productDtos.get(i).getP_sale_price() <= productDtos.get(i+1).getP_sale_price());
        }
    }

    @Test
    public void 상품_높은_가격_정렬_테스트() throws Exception {
        // productService.deleteAll();
        /*
        List<ProductDto> productDtos = new ArrayList<>();
        for (int i = 101; i < 999; i++) {
            int origin_price = (int) (Math.random() * 100000);
            int discount_per = (int) (Math.random() * 90) + 1;
            productDtos.add(new ProductDto.Builder()
                    .product_id("7K00" + i)
                    .p_name("공용 CLARA T 24 클라라 트래블" + i)
                    .p_gender("C")
                    .p_origin_price(origin_price)
                    .p_sale_price((int) (origin_price * (1 - discount_per / 100.0f)))
                    .p_discount_per(discount_per)
                    .member_benefit_price((int) (origin_price * (1 - discount_per / 100.0f)))
                    .p_average_grade((int) (Math.random() * 50) * 0.1f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .sale_count((long) (Math.random() * 10000))
                    .main_image_url("이미지" + ((int) (Math.random() * 10) + 1))
                    .p_season("A")
                    .category_id((int) (Math.random() * 8) + 5)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build());
            productDao.insert(productDto);
        }
        */
        /**
         * 앞에 오는 product_Dto가 뒤에 오는 productDto보다 sale_price가 높은지 확인
         * sort 기준 = 5 : 높은 가격 순
         */
        int sort = 5;
        List<Integer> categories = productService.getCategories(3);
        List<ProductDto> productDtos = productService.findProductCategoryPage(categories, sort, PAGE, PAGE_SIZE);
        int count = productService.findProductCategoryPageCount(categories);

        System.out.println(count);
        for (int i = 0; i < productDtos.size() - 1; i++) {
            assertTrue(productDtos.get(i).getP_sale_price() >= productDtos.get(i+1).getP_sale_price());
        }
    }

    @Test
    public void 상품_색상_종류_사이즈_찾기_테스트() throws Exception {
        // productService.deleteAll();
        /*
        List<ProductDto> productDtos = new ArrayList<>();
        for (int i = 101; i < 999; i++) {
            int origin_price = (int) (Math.random() * 100000);
            int discount_per = (int) (Math.random() * 90) + 1;
            productDtos.add(new ProductDto.Builder()
                    .product_id("7K00" + i)
                    .p_name("공용 CLARA T 24 클라라 트래블" + i)
                    .p_gender("C")
                    .p_origin_price(origin_price)
                    .p_sale_price((int) (origin_price * (1 - discount_per / 100.0f)))
                    .p_discount_per(discount_per)
                    .member_benefit_price((int) (origin_price * (1 - discount_per / 100.0f)))
                    .p_average_grade((int) (Math.random() * 50) * 0.1f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .sale_count((long) (Math.random() * 10000))
                    .main_image_url("이미지" + ((int) (Math.random() * 10) + 1))
                    .p_season("A")
                    .category_id((int) (Math.random() * 8) + 5)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build());
            productDao.insert(productDto);
        }
        */
        List<ProductKindDto> productKindDtos = productService.getProductKindList("7K00000");
        assertEquals(productKindDtos.size(), 3);

        for (int i = 0; i < productKindDtos.size(); i++) {
            List<ProductStockDto> productSizeList = productService.getProductStockList(productKindDtos.get(i).getProduct_id());
            assertFalse(productSizeList.isEmpty());
            for (int j = 0; j < productSizeList.size(); j++) {
                System.out.println("productSizeList" + j + " = " + productSizeList.get(j).getP_size());
            }
        }
    }

    @Test
    public void 상품_디테일_진입_상품_정보_가져오기_테스트() throws Exception {
        ProductKindDto productKindDto = (ProductKindDto) productService.getProductKindList("7K00000");
        assertNotNull(productKindDto.getProduct_id());
    }
}
