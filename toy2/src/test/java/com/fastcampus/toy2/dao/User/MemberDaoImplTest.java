//package com.fastcampus.toy2.dao.User;
//
//import com.fastcampus.toy2.domain.User.MemberDto;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.sql.Date;
//import java.util.*;
//
//import static org.junit.Assert.*;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml")
//public class MemberDaoImplTest {
//    @Autowired
//    MemberDao memberDao;
//
//    @Test
//    public void insertTest() throws Exception {
//        // 성공 케이스
//        // 1. 1개 회원정보 입력 후 데이터 수 확인
//        memberDao.deleteAll();
//        MemberDto memberDto = new MemberDto("asdf11","pwd11","N","일땡땡","2024-01-01","010-1111-1111","asdf11@naver.com");
//        memberDao.insert(memberDto);
//        assertTrue(memberDao.count()==1);
//        // 2. N개 회원정보 입력 후 데이터 수 확인
//        memberDao.deleteAll();
//        for(int i=0; i<10; i++){
//            memberDto = new MemberDto("asdf"+i,"pwd"+i,"N",i+"땡땡","2024-01-01","010-"+i,"asdf"+i+"@naver.com");
//            memberDao.insert(memberDto);
//        }
//        assertTrue(memberDao.count()==10);
//        // 3. select문 + equals() 적용해서 데이터가 올바르게 들어갔는지 확인
//
//
//        // 실패 케이스 - 길이 제한, 중복, notnull인데 null로 입력한 경우
//
//
//    }
//
//    @Test
//    public void selectAllTest() throws Exception {
//        // 성공 케이스
//        // 1. 회원정보 1개 입력 후 총 데이터 수 확인
//        memberDao.deleteAll();
//        MemberDto memberDto = new MemberDto("asdf11","pwd11","N","일땡땡","2024-01-01","010-1111-1111","asdf11@naver.com");
//        assertTrue(memberDao.insert(memberDto)==1);
//        List<MemberDto> list = memberDao.selectAll();
//        assertTrue(list.size()==1);
//        // 2. 데이터 1개 추가 후 총 데이터 수 확인
//        memberDto = new MemberDto("asdf22","pwd22","N","이땡땡","2024-01-01","010-2222-2222","asdf22@naver.com");
//        assertTrue(memberDao.insert(memberDto)==1);
//        list = memberDao.selectAll();
//        assertTrue(list.size()==2);
//        // 3. 내용 확인
//
//        // 실패 케이스
//
//    }
//
//    @Test
//    public void selectOneTest() throws Exception {
//        // 성공 케이스
//        // 1. mbr_id로 조회하기
//        memberDao.deleteAll();
//        assertTrue(memberDao.count() == 0);
//
//        MemberDto memberDto = null;
//        for(int i=0; i<10; i++){
//            memberDto = new MemberDto("asdf"+i,"pwd"+i,"N",i+"땡땡","2024-01-01","010-"+i,"asdf"+i+"@naver.com");
//            memberDao.insert(memberDto);
//        }
//        String idNum = memberDao.selectAll().get(5).getMbr_id();
//        memberDto.setMbr_id(idNum);
//        System.out.println("내가 넣어준 idNum = " + idNum);
//        MemberDto memberDto2 = memberDao.selectOne(idNum);
//        System.out.println("memberDto2의 id = " + memberDto2.getMbr_id());
//        System.out.println(memberDto);
//        System.out.println(memberDto2);
////        assertTrue(memberDto.equals(memberDto2));
//
//        // 실패 케이스
//
//    }
//
//    @Test
//    public void countTest() throws Exception {
//        // 성공 케이스
//        memberDao.deleteAll();
//        // 실패 케이스
//    }
//
//    @Test
//    public void updateTest() throws Exception {
//        // 성공 케이스
//        memberDao.deleteAll();
//        // 실패 케이스
//    }
//
//    @Test
//    public void deleteAllTest() throws Exception {
//        // 성공 케이스
//        memberDao.deleteAll();
//        // 실패 케이스
//    }
//
//    @Test
//    public void deleteTest() throws Exception {
//        // 성공 케이스
//        memberDao.deleteAll();
//        // 실패 케이스
//    }
//}