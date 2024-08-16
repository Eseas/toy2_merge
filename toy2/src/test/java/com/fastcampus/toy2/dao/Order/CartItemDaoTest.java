package com.fastcampus.toy2.dao.Order;

import com.fastcampus.toy2.domain.Order.CartDto;
import com.fastcampus.toy2.domain.Order.CartItemDto;
import com.fastcampus.toy2.domain.Product.ProductSizeDto;
import com.fastcampus.toy2.domain.User.MemberDto;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class CartItemDaoTest extends TestCase {

    @Autowired
    CartDao cartDao;
    @Autowired
    CartItemDao cartItemDao;

    @Test
    public void 데이터_전체_삭제() throws Exception {
        cartItemDao.deleteAll();
        int cnt = cartItemDao.count();
        assertTrue(cnt == 0);
    }


    @Test
    public void 고객이_장바구니에_상품을_담는_경우() throws Exception {
        cartDao.deleteAll();
        int cnt = cartDao.count();
        assertTrue(cnt == 0);
        cartItemDao.deleteAll();
        cnt = cartItemDao.count();
        assertTrue(cnt == 0);

        // 상품 정보(스타일번호, 사이즈)
        ProductSizeDto productKindDto = new ProductSizeDto();
        productKindDto.setStyle_num("7K27620_H01");
        productKindDto.setP_size(230);

        // 상품을 장바구니에 담음
        // 1. 장바구니 생성
        String mbr_id = "asdf123";
        Integer count = 2;

        MemberDto memberDto = new MemberDto();
        memberDto.setMbr_id(mbr_id);

        CartDto cartDto = new CartDto.Builder()
                .mbr_id(mbr_id)
                .build();

        cartDao.insert(cartDto);
        System.out.println("cartDto.getCrt_id() : " + cartDto.getCrt_id());

        // 2. 장바구니_상품에 선택한 상품 정보 insert
        // 2.1. 장바구니_상품에 넣으려면 일련번호를 구해야 됨
        Integer crt_seq = cartItemDao.maxCartCrtSeq(cartDto.getCrt_id());
        System.out.println("cart_seq = " + crt_seq);
        if(crt_seq == null) {
            crt_seq = 1;
        } else {
            crt_seq += 1;
        }
        System.out.println("update crt_seq = " + crt_seq);

        CartItemDto cartItemDto = new CartItemDto(
                cartDto.getCrt_id(),
                crt_seq,
                productKindDto.getStyle_num(),
                productKindDto.getP_size(),
                count);

        System.out.println("cartItemDto.crt_id = " + cartItemDto.getCrt_id());

        Map<String, Object> map = new HashMap<>();
        map.put("cart_item", cartItemDto);
        map.put("member", memberDto);

        cnt = cartItemDao.insert(map);
        assertTrue(cnt == 1);
    }

    @Test
    public void 고객이_장바구니에_여러_종류의_상품을_담는_경우() throws Exception {
        cartDao.deleteAll();
        int cnt = cartDao.count();
        assertTrue(cnt == 0);
        cartItemDao.deleteAll();
        cnt = cartItemDao.count();
        assertTrue(cnt == 0);

        // 상품을 장바구니에 담음
        // 1. 장바구니 생성
        String mbr_id = "asdf123";

        MemberDto memberDto = new MemberDto();
        memberDto.setMbr_id(mbr_id);

        CartDto cartDto = new CartDto.Builder()
                .mbr_id(mbr_id)
                .build();

        cartDao.insert(cartDto);

        List<ProductSizeDto> productList = new ArrayList<>();
        // 상품 정보(스타일번호, 사이즈)
        ProductSizeDto productKindDto = new ProductSizeDto();

        int num = 1000;

        for (int i = 0; i < 1000; i++) {
            String brand = "7K";
            String strNum = String.format("%05d", i+1);
            String color_cd = "H01";
            productKindDto.setStyle_num(brand + strNum + color_cd);
            productKindDto.setP_size(230);

            productList.add(productKindDto);
        }

        assertTrue(productList.size() == num);

        // 2. 장바구니_상품에 선택한 상품 정보 insert
        List<CartItemDto> cartItemList = new ArrayList<>();

        // 2.1. 장바구니_상품에 넣으려면 일련번호를 구해야 됨(임시 일련번호)
        Integer crt_seq = 1;
        for (int i = 0; i < num; i++) {
            Integer count = (int) (Math.random() * 100) + 1;

            CartItemDto cartItemDto = new CartItemDto(
                    cartDto.getCrt_id(),
                    crt_seq,
                    productKindDto.getStyle_num(),
                    productKindDto.getP_size(),
                    count);
//            CartItemDto cartItemDto = new CartItemDto.Builder()
//                    .crt_id(cartDto.getCrt_id())
//                    .crt_seq(crt_seq)
//                    .style_num(productKindDto.getStyle_num())
//                    .p_size(productKindDto.getP_size())
//                    .count(count)
//                    .build();

            cartItemList.add(cartItemDto);

            crt_seq += 1;
        }

        for (CartItemDto cartItemDto : cartItemList) {
            Map<String, Object> map = new HashMap<>();
            map.put("cart_item", cartItemDto);
            map.put("member", memberDto);
            cnt = cartItemDao.insert(map);
        }

        assertTrue(cartItemList.size() == num);
        assertTrue(cartItemDao.count() == 1000);

        int index = (int) (Math.random() * num);
        CartItemDto cartItemDto = cartItemList.get(index);
        System.out.println("cartItemDto = " + cartItemDto);

        CartItemDto compairCartItemDto = cartItemDao.selectCartItem(cartItemDto);
        System.out.println("compairCartItemDto = " + compairCartItemDto);
        assertTrue(cartItemDto.getCrt_seq().equals(compairCartItemDto.getCrt_seq()));
    }

    @Test
    public void 고객이_장바구니에_담은_상품의_옵션을_변경하는_경우() throws Exception {
        cartDao.deleteAll();
        int cnt = cartDao.count();
        assertTrue(cnt == 0);
        cartItemDao.deleteAll();
        cnt = cartItemDao.count();
        assertTrue(cnt == 0);

        // 상품을 장바구니에 담음
        // 1. 장바구니 생성
        String mbr_id = "asdf123";

        MemberDto memberDto = new MemberDto();
        memberDto.setMbr_id(mbr_id);

        CartDto cartDto = new CartDto.Builder()
                .mbr_id(mbr_id)
                .build();

        cartDao.insert(cartDto);

        List<ProductSizeDto> productList = new ArrayList<>();
        // 상품 정보(스타일번호, 사이즈)
        ProductSizeDto productKindDto = new ProductSizeDto();

        int num = 1000;

        for (int i = 0; i < 1000; i++) {
            String brand = "7K";
            String strNum = String.format("%05d", i+1);
            String color_cd = "H01";
            productKindDto.setStyle_num(brand + strNum + color_cd);
            productKindDto.setP_size(230);

            productList.add(productKindDto);
        }

        assertTrue(productList.size() == num);

        // 2. 장바구니_상품에 선택한 상품 정보 insert
        List<CartItemDto> cartItemList = new ArrayList<>();

        // 2.1. 장바구니_상품에 넣으려면 일련번호를 구해야 됨(임시 일련번호)
        Integer crt_seq = 1;
        for (int i = 0; i < num; i++) {
            Integer count = (int) (Math.random() * 100) + 1;

            CartItemDto cartItemDto = new CartItemDto(
                    cartDto.getCrt_id(),
                    crt_seq,
                    productKindDto.getStyle_num(),
                    productKindDto.getP_size(),
                    count);

            cartItemList.add(cartItemDto);

            crt_seq += 1;
        }

        for (CartItemDto cartItemDto : cartItemList) {
            Map<String, Object> map = new HashMap<>();
            map.put("cart_item", cartItemDto);
            map.put("member", memberDto);
            cnt = cartItemDao.insert(map);
        }

        assertTrue(cartItemList.size() == num);
        assertTrue(cartItemDao.count() == 1000);

        int index = (int) (Math.random() * num);
        CartItemDto cartItemDto = cartItemList.get(index);
        System.out.println("cartItemDto = " + cartItemDto);

        CartItemDto compairCartItemDto = cartItemDao.selectCartItem(cartItemDto);
        assertTrue(cartItemDto.getCrt_seq().equals(compairCartItemDto.getCrt_seq()));

//      상품 선택하여 옵션 변경
        int p_size = 240;
        int count = 101;
        cartItemDto.setP_size(p_size);
        cartItemDto.setCount(count);

        cartItemDao.updateOption(cartItemDto);

        CartItemDto updateCartItemDto = cartItemDao.selectCartItem(cartItemDto);
        System.out.println("updateCartItemDto = " + updateCartItemDto);

        assertTrue(updateCartItemDto.getP_size() == p_size && updateCartItemDto.getCount() == count);

    }

    @Test
    public void 장바구니에_담은_상품_삭제() throws Exception {
        cartDao.deleteAll();
        int cnt = cartDao.count();
        assertTrue(cnt == 0);
        cartItemDao.deleteAll();
        cnt = cartItemDao.count();
        assertTrue(cnt == 0);

        // 상품을 장바구니에 담음
        // 1. 장바구니 생성
        String mbr_id = "asdf123";

        MemberDto memberDto = new MemberDto();
        memberDto.setMbr_id(mbr_id);

        CartDto cartDto = new CartDto.Builder()
                .mbr_id(mbr_id)
                .build();

        cartDao.insert(cartDto);

        List<ProductSizeDto> productList = new ArrayList<>();
        // 상품 정보(스타일번호, 사이즈)
        ProductSizeDto productKindDto = new ProductSizeDto();

        int num = 10;

        for (int i = 0; i < num; i++) {
            String brand = "7K";
            String strNum = String.format("%05d", i+1);
            String color_cd = "H01";
            productKindDto.setStyle_num(brand + strNum + color_cd);
            productKindDto.setP_size(230);

            productList.add(productKindDto);
        }

        assertTrue(productList.size() == num);

        // 2. 장바구니_상품에 선택한 상품 정보 insert
        List<CartItemDto> cartItemList = new ArrayList<>();

        // 2.1. 장바구니_상품에 넣으려면 일련번호를 구해야 됨(임시 일련번호)
        Integer crt_seq = 1;
        for (int i = 0; i < num; i++) {
            Integer count = (int) (Math.random() * num) + 1;

            CartItemDto cartItemDto = new CartItemDto(
                    cartDto.getCrt_id(),
                    crt_seq,
                    productKindDto.getStyle_num(),
                    productKindDto.getP_size(),
                    count);

            cartItemList.add(cartItemDto);

            crt_seq += 1;
        }

        for (CartItemDto cartItemDto : cartItemList) {
            Map<String, Object> map = new HashMap<>();
            map.put("cart_item", cartItemDto);
            map.put("member", memberDto);
            cnt = cartItemDao.insert(map);
        }

        assertTrue(cartItemList.size() == num);
        assertTrue(cartItemDao.count() == num);

        int index = (int) (Math.random() * num);
        CartItemDto cartItemDto = cartItemList.get(index);

        // 장바구니의 상품 전체를 삭제
        int size = cartItemDao.countCartItem(cartItemDto.getCrt_id());

        List<Integer> list = cartItemDao.selectCrtSeqListByCrtId(cartItemDto.getCrt_id());

        assertTrue(size == list.size());

        Map map = new HashMap<>();
        map.put("crt_id", cartItemDto.getCrt_id());
        for (int i = 0; i < size; i++) {
            map.put("crt_seq", list.get(i));
            cnt = cartItemDao.deleteCartItem(map);
            assertTrue(cnt == 1);
        }

        assertTrue(cartItemDao.countCartItem(cartItemDto.getCrt_id()) == 0);
    }

    // 발생할 수 있는 에어
    // DuplicateKeyException
}