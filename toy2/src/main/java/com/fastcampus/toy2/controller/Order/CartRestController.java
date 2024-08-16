package com.fastcampus.toy2.controller.Order;

import com.fastcampus.toy2.dao.Product.ProductImageDao;
import com.fastcampus.toy2.dao.Product.ProductKindDao;
import com.fastcampus.toy2.dao.Product.ProductStockDao;
import com.fastcampus.toy2.domain.Order.CartItemDto;
import com.fastcampus.toy2.service.Order.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
public class CartRestController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductKindDao productKindDao;

    @Autowired
    ProductStockDao productStockDao;

    @Autowired
    ProductImageDao productImageDao;

    /*
        고객(회원, 비회원)이 장바구니에 상품을 추가하는 경우
        바로 장바구니에 저장하는 것이 아닌 세션에 list로 저장 -> 구현 필요
        @Valid 사용하는 것에 대해서 이해가 필요함
    */
    @PostMapping("/addToCart")
    public ResponseEntity<String> addToCart(@Valid @RequestBody CartItemDto cartItemDto,
                                            BindingResult bindingResult,
                                            HttpServletRequest request) throws Exception {

        // 유효성 검사에서 오류가 발생한 경우
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));
            return new ResponseEntity<>(errorMessage.toString().trim(), HttpStatus.BAD_REQUEST);
        }

        HttpSession session = request.getSession();

        // 세션에서 cartList 호출
        List<CartItemDto> cartItemDtoList = (List<CartItemDto>) session.getAttribute("cartItemList");

        // 세션에 cartListdp 장바구니에 담으려는 정보를 list로 저장
        session.setAttribute("cartItemList", cartItemDtoList.add(cartItemDto));

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    /*
        장바구니에서 상품을 삭제할 때
    */
    @PostMapping("/removeItems")
    @ResponseBody
    public ResponseEntity<String> removeItems(@RequestBody List<Map<String, String>> items, HttpSession session) {
        try {
            String result = cartService.removeItems(items, session);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("상품 삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    장바구니에서 옵션 변경하기 버튼을 클릭할 때
    */
    @PostMapping("/getProductOptions")
    public ResponseEntity<Map<String, Object>> getProductOptions(@RequestParam("style_num") String style_num) {
        /*
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
        try {
            // 서비스로 style_num을 넘겨서 대한 옵션 정보를 조회
            Map<String, Object> productOptions = cartService.getProductOptionsByStyleNum(style_num);

            // 조회된 정보를 ResponseEntity에 담아 반환
            return new ResponseEntity<>(productOptions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}