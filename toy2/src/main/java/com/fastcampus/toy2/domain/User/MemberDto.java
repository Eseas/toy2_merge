package com.fastcampus.toy2.domain.User;

import java.sql.*;
import java.util.Objects;

public class MemberDto {
    String mbr_id;       // 회원id
    Long mbr_state_cd;    // 회원상태코드
    Long login_fail_cnt;  // 로그인 실패 횟수
    String pw;          // 회원pw
    String id_save_yn;    // id 저장 여부
    Date last_access_date;// 최근 접속일자
    Long mbr_grade_cd;    // 회원등급코드
    String name;        // 이름
    Date birth;         // 생년월일
    String gender;      // 성별
    String phone_num;    // 휴대폰번호
    String email;       // 이메일
    String addr_cd;      // 우편주소
    String addr_line1;   // 기본주소
    String addr_line2;   // 상세주소
    Date ps_info_collect_use_agree; //개인정보수집및이용동의
    Date sms_rcv_agree;   // sms 수신동의
    Date dm_rcv_agree;    // dm 수신동의
    Date tm_rcv_agree;    // tm 수신동의
    Date email_rcv_agree; // email 수신동의
    Date opt_info_collect_use_agree;//선택정보수집및이용동의
    Date life_mbr_agree;  // 평생회원동의
    Timestamp created_dt;
    String created_id;
    Timestamp updated_dt;
    String     updated_id;

    public MemberDto(){}
    public MemberDto(String mbr_id, String pw, String id_save_yn, String name,
                     String birth, String phone_num, String email){
        this.mbr_id = mbr_id;
        this.pw = pw;
        this.id_save_yn = id_save_yn;
        this.name = name;
        this.birth = Date.valueOf(birth);
        this.phone_num = phone_num;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDto memberDto = (MemberDto) o;
        return mbr_state_cd == memberDto.mbr_state_cd && login_fail_cnt == memberDto.login_fail_cnt && mbr_grade_cd == memberDto.mbr_grade_cd && Objects.equals(mbr_id, memberDto.mbr_id) && Objects.equals(pw, memberDto.pw) && Objects.equals(id_save_yn, memberDto.id_save_yn) && Objects.equals(last_access_date, memberDto.last_access_date) && Objects.equals(name, memberDto.name) && Objects.equals(birth, memberDto.birth) && Objects.equals(gender, memberDto.gender) && Objects.equals(phone_num, memberDto.phone_num) && Objects.equals(email, memberDto.email) && Objects.equals(addr_cd, memberDto.addr_cd) && Objects.equals(addr_line1, memberDto.addr_line1) && Objects.equals(addr_line2, memberDto.addr_line2) && Objects.equals(ps_info_collect_use_agree, memberDto.ps_info_collect_use_agree) && Objects.equals(sms_rcv_agree, memberDto.sms_rcv_agree) && Objects.equals(dm_rcv_agree, memberDto.dm_rcv_agree) && Objects.equals(tm_rcv_agree, memberDto.tm_rcv_agree) && Objects.equals(email_rcv_agree, memberDto.email_rcv_agree) && Objects.equals(opt_info_collect_use_agree, memberDto.opt_info_collect_use_agree) && Objects.equals(life_mbr_agree, memberDto.life_mbr_agree) && Objects.equals(created_dt, memberDto.created_dt) && Objects.equals(created_id, memberDto.created_id) && Objects.equals(updated_dt, memberDto.updated_dt) && Objects.equals(updated_id, memberDto.updated_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mbr_id, mbr_state_cd, login_fail_cnt, pw, id_save_yn, last_access_date, mbr_grade_cd, name, birth, gender, phone_num, email, addr_cd, addr_line1, addr_line2, ps_info_collect_use_agree, sms_rcv_agree, dm_rcv_agree, tm_rcv_agree, email_rcv_agree, opt_info_collect_use_agree, life_mbr_agree, created_dt, created_id, updated_dt, updated_id);
    }

