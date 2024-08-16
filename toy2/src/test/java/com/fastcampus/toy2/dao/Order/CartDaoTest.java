package com.fastcampus.toy2.dao.Order;

import com.fastcampus.toy2.domain.Order.CartDto;
import com.fastcampus.toy2.domain.User.MemberDto;
import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CartDaoTest extends TestCase {
    @Autowired
    CartDao cartDao;

//  테스트 할 때 꼭 확인해둘 사항!!!!
//    1. 성공 CASE
//    2. 실패 CASE
//      2.1. 실패한 경우 발생하는 에러 체크하기

    // 테이블 내용 전부 다 지우는 테스트
    @Test
    public void Cart테이블의_데이터_전체_삭제() throws Exception {
        cartDao.deleteAll();
        int cnt = cartDao.count();
        assertTrue(cnt == 0);
    }

    @Test
    public void 장바구니_1개_Insert() throws Exception {
        cartDao.deleteAll();
        int cnt = cartDao.count();
        assertTrue(cnt == 0);

        // 1. 주문을 장바구니에 담는다.
        // 2. 장바구니 번호는 auto increasement로 자동증가한다.
        MemberDto memberDto = new MemberDto();
        String mbr_id = "asdf123";
        memberDto.setMbr_id(mbr_id);

        CartDto cartDtoInsert = new CartDto.Builder()
                .mbr_id(memberDto.getMbr_id())
                .build();

        cnt = cartDao.insert(cartDtoInsert);
        assertTrue(cnt == 1);

        Long crt_id = cartDtoInsert.getCrt_id();

//      insert 한 값을 select해서 제대로 들어갔는지 확인
        CartDto cartDto2 = cartDao.selectCartId(crt_id);

        System.out.println(cartDto2);

        assertTrue(cartDto2.getCrt_id().equals(crt_id));

    }

    // 1000개 삽입하면서 비교
    @Test
    public void 장바구니_1000개_Insert() throws Exception {
        cartDao.deleteAll();
        int cnt = cartDao.count();
        assertTrue(cnt == 0);

        // 1. 주문을 장바구니에 담는다.
        // 2. 장바구니 번호는 auto increasement로 자동증가한다.

        MemberDto memberDto = new MemberDto();
        String mbr_id = "asdf123";
        memberDto.setMbr_id(mbr_id);
        List<CartDto> cartDtoList;

        for (int i = 0; i < 1000; i++) {

            CartDto cartDtoInsert = new CartDto.Builder()
                    .mbr_id(memberDto.getMbr_id())
                    .build();

            cnt = cartDao.insert(cartDtoInsert);
            assertTrue(cnt == 1);
        }

        int count_row = cartDao.count();
        assertTrue(count_row == 1000);
    }

    // insert되어 있는 id의 상태를 변경
    @Test
    public void 고객의_장바구니를_삭제하는_경우() throws Exception {
        cartDao.deleteAll();
        int cnt = cartDao.count();
        assertTrue(cnt == 0);

        // 삭제하려는 장바구니를 보유한 고객
        String mbr_id = "asdf123";

        String user_id;
        for (int i = 0; i < 10000; i++) {
            MemberDto memberDto = new MemberDto();
            user_id = "asdf" + i;
            memberDto.setMbr_id(user_id);

            CartDto cartDtoInsert = new CartDto.Builder()
                    .mbr_id(memberDto.getMbr_id())
                    .build();

            cnt = cartDao.insert(cartDtoInsert);
        }

        int count_row = cartDao.count();
        System.out.println("count_row = " + count_row);
        assertTrue(count_row == 10000);

        String select_mbr_id = cartDao.selectUserCart(mbr_id).getMbr_id();
        System.out.println("select_mbr_id: " + select_mbr_id);
        assertTrue(mbr_id.equals(select_mbr_id));

        // 해당 고객의 장바구니 삭제
        cnt = cartDao.deleteUserCart(select_mbr_id);
        assertTrue(cnt == 1);
    }
}


//@Test
//public void testMaxCartSeqNotNull() throws Exception {
//    cartDao.deleteAll();
//    int cnt = cartDao.count();
//    assertTrue(cnt == 0);
//
//    Long crt_id = 1L;
//    String mbr_id = "asdf123";
//
//    CartDto cartDtoInsert = new CartDto.Builder()
//            .crt_id(crt_id)
//            .mbr_id(mbr_id)
//            .build();
//
//    cnt = cartDao.insert(cartDtoInsert);
//    assertTrue(cnt == 1);
//
//    // insert한 데이터로 확인
//    String datePart = crt_id.substring(0,10);
//    System.out.println(datePart);
//
//    // 존재하는 장바구니의 최대 번호 구하기
//    String maxCartSeq = cartDao.maxCartSeq(datePart);
//    System.out.println(maxCartSeq);
//    assertNotNull(maxCartSeq);
//}

