package com.fastcampus.toy2.dao.Order;


import com.fastcampus.toy2.domain.Order.CartDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CartDaoImpl implements CartDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.fastcampus.toy2.dao.CartMapper.";

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    }

    @Override
    public int insert(CartDto cartDto) throws Exception {
        return session.insert(namespace + "insert", cartDto);
    }

    @Override
    public int update(Long crt_id) throws Exception {
        return session.update(namespace + "update", crt_id);
    }

    @Override
    public CartDto selectCartId(Long crt_id) throws Exception {
        return session.selectOne(namespace + "selectCartId", crt_id);
    }

    @Override
    public String maxCartSeq(String datePart) throws Exception{
        return session.selectOne(namespace + "maxCartSeq", datePart);
    }

    @Override
    public CartDto selectUserCart(String mbr_id) throws Exception{
        return session.selectOne(namespace + "selectUserCart", mbr_id);
    }

    @Override
    public int deleteUserCart(String mbr_id) throws Exception {
        return session.delete(namespace + "deleteUserCart", mbr_id);
    }

}
