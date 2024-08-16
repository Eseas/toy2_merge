package com.fastcampus.toy2.dao.Order;

import com.fastcampus.toy2.domain.Order.CartItemDto;
import com.fastcampus.toy2.domain.Order.CartItemHistoryDto;
import com.fastcampus.toy2.domain.User.MemberDto;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CartItemHistoryDaoTest extends TestCase {

    @Autowired
    CartItemHistoryDao cartItemHistoryDao;

    @Test
    public void 데이터_전체_삭제() throws Exception {
        cartItemHistoryDao.deleteAll();
        int cnt = cartItemHistoryDao.count();
        assertTrue(cnt == 0);
    }

    @Test
    public void 고객이_장바구니에_상품을_담는_경우() throws Exception {
        cartItemHistoryDao.deleteAll();
        int cnt = cartItemHistoryDao.count();
        assertTrue(cnt == 0);

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCrt_id((long) 1);
        cartItemDto.setCrt_seq(1);
        cartItemDto.setStyle_num("7KC7650_A01");
        cartItemDto.setP_size(230);
        cartItemDto.setCount(1);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        cartItemDto.setCrt_item_date(timestamp);
        cartItemDto.setCrt_item_update(timestamp);

        MemberDto memberDto = new MemberDto();
        memberDto.setMbr_id("asdf123");

        Map map = new HashMap();
        map.put("cart_item", cartItemDto);
        map.put("member", memberDto);
        cnt = cartItemHistoryDao.insert(map);

        assertTrue(cnt == 1);
    }

    @Test
    public void 고객이_장바구니에_담은_상품의_옵션을_변경하는_경우() throws Exception {
        cartItemHistoryDao.deleteAll();
        int cnt = cartItemHistoryDao.count();
        assertTrue(cnt == 0);

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setCrt_id((long) 1);
        cartItemDto.setCrt_seq(1);
        cartItemDto.setStyle_num("7KC7650_A01");
        cartItemDto.setP_size(230);
        cartItemDto.setCount(1);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        cartItemDto.setCrt_item_date(timestamp);
        cartItemDto.setCrt_item_update(timestamp);

        MemberDto memberDto = new MemberDto();
        memberDto.setMbr_id("asdf123");

        Map map = new HashMap();
        map.put("cart_item", cartItemDto);
        map.put("member", memberDto);
        cnt = cartItemHistoryDao.insert(map);
        assertTrue(cnt == 1);

        cartItemDto.setP_size(240);
        timestamp = new Timestamp(System.currentTimeMillis());
        cartItemDto.setCrt_item_update(timestamp);

        Timestamp hist_date = timestamp;
        char state = 'U';

        map.put("cart_item", cartItemDto);
        map.put("member", memberDto);
        map.put("hist_date", hist_date);
        map.put("state", state);
        cnt = cartItemHistoryDao.insert(map);
        assertTrue(cnt == 1);

        List<CartItemHistoryDto> cartItemHistoryList = cartItemHistoryDao.selectCartItemHistory(cartItemDto);

        assertTrue(cartItemHistoryList.size() == 2);
        
        for (CartItemHistoryDto cartItemHistoryDto : cartItemHistoryList) {
            System.out.println("cartItemHistoryDto = " + cartItemHistoryDto);
        }
        
        assertTrue(cartItemHistoryList.get(0).getState() == 'U');
    }
}