// 현재 날짜에 존재하는 장바구니 존재여부 확인
//    @Test
//    public void testMaxCartSeqNull() throws Exception {
//        cartDao.deleteAll();
//        int cnt = cartDao.count();
//        assertTrue(cnt == 0);
//
//        Long crt_id = 1L;
//        String mbr_id = "asdf123";
//
//        CartDto cartDtoInsert = new CartDto.Builder()
//                .crt_id(crt_id)
//                .mbr_id(mbr_id)
//                .build();
//
//        cnt = cartDao.insert(cartDtoInsert);
//        assertTrue(cnt == 1);
//
//        // 현재 날짜 가져오기
//        LocalDate currentDate = LocalDate.now();
//
//        // 날짜를 "yyyyMMdd" 형식으로 포맷하기
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String formattedDate = currentDate.format(formatter);
//        System.out.println(formattedDate);
//
//        // 현재 날짜에 존재하는 장바구니 최대 번호 구하기
//        // null값을 반환하는 경우 해당 날짜에 장바구니가 존재하지 않는 것임
//        String maxCartSeq = cartDao.maxCartSeq("CT" + formattedDate);
//        System.out.println(maxCartSeq);
//        assertNull(maxCartSeq);
//    }

//    // 고객의 활성상태 장바구니가 존재하는지 확인
//    @Test
//    public void testUserCartActive() throws Exception {
//        cartDao.deleteAll();
//        int cnt = cartDao.count();
//        assertTrue(cnt == 0);
//
//        Long crt_id = 1L;
//        String mbr_id = "asdf123";
//
//        CartDto cartDtoInsert = new CartDto.Builder()
//                .crt_id(crt_id)
//                .mbr_id(mbr_id)
//                .build();
//
//        cnt = cartDao.insert(cartDtoInsert);
//        assertTrue(cnt == 1);
//
//        CartDto cartDto = cartDao.selectUserCart(mbr_id);
//        assertNotNull(cartDto);
//        assertEquals(cartDto.getMbr_id(), mbr_id);
//    }

//    //    실패 CASE 확인
//    // 장바구니 ID 글자수가 초과하는 경우 : 예상 StringIndexOutOfException
//    @Test
//    public void testInsertFailExceedString() throws Exception {
//        cartDao.deleteAll();
//        int cnt = cartDao.count();
//        assertTrue(cnt == 0);
//
//        Long crt_id = 1L;
//        String mbr_id = "asdf123";
//
//        CartDto cartDto = new CartDto.Builder()
//                .crt_id(crt_id)
//                .mbr_id(mbr_id)
//                .build();
//
//        try {
//            cartDao.insert(cartDto);
//            fail("Expected DataIntegrityViolationException");
//        } catch (DataIntegrityViolationException e) {
//            // 예외가 발생한 경우 메시지 출력
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        } catch (AssertionFailedError e) {
//            // 예외가 발생한 경우 메시지 출력
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            // junit.framework.AssertionFailedError -> junit 에러이므로 발생 예외에서 제외
//        }
//        // DataIntegrityViolationException 발생
//    }
//
//    // 장바구니 ID가 null인 경우 예상 : NullPointerException
//    @Test
//    public void testInsertFailCrtIdNull() throws Exception {
//        cartDao.deleteAll();
//        int cnt = cartDao.count();
//        assertTrue(cnt == 0);
//
//        Long crt_id = null;
//        String mbr_id = "asdf123";
//
//        CartDto cartDto = new CartDto.Builder()
//                .crt_id(crt_id)
//                .mbr_id(mbr_id)
//                .build();
//
//        try {
//            cartDao.insert(cartDto);
//    //            fail("Expected NullPointerException");
//            fail("Expected DataIntegrityViolationException");
//        } catch (DataIntegrityViolationException e) {
//            // 예외가 발생한 경우 메시지 출력
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        } catch (AssertionFailedError e) {
//            // 예외가 발생한 경우 메시지 출력
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            // junit.framework.AssertionFailedError -> junit 에러이므로 발생 예외에서 제외
//        }
//        // DataIntegrityViolationException 발생
//    }
//
//    // Dto에 데이터 담을 경우 검증이 안된 상태로 담는 경우 예상 : NullPointerException
//    @Test
//    public void testInsertFailDtoCrtIdNull() throws Exception {
//        cartDao.deleteAll();
//        int cnt = cartDao.count();
//        assertTrue(cnt == 0);
//
//        Long crt_id = 1L;
//        String mbr_id = "asdf123";
//
//        try {
//            CartDto cartDto = new CartDto.Builder()
//                    .crt_id(crt_id)
//                    .mbr_id(mbr_id)
//                    .build();
//            fail("Expected NullPointerException");
//        } catch (NullPointerException e) {
//            // 예외가 발생한 경우 메시지 출력
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        } catch (AssertionFailedError e) {
//            // 예외가 발생한 경우 메시지 출력
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//            // junit.framework.AssertionFailedError -> junit 에러이므로 발생 예외에서 제외
//        }
//    }
//
//    // 발생한 예외 정리
//    // DataIntegrityViolationException