//package com.fastcampus.toy2.dao.User;
//
//import com.fastcampus.toy2.domain.User.MemberDto;
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import java.util.*;
//
//@Repository
//public class MemberDaoImpl implements MemberDao {
//    @Autowired
//    SqlSession session;
//    String namespace = "com.fastcampus.toy2.dao.MemberMapper.";
//
//
//    @Override
//    public List<MemberDto> selectAll() throws Exception {
//        return session.selectList(namespace+"selectAll");
//    }
//    @Override
//    public MemberDto selectOne(String mbr_id) throws Exception {
//        return session.selectOne(namespace+"selectOne", mbr_id);
//    }
//    @Override
//    public int count() throws Exception {
//        return session.selectOne(namespace+"count");
//    }
//
//    @Override
//    public int insert(MemberDto dto) throws Exception {
//        return session.insert(namespace+"insert", dto);
//    }
//
//    @Override
//    public int deleteAll() throws Exception {
//        return session.delete(namespace+"deleteAll");
//    }
//    @Override
//    public int delete(String mbr_id, Date birth) throws Exception {
//        Map map = new HashMap();
//        map.put("mbr_id", mbr_id);
//        map.put("birth",birth);
//        return session.delete(namespace+"delete", map);
//    }
//
//    @Override
//    public int update(MemberDto dto) throws Exception {
//        return session.update(namespace+"update", dto);
//    }
//}
