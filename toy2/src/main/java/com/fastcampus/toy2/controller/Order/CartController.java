package com.fastcampus.toy2.controller.Order;

import com.fastcampus.toy2.domain.Order.CartItemDto;
import com.fastcampus.toy2.domain.Order.CartItemInfoDto;
import com.fastcampus.toy2.service.Order.CartService;
import com.fastcampus.toy2.service.Order.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/order")
public class CartController {

    @Autowired
    CartService cartService;

    /*
        크게 장바구니를 나누어서 생각한다면?

        1. 장바구니에 담고나서 로그인 하는 경우
        2. 로그인 하고 장바구니에 담는 경우

        고객(회원, 비회원)이 장바구니에 담는 시기?

        1. 장바구니에 담다가 중간에 로그인하는 경우
        2. 장바구니에 담고 결제할 때 로그인하는 경우
        3. 로그인 하고 장바구니에 담는 경우
        4. 장바구니에 담고 결제할 때 비회원 구매하는 경우

        로그인하면 로그인 정보를 세션에서 확인해서 세션에 로그인 정보가 있다면 장바구니 테이블에 저장?
        장바구니 테이블에 언제 insert를 진행할 것인가?
        로그인 하고 장바구니에 담으면 바로 테이블로 저장하려고 했는 데 이런 경우에 장바구니에서 상품을 삭제할 때 어떻게 할지
        테이블에 저장하면 로그인할때 정보를 불러오면 된다고 생각했는 데 상품 삭제하는 경우랑… 계속 헷갈려서 고민이 아직 덜된거 같음. 계속 더 고민하면서 진행해보면 될꺼 같음

        쿠키
    */

    // 바로구매하기 버튼을 클릭한다 -> 추후 필요한 메서드 임의 작성
    @PostMapping("/buyToItem")
    public String buyToItem(@RequestParam("style_num") String styleNum,
                            @RequestParam("price") Integer price,
                            @RequestParam("size") String size,
                            @RequestParam("count") Integer quantity,
                            Model model, HttpSession session) throws Exception {
        return "/order/buy";
    }


    /*
        장바구니 페이지로 이동하는 경우.
        1. 세션에서 장바구니 리스트를 불러온다.
        2. 장바구니 리스트를 모델에 담아서 cart.jsp 페이지로 넘긴다.
            - cartItem에서 다루는 정보 : sytle_num, p_size
            - 판매 상태를 다루기 위해서는 product_size 테이블의 판매 상태 코드 필요
              (품절인 경우 때문)
            - 상품 가격정보를 알기위해서는 product 테이블에서 가격정보(실판가)를 가져와야 됨
    */
    @RequestMapping("/cart")
    public String getCartList(Model model, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        List<CartItemDto> cartItemDtoList = (List<CartItemDto>) session.getAttribute("cartList");

        // 테스트용 데이터 설정
        if (cartItemDtoList == null || cartItemDtoList.isEmpty()) {
            cartItemDtoList = new ArrayList<>();

            // 테스트용 데이터 추가
            addTestData(cartItemDtoList);

            // 세션에 cartList를 저장
            session.setAttribute("cartList", cartItemDtoList);
        }

        // CartItemDto 목록을 통해 CartItemInfoDto 목록 생성
        List<CartItemInfoDto> cartItemInfoDtoList = cartService.getCartProduct(cartItemDtoList);

        // cartList를 모델에 추가
        model.addAttribute("cartList", cartItemInfoDtoList);

        return "/order/cart";
    }

    // 주문하기 버튼을 클릭한다. -> 추후 필요한 메서드 임의 작성
    @RequestMapping("/pay")
    public String getOrderList(Model model, HttpSession session, HttpServletRequest request) throws Exception {

        return "/order/pay";
    }




    // 화면단으로 데이터가 잘 넘어가는지 확인하기 위해서 임의로 작성한 3가지 데이터를 담고있는 테스트용 메서드(추후 삭제)
    public static void addTestData(List<CartItemDto> cartItemDtoList) {
        CartItemDto sample1 = new CartItemDto();
        sample1.setStyle_num("7K17664_K01");
        sample1.setP_size(230);
        sample1.setCount(2);
        cartItemDtoList.add(sample1);

        CartItemDto sample2 = new CartItemDto();
        sample2.setStyle_num("7K27661_B00");
        sample2.setP_size(260);
        sample2.setCount(3);
        cartItemDtoList.add(sample2);

        CartItemDto sample3 = new CartItemDto();
        sample3.setStyle_num("7KC7602_K16");
        sample3.setP_size(255);
        sample3.setCount(3);
        cartItemDtoList.add(sample3);
    }
}
