package com.fastcampus.toy2.dao.User;

import com.fastcampus.toy2.domain.User.MemberDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberDaoImpl implements MemberDao {
    @Autowired
    SqlSession session;
    String namespace = "com.fastcampus.toy2.dao.MemberMapper.";

    //select 6개
    @Override
    public String selectMemberId(String mbr_id) throws Exception {
        System.out.println("selectMemberId called()");
        return session.selectOne(namespace + "selectMemberId", mbr_id);
    }
    @Override
    public MemberDto selectMemberInfo(String mbr_id) throws Exception {
        return session.selectOne(namespace + "selectMemberInfo", mbr_id);
    }
    @Override
    public List<MemberDto> selectAllMembers(Map map) throws Exception {
        return session.selectList(namespace + "selectAllMembers", map);
    }
    @Override
    public List<MemberDto> selectMembers(Map map) throws Exception {
        return session.selectList(namespace + "selectMembers", map);
    }
    @Override
    public MemberDto selectOneMember(Map map) throws Exception {
        return session.selectOne(namespace + "selectOneMember", map);
    }
    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    // insert 1개
    @Override
    public int memberSignup(MemberDto memberDto) throws Exception {
        return session.insert(namespace + "memberSignup", memberDto);
    }

    // update 3개
    @Override
    public int updateMember(MemberDto memberDto) throws Exception {
        return session.update(namespace + "updateMember", memberDto);
    }
    @Override
    public int updateAdmin(MemberDto memberDto) throws Exception {
        return session.update(namespace + "updateAdmin", memberDto);
    }
    @Override
    public int setMemberStatus(MemberDto memberDto) throws Exception {
        return session.update(namespace + "setMemberStatus", memberDto);
    }

    // delete 2개
    @Override
    public int delete(String mbr_id) throws Exception {
        return session.delete(namespace + "delete", mbr_id);
    };
    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    };
}