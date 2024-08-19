//package com.fastcampus.toy2.dao.User;
//
//import com.fastcampus.toy2.domain.User.MemberDto;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class MemberDaoImplTest {
//    @Autowired
//    MemberDao memberDao;
//    // 성공 케이스
//    // 실패 케이스
//    @Autowired
//    DataSource ds;
//
//    @Test
//    public void 데이터베이스_연결_테스트() throws Exception {
//        try {
//            Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.
//            System.out.println("conn = " + conn);
//            assertTrue(conn != null);
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//            //Access denied for user 'root'@'localhost' (using password: YES) : password 틀릴 때
//            //Access denied for user 'roo'@'localhost' (using password: YES) : username 틀릴 때
//            //Unknown database 'toy3' : 스키마 이름 틀릴 때
//        }
//    }
//    // 테스트할 때는 중복을 줄이고 깔끔한 코드를 짜는 것 보다는 '확인' 목적에 알맞게 작성해야 한다.
//    // getTestMemberDto()를 만들어서 중복된 '회원가입 입력 데이터'를 줄여도 좋지만, 직관적으로 확인해야 하는 부분에서는 직접 데이터를 볼 수 있게
//    // new MemberDto("memberId999","memberPw#999","name","2000-01-01","F","01012345678","memberEmail@naver.com"); 이런 식으로 작성하는 것이 좋다.
//    @Test
//    public void selectMemberIdTest() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//        MemberDto memberDto = new MemberDto("mbrId999","mbrPw#999","name","2000-01-01","F","01012345678","mbrEmail@naver.com");
//        assertTrue(memberDao.memberSignup(memberDto)==1);
//        assertTrue(memberDao.selectMemberId("mbrId999").equals("mbrId999"));
//    }
//
//    @Test
//    public void selectMemberInfoTest() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//
//        List<MemberDto> list = new ArrayList<>();
//        for(int i=0; i<500; i++){
//            list.add(new MemberDto("mbrId"+i,"mbrPw#"+i,"mbrName"+i,"2000-01-01","F","01012345678","mbrEmail@naver.com"));
//            memberDao.memberSignup(list.get(i));
//        }
//        assertTrue(memberDao.count()==list.size());
//        // 첫번째 값
//        MemberDto test = memberDao.selectMemberInfo(list.get(0).getMbr_id());
//        assertTrue((test.getName()).equals(list.get(0).getName()));
//        // 중간 값
//        test = memberDao.selectMemberInfo(list.get(50).getMbr_id());
//        assertTrue((test.getName()).equals(list.get(50).getName()));
//        // 마지막 값
//        test = memberDao.selectMemberInfo(list.get(499).getMbr_id());
//        assertTrue((test.getName()).equals(list.get(499).getName()));
//    }//리스트에 담아서 하기
//
//    @Test
//    public void selectAllMembersTest() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//        // (문제점) map을 파라미터타입으로 받을 때, 어떻게 값을 넘겨주어야 하는지 모르겠다.
//        // 아래의 내용처럼 해봤지만, 'memberDao.selectAllMembers(sortBy)' 이 부분이 null로 들어가는 것 같다.
//        // 원인을 모르겠어서 selectOneMemberTest까지 테스트 진행 못 함
//        // (해결방법1) list로 반환하기 때문에 Dao에서 List 타입으로 받아야 함. MemberDto -> List<MemberDto>로 수정
//        // (해결방법2) daoImpl에서 selectOne()이 아닌 selectList()로 실행해야 함. selectOne() -> selectList() 로 수정
//
//        // 데이터가 없을 때 list.isEmpty() 이어야 함
//        Map<String, Object> sortBy = new HashMap<>();
//        sortBy.put("sortBy", "birth");
//        List<MemberDto> list = memberDao.selectAllMembers(sortBy);
//        assertTrue(list.isEmpty());
//
//        for(int i=1; i<=9; i++){
//            MemberDto memberDto = getTestMemberDtoIndex(i);
//            memberDao.memberSignup(memberDto);
//        }
//        assertTrue(memberDao.count()==9);
//        list = (List<MemberDto>) memberDao.selectAllMembers(sortBy);
//        assertTrue(list.size()==9);
//
//        MemberDto memberDto = new MemberDto("mbrId999","mbrPw#999","mbrName999","2000-01-11","F","01012345678","mbrEmail@naver.com");;
//        memberDao.memberSignup(memberDto);
//        assertTrue(memberDao.count()==10);
//        list = (List<MemberDto>) memberDao.selectAllMembers(sortBy);
//        assertTrue(list.size()==10);
//        // sortBy 기준대로 정렬됐는지 확인
//        System.out.println(list.get(9).getName());
//        assertTrue(list.get(0).getName().equals("mbrName1"));
//        assertTrue(list.get(5).getName().equals("mbrName6"));
//        assertTrue(list.get(8).getName().equals("mbrName9"));
//        assertTrue(list.get(9).getName().equals("mbrName999"));
//    }
//
//    @Test
//    public void selectMembersTest() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//        Map<String, Object> sortBy = new HashMap<>();
//        sortBy.put("sortBy", "name");
//        sortBy.put("mbr_status_cd", "1");
//        List<MemberDto> list = (List<MemberDto>) memberDao.selectAllMembers(sortBy);
//        assertTrue(list.isEmpty());
//
//        for(int i=9; i>=0; i--) {
//            MemberDto memberDto = getTestMemberDtoIndex(i);
//            memberDao.memberSignup(memberDto);
////            System.out.println(memberDto.getName());
//        }
//        assertTrue(memberDao.count()==10);
//        list = (List<MemberDto>) memberDao.selectAllMembers(sortBy);
//        assertTrue(list.size()==10);
//        assertTrue(list.get(0).getName().equals("name0"));
//        assertTrue(list.get(9).getName().equals("name9"));
//        // (문제점) 현재 2가지 기준 중에서 1가지(정렬기준)만 적용됨
//        // (해결하고 싶은 점) 기준 2가지(회원상태 기준과 정렬 기준)를 모두 적용하려면 어떻게 해야하는지?
//        // (예측) 회원상태(mbr_status_cd)기준은 map에 담긴 내용으로 if문 실행 여부가 결정될 것이고,
//        //       정렬 기준에 대한 내용도 map에 담긴 내용대로 정렬이 될 것이다!
//        //       즉, 두 가지 기준 모두 map에 담으면 작동될 것이라고 예측해 봄.
//        // (시도해야 하는 것) 회원 상태의 default 값이 1이므로, 일부 회원의 상태 코드를 바꿔준 뒤, 상태값에 따른 조회 시도
//        //
//    }
//
//    @Test
//    public void selectOneMemberTest() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//
//        for(int i=1; i<=9; i++){
//            MemberDto memberDto = getTestMemberDtoIndex(i);
//            memberDao.memberSignup(memberDto);
//        }
//        assertTrue(memberDao.count()==9);
//
//        Map<String, Object> selectKeyword = new HashMap<>();
//        selectKeyword.put("mbr_id", "aaaaa3");  //'a'를 검색한다면 a가 포함된 id도 조회되어야 함 (like)
//        selectKeyword.put("name", null);
//        selectKeyword.put("birth", null);
//        MemberDto memberDto = memberDao.selectOneMember(selectKeyword);
//        assertTrue(memberDto.getMbr_id().equals("aaaaa3")); //contain
//    }
//
//    @Test
//    public void countTest() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//
//        MemberDto memberDto = new MemberDto("aaaaa1","123456a!","name","2024-08-15","F","01011111111","email");
//        assertTrue(memberDao.memberSignup(memberDto)==1);
//        assertTrue(memberDao.count()==1);
//
//        memberDto = new MemberDto("aaaaa2","123456a!","name","2024-08-15","F","01011111111","email");
//        assertTrue(memberDao.memberSignup(memberDto)==1);
//        assertTrue(memberDao.count()==2);
//    }
//
//    @Test
//    public void memberSignupTest() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//        // 1명 회원가입 했을 때
//        MemberDto memberDto = getTestMemberDto();
//        System.out.println(memberDto);
//        assertTrue(memberDao.memberSignup(memberDto)==1);
//        MemberDto testDto = memberDao.selectMemberInfo(memberDto.getMbr_id());
//        assertEquals(testDto, memberDto);
//        // birth말고 unique값인 pk값으로 비교하기
//
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//        // n명 회원가입 했을 때
//        for(int i=1; i<=9; i++){
//            memberDto = getTestMemberDtoIndex(i);
//            assertTrue(memberDao.memberSignup(memberDto)==1);
//            testDto = memberDao.selectMemberInfo(memberDto.getMbr_id());
//            assertEquals(testDto, memberDto);
//        }
//    }
//
//    @Test
//    public void updateMemberTest() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//        // 회원 정보 생성
//        MemberDto memberDto = new MemberDto("aaaaa1","123456a!","name","2024-08-15","F","01011111111","email");
//        memberDao.memberSignup(memberDto);
//        assertTrue(memberDao.count()==1);
//        // select를 사용해서 insert했던 회원정보를 가져오기
//        Map<String, Object> selectKeyword = new HashMap<>();
//        selectKeyword.put("mbr_id", "aaaaa1");
//        memberDto = memberDao.selectOneMember(selectKeyword);
//        // 회원 정보 수정
//        memberDto.setPhone_num("99999999999");
//        memberDto.setBirth("2024-01-01");
//        assertTrue(memberDao.updateMember(memberDto)==1);
//        // 기존 insert 내용과 다른지 확인
//        assertTrue(!(memberDto.getBirth().equals("2024-08-15")));
//        assertTrue(!(memberDto.getPhone_num().equals("01011111111")));
//        // 변경된 update 내용과 같은지 확인
//        assertTrue(memberDto.getBirth().equals("2024-01-01"));
//        assertTrue(memberDto.getPhone_num().equals("99999999999"));
//        assertTrue(memberDao.count()==1);
//    }
//
//    @Test
//    public void updateAdminTest() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//        // 회원 정보 생성
//        MemberDto memberDto = new MemberDto("aaaaa1","123456a!","name","2024-08-15","F","01011111111","email");
//        memberDao.memberSignup(memberDto);
//        assertTrue(memberDao.count()==1);
//        // select를 사용해서 insert했던 회원정보를 가져오기
//        Map<String, Object> selectKeyword = new HashMap<>();
//        selectKeyword.put("mbr_id", "aaaaa1");
//        memberDto = memberDao.selectOneMember(selectKeyword);
//        // 회원 정보 수정 ( update문으로 수정 필요 )
//        memberDto.setEmail("test");
//        memberDto.setPhone_num("99999999999");
//        memberDto.setUpdated_id("관리자");
//        assertTrue(memberDao.updateAdmin(memberDto)==1);
//        // 기존 내용과 다른지 확인
//        assertTrue(!(memberDto.getEmail().equals("email")));
//        assertTrue(!(memberDto.getPhone_num().equals("01011111111")));
//        assertTrue(!(memberDto.getUpdated_id().equals("updator"))); // 'updator'는 insert 실행시 default로 설정되는 값이다.
//        // 변경된 내용과 같은지 확인
//        assertTrue(memberDto.getEmail().equals("test"));
//        assertTrue(memberDto.getPhone_num().equals("99999999999"));
//        assertTrue(memberDto.getUpdated_id().equals("관리자"));
//        assertTrue(memberDao.count()==1);
//    }
//
//    @Test
//    public void setMemberStatus() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//        // 회원 정보 생성
//        MemberDto memberDto = new MemberDto("aaaaa1","123456a!","name","2024-08-15","F","01011111111","email");
//        assertTrue(memberDao.memberSignup(memberDto)==1);
//        assertTrue(memberDao.count()==1);
//        // select를 사용해서 insert했던 회원정보를 가져오기
//        Map<String, Object> selectKeyword = new HashMap<>();
//        selectKeyword.put("mbr_id", "aaaaa1");
//        memberDto = memberDao.selectOneMember(selectKeyword);
//        // 회원 상태가 default 값으로 설정된 1이 맞는지 확인
//        assertTrue(memberDto.getMbr_status_cd().equals((long)1));
//        // 회원 상태 변경 (유효(1) -> 탈퇴(3))
//        memberDto.setMbr_status_cd((long)3);
//        assertTrue(memberDao.setMemberStatus(memberDto)==1);
//        // 기존 내용과 다른지 확인
//        System.out.println("변경 후 회원 상태 : "+memberDto.getMbr_status_cd());
//        assertTrue(!(memberDto.getMbr_status_cd().equals("1")));
//        // 변경된 내용과 같은지 확인
//        assertTrue(memberDto.getMbr_status_cd().equals((long)3));
//    }
//
//
//
//    @Test
//    public void delete() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//
//        MemberDto memberDto = getTestMemberDto();
//        assertTrue(memberDao.memberSignup(memberDto)==1);
//        assertTrue(memberDao.count()==1);
//
//        assertTrue(memberDao.delete(memberDto.getMbr_id())==1);
//        assertTrue(memberDao.count()==0);
//    }
//
//    @Test
//    public void deleteAll() throws Exception {
//        memberDao.deleteAll();
//        assertTrue(memberDao.count()==0);
//
//        List<MemberDto> list = new ArrayList<>();
//        for(int i=1; i<=9; i++) {
//            list.add(getTestMemberDtoIndex(i));
//            assertTrue(memberDao.memberSignup(list.get(i-1))==1); // 0부터 시작하므로 i-1
//        }
//        assertTrue(list.size()==9);
//        // Mybatis에서 delete문의 반환 타입은 int이고 지워진 개수를 반환한다.
//        // 그래서 deleteAll()의 횟수가 list의 사이즈와 같다.
//        assertTrue(memberDao.deleteAll()==list.size());
//        // list.size()로 확인하면 안 된다. list 자체에는 입력했던 dto가 그대로 담겨있기 때문에 9개로 나온다.
//        assertTrue(memberDao.count()==0);
//    }
//
//
//    // Dto 1개 생성
//    MemberDto getTestMemberDto(){
//        return new MemberDto("mbrId999","mbrPw#999","mbrName999","2000-01-01","F","01012345678","mbrEmail@naver.com");
//    }
//    // Dto n개 생성
//    MemberDto getTestMemberDtoIndex(int i) {
//        MemberDto memberDto = new MemberDto("mbrId"+i,"mbrPw#"+i,"mbrName"+i,"2000-01-0"+i,"F","01012345678","mbrEmail@naver.com");
//        return memberDto;
//    }
//
//}