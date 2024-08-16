package com.fastcampus.toy2.service.Order;


import com.fastcampus.toy2.dao.Order.CartDao;
import com.fastcampus.toy2.dao.Order.CartItemDao;
import com.fastcampus.toy2.dao.Order.CartItemHistoryDao;
import com.fastcampus.toy2.dao.Product.*;
import com.fastcampus.toy2.domain.Order.CartItemDto;
import com.fastcampus.toy2.domain.Order.CartItemInfoDto;
import com.fastcampus.toy2.domain.Product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDao cartDao;

    @Autowired
    CartItemDao cartItemDao;

    @Autowired
    CartItemHistoryDao cartItemHistoryDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    ProductKindDao productKindDao;

    @Autowired
    ProductColorDao productColorDao;

    @Autowired
    ProductStockDao productStockDao;

    @Autowired
    ProductImageDao productImageDao;

    /*
        장바구니의 상품 정보를 보여주는 메서드
        1. 장바구니 상품에서 스타일 번호를 가지고 온다.
        2. 스타일 번호에서 "_"를 기준으로 앞쪽이 product_id이므로 상품 아이디를 분리한다.
        3. 상품아이디로 product테이블의 정보를 가지고 온다.
    */
    @Override
    @Transactional
    public List<CartItemInfoDto> getCartProduct(List<CartItemDto> cartItemDtoList) throws Exception {
        if (cartItemDtoList == null || cartItemDtoList.isEmpty()) {
            return new ArrayList<>(); // 빈 리스트 반환
        }

        // 장바구니에 담은 상품만큼의 장바구니와 상품 정보를 한번에 담을 cartItemInfo 리스트 생성
        List<CartItemInfoDto> cartItemInfoDtoList = new ArrayList<>();

        for (CartItemDto cartItemDto : cartItemDtoList) {
            CartItemInfoDto cartItemInfoDto = new CartItemInfoDto();

            cartItemInfoDto.setCartItemDto(cartItemDto);

            Map<String, String> productInfo = parseStyleNum(cartItemDto.getStyle_num());
            String product_id = productInfo.get("product_id");
            String color_code = productInfo.get("color_code");

            ProductDto productDto = productDao.selectById(product_id);
            ProductColorDto productColorDto = productColorDao.selectByColorCode(color_code);

            cartItemInfoDto.setProductDto(productDto);
            cartItemInfoDto.setProductColorDto(productColorDto);

            cartItemInfoDtoList.add(cartItemInfoDto);
        }

        return cartItemInfoDtoList;
    }

     /*
        1. 장바구니에 담는 경우
            1.1. 상품 상세에서 옵션을 선택한다.
            1.2. 장바구니에 담는 버튼을 누른다.
                - 회원의 장바구니를 확인한다.
            1.3. 장바구니가 존재하는 회원인 경우
                - 장바구니를 생성한다. 생성과 동시에 바로 번호 확인 가능
            1.4. 장바구니가 존재하지 않는 회원인 경우
                - 회원의 장바구니 번호를 조회하여 가지고 온다.
            1.5. 장바구니 번호에 상품을 담는다.
     */
