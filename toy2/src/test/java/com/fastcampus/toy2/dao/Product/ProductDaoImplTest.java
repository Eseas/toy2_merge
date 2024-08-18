package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductDaoImplTest {
    /**
     * 1. 데이터베이스 연결 테스트 - O
     * 2. 데이터 입력 테스트 - O
     * 3. 데이터 입력 후 수정 테스트 - O
     * 4. 데이터 NotNull 속성 테스트 - O
     * 5. 데이터 입력후 아이디로 찾기 테스트 - O
     * 6. 없는 product_id로 조회 테스트 - O
     * 7. 데이터 여러 개 넣고 전체 조회 테스트 - O
     * 8. 판매 중인 상품만 전체 조회해보기. - O
     * 9. 성별 검색 테스트
     * 10.카테고리 별 검색 테스트
     * --------------------------------------
     * 테스트
     * 동일한 테이터 입력해보기.
     * 길이 안 맞는 거
     * 페이징 테스트 - 아직 진행x
     *
     */

    @Autowired
    private ProductDao productDao;

    @Autowired
    DataSource ds;

    @Test
    public void 데이터베이스_연결_테스트() throws Exception {
        try {
            Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.
            System.out.println("conn = " + conn);
            assertTrue(conn != null);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            //Access denied for user 'root'@'localhost' (using password: YES) : password 틀릴 때
            //Access denied for user 'roo'@'localhost' (using password: YES) : username 틀릴 때
            //Unknown database 'toy3' : 스키마 이름 틀릴 때
        }
    }

    /**
     * 데이터가 제대로 입력되는가?
     * @throws Exception
     */
    @Test
    public void 데이터_입력_테스트() throws Exception {
        // 1. 입력
        /**
         * 1. 데이터 전체 삭제
         * 2. 데이터 개수 확인
         * 3. insert 10개 입력.
         * 3-1. insert할 때마다 넣은 데이터를 다시 조회하여 equals 비교
         */
        productDao.deleteAll();
        int count = productDao.count();
        assertTrue(count == 0);
        for (int i = 0; i < 10; i++) {
            ProductDto productDto = new ProductDto.Builder()
                    .product_id("7K2321" + i)
                    .p_name("물품" + i)
                    .p_gender("C")
                    .p_origin_price(i*10000)
                    .p_sale_price(i*10000)
                    .p_discount_per(0)
                    .member_benefit_price(i + 10000)
                    .p_average_grade(2.5f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .main_image_url(""+i)
                    .p_season("A")
                    .category_id(i)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
            productDao.insert(productDto);

            ProductDto checkproductDto = productDao.selectById(productDto.getProduct_id());
            assertEquals(productDto, checkproductDto);
        }
        // 2. 처리
        // 3. 확인
        /**
         * 1. 데이터 개수 10개인지 확인
         */

        assertEquals(productDao.count(), 10);
    }

    /**
     * 데이터 베이스 설정 상 Not Null인 컬럼에 Null으로 insert하려고 할 경우 어떻게 되는지 확인
     */
    @Test(expected = DataIntegrityViolationException.class)
    public void 데이터_NotNull_테스트() throws Exception {
        /**
         * ### Error updating database.  Cause: java.sql.SQLIntegrityConstraintViolationException: Column 'product_id' cannot be null
         * ### The error may exist in file [C:\Usersuser\Documents\java\toy2\toy2\target\classes\mapper\Product\ProductMapper.xml]
         * ### The error may involve com.fastcampus.toy2.dao.Product.ProductMapper.insert-Inline
         * ### The error occurred while setting parameters
         * ### SQL: INSERT INTO product         (             product_id,             p_name,             p_gender,             p_origin_price,             p_sale_price,             p_discount_per,             member_benefit_price,             p_average_grade,             p_brief_text,             materials_care_methods,             sale_state,             main_image_url,             p_season,             category_id,             created_id,             updated_id         )         VALUES         (             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?,             ?         )
         * ### Cause: java.sql.SQLIntegrityConstraintViolationException: Column 'product_id' cannot be null
         * ; ]; Column 'product_id' cannot be null; nested exception is java.sql.SQLIntegrityConstraintViolationException: Column 'product_id' cannot be null
         */
        // 1. 입력
        /**
         *  1. 곳곳에 NULL이 저장된 productDto 저장
         */
        productDao.deleteAll();
        assertEquals(productDao.count(), 0);
        ProductDto productDto = new ProductDto.Builder()
                .p_gender("C")
                .p_origin_price(10000)
                .p_discount_per(0)
                .member_benefit_price(10000)
                .p_average_grade(2.5f)
                .materials_care_methods("신발갑피1폴리에스터100%")
                .sale_state("Y")
                .main_image_url("이미지1")
                .p_season("A")
                .category_id(123)
                .build();
        System.out.println(productDto.toString());
        // 2. 처리
        /**
         * ! 에러 발생 !
         * 1. insert문 실행
         * 2. Exception 발생
         */
        int result = -1;
        result = productDao.insert(productDto);
        // 3. 확인
        productDao.deleteAll();
    }

    /**
     * 여러 개의 데이터가 있을 때 원하는 데이터를 찾을 수 있는가?
     * @throws Exception
     */
    @Test
    public void 데이터_입력_후_아이디로_찾기_테스트() throws Exception {
        /**
         * 1. 데이터 전체 삭제
         * 2. 데이터 개수 0개인지 확인
         * 3. insert 10개 입력.
         * 4. 데이터를 조회 후 같은지 equals로 비교.
         */
        productDao.deleteAll();
        assertTrue(productDao.count() == 0);
        ProductDto[] insertProductDtos = new ProductDto[10];
        for (int i = 0; i < 10; i++) {
            insertProductDtos[i] = new ProductDto.Builder()
                    .product_id("7K2762" + i)
                    .p_name("여성 CLARA T 24 클라라 트래블" + i)
                    .p_gender("F")
                    .p_origin_price(i*10000)
                    .p_sale_price(i*10000)
                    .p_discount_per(0)
                    .member_benefit_price(i + 10000)
                    .p_average_grade(2.5f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .main_image_url("이미지"+i)
                    .p_season("A")
                    .category_id(i)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
            productDao.insert(insertProductDtos[i]);
            ProductDto checkproductDto = new ProductDto.Builder()
                    .product_id("7K2762" + i)
                    .build();
            checkproductDto = productDao.selectById(checkproductDto.getProduct_id());
            assertEquals(insertProductDtos[i], checkproductDto);
        }
        assertTrue(productDao.count() == 10);
        // 2. 처리
        /**
         *  1. insert한 아이디를 가진 productDto 10개와 새로운 아이디를 가진 productDto 1개인 11개의 객체 배열 생성
         *  2. 10의 productDto는 insert하지 않은 id를 지정.
         */
        ProductDto[] productDtos = new ProductDto[11];
        for(int i = 0; i < 10; i++) {
            productDtos[i] = new ProductDto.Builder()
                    .product_id("7K2762" + i)
                    .build();
        }

        productDtos[10] = new ProductDto.Builder()
                .product_id("7K27632")
                .build();

        // 3. 확인
        /**
         *  1. 0부터 9까지 저장된 productDto를 통해 select문 확인 후 상품 아이디를 확인한다.
         *  1-1. 만약 찾은 아이디가 다르다면 SQLException 오류를 발생시킨다.
         *  1-2. 만약 찾은 데이터가 없다면, 안에 값을 읽으려고 할 때, NullPointerException.
         *  2. insert하지 않은 productDto[10]을 selectOne에 넣고 실행한다.
         *  3. assertNull을 통해 넘어온 데이터가 null인지 확인한다.
         */
        for(int i = 0; i < 10; i++) {
            ProductDto resultProduct = productDao.selectById(productDtos[i].getProduct_id());
//          System.out.println("i : " + i);
//          System.out.println(insertProductDtos[i]);
//          System.out.println(resultProduct.toString());
            if(!insertProductDtos[i].equals(resultProduct)) {
                throw (new Exception("테스트 실패"));
            }
        }
    }

    /**
     * 만약 데이터베이스에 저장되어 있지 않은 데이터로 조회가 일어났을 경우 에러 확인.
     * @throws Exception
     */
    @Test
    public void 없는_아이디로_조회해보기() throws Exception {
        productDao.deleteAll();
        assertTrue(productDao.count() == 0);

        // 1. 입력
        /**
         * 1. 10개의 더미 데이터 입력.
         * 2. insert 결과를 저장할 10개 크기의 int형 배열 선언.
         */
        ProductDto[] insertProductDtos = new ProductDto[10];
        int[] resultinsert = new int[10];
        for (int i = 0; i < 10; i++) {
            insertProductDtos[i] = new ProductDto.Builder()
                    .product_id("7K2762" + i)
                    .p_name("여성 CLARA T 24 클라라 트래블" + i)
                    .p_gender("F")
                    .p_origin_price(i*10000)
                    .p_sale_price(i*10000)
                    .p_discount_per(0)
                    .member_benefit_price(i + 10000)
                    .p_average_grade(2.5f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .main_image_url("이미지"+i)
                    .p_season("A")
                    .category_id(i)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
            // 2. 처리
            resultinsert[i] = productDao.insert(insertProductDtos[i]);

            ProductDto checkproductDto = new ProductDto.Builder()
                    .product_id(insertProductDtos[i].getProduct_id())
                    .build();
            checkproductDto = productDao.selectById(checkproductDto.getProduct_id());
            assertEquals(insertProductDtos[i], checkproductDto);
        }
        // 3. 확인
        /**
         * 1. insert 결과가 전부 1인지 확인
         * 2. 입력되어 있는 데이터가 10개인지 확인
         * 3. 없는 아이디로 select 시도
         * 4. select 결과가 null인지 확인.
         */
        for(int i = 0; i < 10; i++) {
            assertEquals(resultinsert[i],1);
        }
        assertEquals(10, productDao.count());
        ProductDto checkproductDto = new ProductDto.Builder()
                .product_id("7K00000")
                .build();

        ProductDto resultselect = productDao.selectById(checkproductDto.getProduct_id());

        assertNull(resultselect);
    }

    /**
     * 데이터 입력 후 수정 시도 시 제대로 수정되는지 확인한다.
     * @throws Exception
     */
    @Test
    public void 데이터_입력_후_수정_테스트() throws Exception {
        productDao.deleteAll();
        assertTrue(productDao.count() == 0);
        // 1. 입력
        /**
         * 1. 입력할 데이터 productDto 1개 생성.
         * 2. 수정할 데이터 EditProductDto 1개 생성
         * 3. insert 진행 후 insert 변수에 결과 저장.
         * 4. 체크용 productDto 1개 생성 후 product_id를 productDto와 동일하게 저장
         * 5. 체크용 productDto의 아이디를 사용하여 selectByID 실행
         * 6. 체크용 productDto와 productDto가 equal 한지 확인.
         */
        ProductDto productDto = new ProductDto.Builder()
                .product_id("7K27632")
                .p_name("더미데이터1")
                .p_gender("C")
                .p_origin_price(10000)
                .p_sale_price(10000)
                .p_discount_per(0)
                .member_benefit_price(10000)
                .p_average_grade(2.5f)
                .p_brief_text("아웃도어")
                .materials_care_methods("신발갑피1폴리에스터100%")
                .sale_state("Y")
                .main_image_url("이미지1")
                .p_season("A")
                .category_id(123)
                .created_id("등록자1")
                .updated_id("등록자1")
                .build();
        int insert = productDao.insert(productDto);
        ProductDto checkproductDto = new ProductDto.Builder()
                .product_id(productDto.getProduct_id())
                .build();

        checkproductDto = productDao.selectById(checkproductDto.getProduct_id());
        assertEquals(productDto, checkproductDto);

        ProductDto EditProductDto = new ProductDto.Builder()
                .product_id("7K27632")
                .p_name("수정더미데이터")
                .p_gender("F")
                .p_origin_price(50000)
                .p_sale_price(40000)
                .p_discount_per(20)
                .member_benefit_price(40000)
                .p_average_grade(5.0f)
                .p_brief_text("OOTD에 맞는 편안한 라이프")
                .materials_care_methods("창-아웃솔  합성고무")
                .sale_state("Y")
                .main_image_url("수정이미지1")
                .p_season("S")
                .category_id(222)
                .created_id("등록자1")
                .updated_id("수정자10")
                .build();
        // 2. 처리
        /**
         * 1. 기존 데이터에서 수정 데이터로 업데이트 실행
         */
        int update = productDao.update(EditProductDto);
        // 3. 확인
        /**
         *  1. update 결과 1인지 확인
         *  2. insert 결과 1인지 확인
         *  3. 체크용 업데이트 productDto를 생성 후 아이디를 동일하게 지정
         *  4. 체크용 업데이트 productDto의 id를 통해 select문 실행.
         *  5. select를 통해 받아온 checkupdateproductDto와 수정 시도한 EditProductDto가 equals한지 비교
         */
        assertEquals(update, 1);
        assertEquals(insert, 1);
        ProductDto checkupdateproductDto = new ProductDto.Builder()
                .product_id("7K27632")
                .build();
        System.out.println(checkupdateproductDto.getProduct_id());

        checkupdateproductDto = productDao.selectById(checkupdateproductDto.getProduct_id());
        assertEquals(EditProductDto, checkupdateproductDto);
    }

    /**
     * 다수의 데이터를 입력 후 전체 select 문을 사용하여 제대로 조회되는지 확인한다.
     * @throws Exception
     */
    @Test
    public void 테스트_데이터_집어넣기() throws Exception {
        String[] test_gender = {"F", "M", "C"};
        // 1. 입력
        /**
         * 1. 50000개 상품 집어넣어보기.
         */
        productDao.deleteAll();
        assertEquals(productDao.count(), 0);
        int[] resultInsert = new int[100];
        List<ProductDto> resultselect = new ArrayList<ProductDto>();
        for (int i = 0; i < 10; i++) {
            int origin_price = (int) (Math.random() * 100000);
            int discount_per = (int) (Math.random() * 90) + 1;
            ProductDto productDto = new ProductDto.Builder()
                    .product_id("7K0000" + i)
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
                    .main_image_url("image" + ((int) (Math.random() * 10) + 1))
                    .p_season("A")
                    .category_id((int) (Math.random() * 8) + 5)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
            productDao.insert(productDto);
        }

        for (int i = 10; i < 100; i++) {
            int origin_price = (int) (Math.random() * 100000);
            int discount_per = (int) (Math.random() * 90) + 1;
            ProductDto productDto = new ProductDto.Builder()
                    .product_id("7K000" + i)
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
                    .main_image_url("image" + ((int) (Math.random() * 10) + 1))
                    .p_season("A")
                    .category_id((int) (Math.random() * 8) + 5)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();

            /**
             * 1. 데이터 입력한 결과를 int형 배열에 저장
             * 2. 입력했던 아이디값을 가진
             */
            productDao.insert(productDto);
        }

        for (int i = 101; i < 999; i++) {
            int origin_price = (int) (Math.random() * 100000);
            int discount_per = (int) (Math.random() * 90) + 1;
            ProductDto productDto = new ProductDto.Builder()
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
                    .main_image_url("image" + ((int) (Math.random() * 10) + 1))
                    .p_season("A")
                    .category_id((int) (Math.random() * 8) + 5)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
            productDao.insert(productDto);
        }

        for (int i = 1000; i < 10000; i++) {
            int origin_price = (int) (Math.random() * 100000);
            int discount_per = (int) (Math.random() * 90) + 1;
            ProductDto productDto = new ProductDto.Builder()
                    .product_id("7K0" + i)
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
                    .main_image_url("image" + ((int) (Math.random() * 10) + 1))
                    .p_season("A")
                    .category_id((int) (Math.random() * 8) + 5)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
            productDao.insert(productDto);
        }

        for (int i = 10000; i < 50000; i++) {
            int origin_price = (int) (Math.random() * 100000);
            int discount_per = (int) (Math.random() * 90) + 1;
            ProductDto productDto = new ProductDto.Builder()
                    .product_id("7K" + i)
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
                    .main_image_url("image" + ((int) (Math.random() * 10) + 1))
                    .p_season("A")
                    .category_id((int) (Math.random() * 8) + 5)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
            productDao.insert(productDto);
        }
/*
        // 2. 처리
        resultselect = productDao.selectAll();

        // 3. 확인

        for (int i = 0; i < 100; i++) {
            assertEquals(resultInsert[i], 1);
        }

        assertEquals(resultselect.size(), 100);
        */
    }

    /**
     * 판매중이지 않은 상품은 제외하고 조회해보기.
     * @throws Exception
     */

    @Test
    public void 판매_중인_상품만_전체_조회하기() throws Exception{
        productDao.deleteAll();
        assertEquals(productDao.count(), 0);
        // 1. 입력
        /**
         * 1. 10개의 더미데이터 생성.
         * 1-1. 5개는 판매중인 상품으로 지정, 나머지 5개는 판매 중단한 상품으로 지정.
         *      sale_state("Y") vs sale_state("N")
         */
        ProductDto[] productDtos = new ProductDto[10];
        int[] resultInsert = new int[10];
        for (int i = 0; i < 10; i = i + 2) {
            productDtos[i] = new ProductDto.Builder()
                    .product_id("7K2700" + i)
                    .p_name("여성 CLARA T 24 클라라 트래블" + i)
                    .p_gender("F")
                    .p_origin_price(i*10000)
                    .p_sale_price(i*10000)
                    .p_discount_per(0)
                    .member_benefit_price(i + 10000)
                    .p_average_grade(2.5f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .main_image_url("이미지"+i)
                    .p_season("A")
                    .category_id(i)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
            resultInsert[i] = productDao.insert(productDtos[i]);
            ProductDto checkproductDto = new ProductDto.Builder()
                    .product_id(productDtos[i].getProduct_id())
                    .build();
            checkproductDto = productDao.selectById(checkproductDto.getProduct_id());
            assertEquals(productDtos[i], checkproductDto);
        }
        for (int i = 1; i < 10; i = i + 2) {
            productDtos[i] = new ProductDto.Builder()
                    .product_id("7K2700" + i)
                    .p_name("여성 CLARA T 24 클라라 트래블" + i)
                    .p_gender("F")
                    .p_origin_price(i*10000)
                    .p_sale_price(i*10000)
                    .p_discount_per(0)
                    .member_benefit_price(i + 10000)
                    .p_average_grade(2.5f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("N")
                    .main_image_url("이미지"+i)
                    .p_season("A")
                    .category_id(i)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
            resultInsert[i] = productDao.insert(productDtos[i]);
            ProductDto checkproductDto = new ProductDto.Builder()
                    .product_id(productDtos[i].getProduct_id())
                    .build();
            checkproductDto = productDao.selectById(checkproductDto.getProduct_id());
            assertEquals(productDtos[i], checkproductDto);
        }

        // 2. 처리
        List<ProductDto> productDtoList = productDao.selectSale();

        // 3. 확인
        /**
         * 1. insert 결과 전부 1인지 확인
         * 2. 판매중인 상품만 들고온 productDtoList의 길이가 5인지 확인.
         */
        for (int i = 0; i < 10; i++) {
            assertEquals(resultInsert[i],1);
        }
        assertEquals(productDtoList.size(), 5);
    }
/*
    @Test
    public void 성별_검색_테스트() throws Exception {
        productDao.deleteAll();
        assertEquals(productDao.count(), 0);
        // 1. 입력
        /**
         * 1. 10개의 더미데이터 생성.
         * 1-1. 5개는 판매중인 상품으로 지정, 나머지 5개는 판매 중단한 상품으로 지정.
         *      sale_state("Y") vs sale_state("N")

        ProductDto[] productDtos = new ProductDto[15];
        int[] resultInsert = new int[15];
        for (int i = 10; i < 25; i = i + 3) {
            productDtos[i - 10] = new ProductDto.Builder()
                    .product_id("7K270" + i)
                    .p_name("여성 CLARA T 24 클라라 트래블" + i)
                    .p_gender("F")
                    .p_origin_price(i*10000)
                    .p_sale_price(i*10000)
                    .p_discount_per(0)
                    .member_benefit_price(i + 10000)
                    .p_average_grade(2.5f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .main_image_url("이미지"+i)
                    .p_season("A")
                    .category_id(i)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();

            resultInsert[i - 10] = productDao.insert(productDtos[i - 10]);

            ProductDto checkproductDto = new ProductDto.Builder()
                    .product_id(productDtos[i - 10].getProduct_id())
                    .build();
            checkproductDto = productDao.selectOne(checkproductDto.getProduct_id());
            assertEquals(productDtos[i - 10], checkproductDto);

        }
        for (int i = 11; i < 25; i = i + 3) {
            productDtos[i - 10] = new ProductDto.Builder()
                    .product_id("7K270" + i)
                    .p_name("남성 CLARA T 24 클라라 트래블" + i)
                    .p_gender("M")
                    .p_origin_price(i*10000)
                    .p_sale_price(i*10000)
                    .p_discount_per(0)
                    .member_benefit_price(i + 10000)
                    .p_average_grade(2.5f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .main_image_url("이미지"+i)
                    .p_season("A")
                    .category_id(i)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();

            resultInsert[i - 10] = productDao.insert(productDtos[i - 10]);

            ProductDto checkproductDto = new ProductDto.Builder()
                    .product_id(productDtos[i].getProduct_id())
                    .build();
            checkproductDto = productDao.selectOne(checkproductDto.getProduct_id());
            assertEquals(productDtos[i - 10], checkproductDto);
        }
        for (int i = 12; i < 25; i = i + 3) {
            productDtos[i - 10] = new ProductDto.Builder()
                    .product_id("7K270" + i)
                    .p_name("공용 CLARA T 24 클라라 트래블" + i)
                    .p_gender("C")
                    .p_origin_price(i*10000)
                    .p_sale_price(i*10000)
                    .p_discount_per(0)
                    .member_benefit_price(i + 10000)
                    .p_average_grade(2.5f)
                    .p_brief_text("아웃도어" + i)
                    .materials_care_methods("신발갑피1폴리에스터" + i + "%")
                    .sale_state("Y")
                    .main_image_url("이미지"+i)
                    .p_season("A")
                    .category_id(i)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
            resultInsert[i - 10] = productDao.insert(productDtos[i - 10]);
            ProductDto checkproductDto = new ProductDto.Builder()
                    .product_id(productDtos[i - 10].getProduct_id())
                    .build();
            checkproductDto = productDao.selectOne(checkproductDto.getProduct_id());
            assertEquals(productDtos[i - 10], checkproductDto);

        }
    }
    */
}