    @Override
    public String toString() {
        return "MemberDto{" +
                "mbr_id='" + mbr_id + '\'' +
                ", mbr_state_cd=" + mbr_state_cd +
                ", login_fail_cnt=" + login_fail_cnt +
                ", pw='" + pw + '\'' +
                ", id_save_yn='" + id_save_yn + '\'' +
                ", last_access_date=" + last_access_date +
                ", mbr_grade_cd=" + mbr_grade_cd +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", gender='" + gender + '\'' +
                ", phone_num='" + phone_num + '\'' +
                ", email='" + email + '\'' +
                ", addr_cd='" + addr_cd + '\'' +
                ", addr_line1='" + addr_line1 + '\'' +
                ", addr_line2='" + addr_line2 + '\'' +
                ", ps_info_collect_use_agree=" + ps_info_collect_use_agree +
                ", sms_rcv_agree=" + sms_rcv_agree +
                ", dm_rcv_agree=" + dm_rcv_agree +
                ", tm_rcv_agree=" + tm_rcv_agree +
                ", email_rcv_agree=" + email_rcv_agree +
                ", opt_info_collect_use_agree=" + opt_info_collect_use_agree +
                ", life_mbr_agree=" + life_mbr_agree +
                ", created_dt=" + created_dt +
                ", created_id='" + created_id + '\'' +
                ", updated_dt=" + updated_dt +
                ", updated_id='" + updated_id + '\'' +
                '}';
    }

    public String getMbr_id() {
        return mbr_id;
    }

    public void setMbr_id(String mbr_id) {
        this.mbr_id = mbr_id;
    }

    public long getMbr_state_cd() {
        return mbr_state_cd;
    }

    public void setMbr_state_cd(long mbr_state_cd) {
        this.mbr_state_cd = mbr_state_cd;
    }

    public long getLogin_fail_cnt() {
        return login_fail_cnt;
    }

    public void setLogin_fail_cnt(long login_fail_cnt) {
        this.login_fail_cnt = login_fail_cnt;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getId_save_yn() {
        return id_save_yn;
    }

    public void setId_save_yn(String id_save_yn) {
        this.id_save_yn = id_save_yn;
    }

    public Date getLast_access_date() {
        return last_access_date;
    }

    public void setLast_access_date(Date last_access_date) {
        this.last_access_date = last_access_date;
    }

    public long getMbr_grade_cd() {
        return mbr_grade_cd;
    }

    public void setMbr_grade_cd(long mbr_grade_cd) {
        this.mbr_grade_cd = mbr_grade_cd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddr_cd() {
        return addr_cd;
    }

    public void setAddr_cd(String addr_cd) {
        this.addr_cd = addr_cd;
    }

    public String getAddr_line1() {
        return addr_line1;
    }

    public void setAddr_line1(String addr_line1) {
        this.addr_line1 = addr_line1;
    }

    public String getAddr_line2() {
        return addr_line2;
    }

    public void setAddr_line2(String addr_line2) {
        this.addr_line2 = addr_line2;
    }

    public Date getPs_info_collect_use_agree() {
        return ps_info_collect_use_agree;
    }

    public void setPs_info_collect_use_agree(Date ps_info_collect_use_agree) {
        this.ps_info_collect_use_agree = ps_info_collect_use_agree;
    }

    public Date getSms_rcv_agree() {
        return sms_rcv_agree;
    }

    public void setSms_rcv_agree(Date sms_rcv_agree) {
        this.sms_rcv_agree = sms_rcv_agree;
    }

    public Date getDm_rcv_agree() {
        return dm_rcv_agree;
    }

    public void setDm_rcv_agree(Date dm_rcv_agree) {
        this.dm_rcv_agree = dm_rcv_agree;
    }

    public Date getTm_rcv_agree() {
        return tm_rcv_agree;
    }

    public void setTm_rcv_agree(Date tm_rcv_agree) {
        this.tm_rcv_agree = tm_rcv_agree;
    }

    public Date getEmail_rcv_agree() {
        return email_rcv_agree;
    }

    public void setEmail_rcv_agree(Date email_rcv_agree) {
        this.email_rcv_agree = email_rcv_agree;
    }

    public Date getOpt_info_collect_use_agree() {
        return opt_info_collect_use_agree;
    }

    public void setOpt_info_collect_use_agree(Date opt_info_collect_use_agree) {
        this.opt_info_collect_use_agree = opt_info_collect_use_agree;
    }

    public Date getLife_mbr_agree() {
        return life_mbr_agree;
    }

    public void setLife_mbr_agree(Date life_mbr_agree) {
        this.life_mbr_agree = life_mbr_agree;
    }

    public Timestamp getCreated_dt() {
        return created_dt;
    }

    public void setCreated_dt(Timestamp created_dt) {
        this.created_dt = created_dt;
    }

    public String getCreated_id() {
        return created_id;
    }

    public void setCreated_id(String created_id) {
        this.created_id = created_id;
    }

    public Timestamp getUpdated_dt() {
        return updated_dt;
    }

    public void setUpdated_dt(Timestamp updated_dt) {
        this.updated_dt = updated_dt;
    }

    public String getUpdated_id() {
        return updated_id;
    }

    public void setUpdated_id(String updated_id) {
        this.updated_id = updated_id;
    }
}