//    public HttpSession addToCart(HttpSession session) throws Exception {
//
//
//    }


    /*
        2. 장바구니에 들어가서 보는 경우
            2.1. 회원 장바구니를 들어간다.
            2.2. 회원 장바구니에 담겨있는 정보들을 불러온다. -> 장바구니 정보 세션에 저장(브라우저 종료시 장바구니 내역 사라짐)
     */


    /*
        3. 바로 구매하기를 클릭하는 경우
            3.1. 로그인 한 경우
            3.2. 비로그인인 경우
    */


    /*
        장바구니에서 상품을 삭제하는 경우
    */
    @Override
    @Transactional
    public String removeItems(List<Map<String, String>> items, HttpSession session) throws Exception {
        // 세션에서 장바구니 리스트 가져오기
        List<CartItemDto> cartList = (List<CartItemDto>) session.getAttribute("cartList");

        if (cartList == null) {
            return "장바구니가 비어 있습니다.";
        }

        // items 리스트에 있는 style_num과 p_size를 이용해 해당 상품을 삭제
        for (Map<String, String> item : items) {
            String style_num = item.get("style_num");
            System.out.println("style_num = " + style_num);

            String p_size = item.get("p_size");
            System.out.println("p_size = " + p_size);

            // 삭제 조건이 정확한지 확인하기 위해 프린트문 추가
            boolean removed = cartList.removeIf(cartItem -> style_num.equals(cartItem.getStyle_num()) && p_size.equals(String.valueOf(cartItem.getP_size())));
            System.out.println("Item removed: " + removed);
        }

        // 세션에 업데이트된 장바구니 리스트 저장
        session.setAttribute("cartList", cartList);
        System.out.println("Updated cartList: " + cartList);

        return "상품 삭제 성공";
    }


    /*
        장바구니에서 상품 옵션을 변경하기위해서 옵션 변경하기 버튼을 클릭하는 경우

        1. style_num을 받아온다.
        2. style_num에서 product_id를 이용해서 product_kind 테이블의 product_id 컬럼이 일치하는 정보를 가지고 온다.
            - 색상정보를 확인하면 style_num들을 알 수 있기 때문에 style_num이 일치하는 product_stock 테이블에서 상품 사이즈들을 가지고 온다.
            - 상품 사이즈를 확인하면 해당 상품의 재고를 알 수 있다.
            - 상품 이미지는 style_num을 가지고 product_image 테이블에서 표시 우선순위가 가장 작은 컬럼을 하나 구해온다.
        3. 모든 상품 정보를 찾은 다음 model에 담아서 jsp로 전달한다.

        -----------------------

        필요한 테이블 : product_color, product, product_kind, product_image
        - product_color : 색상정보 => 이미지만 보여주면되고 style_num으로 가지고 오면 되서 product_color 정보는 필요 없을 것 같기도 함
        - product : 가격정보 => 옵션을 변경하는 것이기 때문에 옵션변경 창에서는 필요 없을 것 같기도 함
        - product_kind : join하기 위한 메인 테이블 => 굳이 없어도 될 것 같음
        - product_image : style_num의 대표 이미지를 가지고 오기 위함

        옵션 변경하면 바로 반영 되어야 됨
    */
    @Override
    @Transactional
    public Map<String, Object> getProductOptionsByStyleNum(String style_num) throws Exception {
        // 1. style_num으로 해당 상품의 product_id를 조회
        Map<String, String> productInfo = parseStyleNum(style_num);
        String product_id = productInfo.get("product_id");

        // 2. product_id로 모든 관련된 style_num들을 조회(productKind 테이블)
        List<ProductKindDto> productKinds = productKindDao.selectByProductId(product_id);

        // 3. 각 style_num에 대한 url 정보(product_image 테이블), 사이즈별 재고 정보 조회(productStock 테이블)
        Map<String, List<ProductStockDto>> stockInfo = new HashMap<>();
        List<ProductImageDto> images = new ArrayList<>();
        for (ProductKindDto productKindDto : productKinds) {

            // 이미지
            images.add(productImageDao.selectMainImageByStyleNum(productKindDto.getStyle_num()));

            // 사이즈별 재고
            List<ProductStockDto> stockList = productStockDao.selectByProductId(product_id);
            stockInfo.put(productKindDto.getStyle_num(), stockList);
        }

        // 4. 조회된 정보들을 맵에 담아 반환
        Map<String, Object> result = new HashMap<>();
        result.put("images", images);  // 모든 색상 이미지 정보
        result.put("stockInfo", stockInfo);  // 각 style_num의 사이즈 대한 재고 정보

        return result;
    }


    // style_num에서 product_id와 color_code를 분리하는 메서드
    public static Map<String, String> parseStyleNum(String styleNum) {
        String[] productIdColor = styleNum.split("_");
        Map<String, String> result = new HashMap<>();
        result.put("product_id", productIdColor[0]);
        result.put("color_code", productIdColor[1]);
        return result;
    }
}
