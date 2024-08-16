package com.fastcampus.toy2.dao.Product;


import com.fastcampus.toy2.domain.Product.ProductColorDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ProductColorDaoImplTest {

    /**
     * 1. 데이터베이스 연결 테스트 - O
     * 2. 데이터 입력 테스트 - O
     * 3. 데이터 입력 후 수정 테스트
     * 4. 데이터 NotNull 속성 테스트 - O
     * 5. 데이터 입력후 아이디로 찾기 테스트
     * 6. 없는 product_id로 조회 테스트
     * 7. 데이터 여러 개 넣고 전체 조회 테스트
     * 8. 페이징 테스트 - 아직 진행x
     */

    @Autowired
    private ProductColorDao productColorDao;

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
     * 데이터를 입력하는 것을 확인한다.
     * 테이블 전체 데이터 삭제 > 데이터 개수 0개인지 확인
     * 데이터 입력 시도
     * insert 결과 확인 > 데이터 개수 1개인지 확인
     * select로 집어넣은 데이터 갖고오기. > 데이터 비교.
     */
    @Test
    public void 데이터_입력_테스트() throws Exception {
        productColorDao.deleteAll();
        assertEquals(productColorDao.count(), 0);
        // 1. 입력
        /**
         * 더미 데이터 productColorDto를 생성.
         */
        ProductColorDto productColorDto = new ProductColorDto.Builder()
                .color_code("C01")
                .english_color("Black")
                .korean_color("검정")
                .created_id("등록자1")
                .updated_id("등록자1")
                .build();

        // 2. 처리
        /**
         * 1. 더미 데이터를 insert 시도
         * 2. 실행 결과를 resultinsert에 저장
         */
        int resultinsert = productColorDao.insert(productColorDto);
        // 3. 확인
        /**
         * 1. resultinsert의 결과가 1인지 확인.
         * 2. productColor 테이블의 로우 수가 1개인지 확인.
         * 3. 확인을 위해 checkProductColorDto를 생성하여 color_code 입력.
         * 4. 컬러 코드를 사용하여 select문 실행
         * 5. 찾은 데이터와 입력한 데이터를 비교.
         */

        assertEquals(resultinsert,1);
        assertEquals(productColorDao.count(), 1);

        ProductColorDto checkProductColorDto = new ProductColorDto.Builder()
                .color_code("C01")
                .build();
        checkProductColorDto = productColorDao.selectByColorCode(checkProductColorDto.getColor_code());

        assertEquals(productColorDto, checkProductColorDto);
    }

    /**
     * 데이터 안에 Not Null 속성을 가진 컬럼에 null 데이터를 입력했을 때, 데이터가 제대로 들어가지 않는지 확인.
     */
    @Test(expected = DataIntegrityViolationException.class)
    public void 데이터_유효성_검사() throws Exception {
        productColorDao.deleteAll();
        assertEquals(productColorDao.count(), 0);
        // 1. 입력
        /**
         * color 명칭을 null로 입력한 productColorDto를 생성.
         */
        ProductColorDto productColorDto = new ProductColorDto.Builder()
                .created_id("등록자1")
                .updated_id("등록자1")
                .build();
        // 2. 처리
        // 3. 확인
        /**
         * 1. insert 시도
         * 2. SQLIntegrityConstraintViolationException 발생하는지 확인.
         */
        int resultinsert = productColorDao.insert(productColorDto);
    }

    @Test
    public void 다수의_데이터_입력_후_전체_조회() throws Exception {
        productColorDao.deleteAll();
        assertEquals(productColorDao.count(), 0);
        //1. 입력
        ProductColorDto[] productColorDtos = new ProductColorDto[100];
        for (int i = 0; i < 10; i++) {
            productColorDtos[i] = new ProductColorDto.Builder()
                    .color_code("C0" + i)
                    .english_color("Black" + i)
                    .korean_color("검정" + i)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
        }
        for(int i = 10; i < 100; i++) {
            productColorDtos[i] = new ProductColorDto.Builder()
                    .color_code("C" + i)
                    .english_color("Black" + i)
                    .korean_color("검정" + i)
                    .created_id("등록자" + i)
                    .updated_id("등록자" + i)
                    .build();
        }
        // 입력
        /**
         * 1. productColorDtos를 전부 입력
         */

    }
}
