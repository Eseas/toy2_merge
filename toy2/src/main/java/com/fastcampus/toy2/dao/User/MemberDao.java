package com.fastcampus.toy2.dao.User;

import com.fastcampus.toy2.domain.User.MemberDto;

import java.util.List;
import java.util.Map;

public interface MemberDao {
    //select 6개
    String selectMemberId(String mbr_id) throws Exception;

    MemberDto selectMemberInfo(String mbr_id) throws Exception;

    List<MemberDto> selectAllMembers(Map map) throws Exception;

    List<MemberDto> selectMembers(Map map) throws Exception;

    MemberDto selectOneMember(Map map) throws Exception;

    int count() throws Exception;

    // insert 1개
    int memberSignup(MemberDto memberDto) throws Exception;

    // update 3개
    int updateMember(MemberDto memberDto) throws Exception;

    int updateAdmin(MemberDto memberDto) throws Exception;

    int setMemberStatus(MemberDto memberDto) throws Exception;

    // delete 2개
    int delete(String mbr_id) throws Exception;

    int deleteAll() throws Exception;
}