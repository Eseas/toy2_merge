package com.fastcampus.toy2.service.Order;

import com.fastcampus.toy2.domain.Order.CartItemDto;
import com.fastcampus.toy2.domain.Order.CartItemInfoDto;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface CartService {
    /*
            장바구니에 상품 정보를 보여주는 메서드
            1. 장바구니 상품에서 스타일 번호를 가지고 온다.
            2. 스타일 번호에서 "_"를 기준으로 앞쪽이 product_id이므로 상품 아이디를 분리한다.
            3. 상품아이디로 product테이블의 정보를 가지고 온다.
        */
    List<CartItemInfoDto> getCartProduct(List<CartItemDto> cartItemDtoList) throws Exception;
    String removeItems(List<Map<String, String>> items, HttpSession session) throws Exception;
    Map<String, Object> getProductOptionsByStyleNum(String style_num) throws Exception;
}
