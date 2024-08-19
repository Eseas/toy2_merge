package com.fastcampus.toy2.service.User;

import com.fastcampus.toy2.dao.User.MemberDao;
import com.fastcampus.toy2.domain.User.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    MemberDao memberDao;

    // 1. 중복 id 확인 기능
    public boolean idDuplCheck(String mbr_id) throws Exception {
        String id = memberDao.selectMemberId(mbr_id);
        // 기존 회원id를 조회했을때 없으면(null이면) id사용 가능
        return id == null;
    }

    // 2. 이메일 인증 기능
    // 입력한 이메일 주소로 (랜덤 정수값을) 전송
    // 인력한 인증번호와 이메일 주소로 보낸 정수값이 같다면, '인증 성공'
    // 같지 않다면, '인증 실패' + 해당 주소 사용 불가

    // 3. 회원가입정보를 DB에 저장하는 기능
    public boolean memberSignup (MemberDto memberDto) throws Exception {
        // insert문을 통해 DB에 저장
        memberDao.memberSignup(memberDto);
        // insert가 제대로 됐는지 확인 (작성 필요)
        return true;
    }
}
/*
    검증과 회원가입 기능은 분리를 하는게 좋다.

    폼 -> 1차 입력 검증
    컨트롤러 -> 2차 입력 검증 (데이터의 형식과 필수 입력 사항을 확인) (타입과 도메인에 맞게 입력됐는지)
    서비스 -> 정보 검증 (데이터의 중복 여부와 이메일 인증 여부를 확인)


    필수 입력 작성 체크 기능 --> jsp에서 진행
        필수 입력 정보 = mbr_id, pw, name, birth, gender, phone_num, email, created_id, updated_id
        선택 입력 정보 = addr_cd, addr_line1, addr_line2, ps_info_collect_use_agree, sms_rcv_agree,
                       dm_rcv_agree, tm_rcv_agree, email_rcv_agree, opt_info_collect_use_agree, life_mbr_agree
    id 입력 기준 체크 기능 --> jsp에서 진행
        기준 1) 6~16자      2) 한글, 공백 불가능
    pw 입력 기준 체크 기능 --> jsp에서 진행
        기준 1) 8~16자      2) 영문, 숫자, 특수문자 조합  3) 대소문 구분   4) 한글, 공백 불가능

    1. id 중복 체크 기능
         입력한 id과 기존의 모든 회원 id를 비교했을 때 같지 않다면(null),
         '사용가능한 아이디 입니다.' 안내 문구
         id 비교시 같다면(notNull),
         '이미 사용중인 id 입니다.' 안내 문구
*/

