package com.fastcampus.toy2.dao.Order;

import com.fastcampus.toy2.domain.Order.CartItemDto;
import com.fastcampus.toy2.domain.Order.CartItemHistoryDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CartItemHistoryDao {
    @Autowired
    private SqlSession session;
    private static String namespace = "com.fastcampus.toy2.dao.CartItemHistoryMapper.";

    public int count() throws Exception {
        return session.selectOne(namespace + "count");
    }

    public int insert(Map map) throws Exception {
        return session.insert(namespace + "insert", map);
    }

    public int deleteAll() throws Exception {
        return session.delete(namespace + "deleteAll");
    }

    public List<CartItemHistoryDto> selectCartItemHistory(CartItemDto CartItemDto) throws Exception {
        return session.selectList(namespace + "selectCartItemHistory", CartItemDto);
    }
}
