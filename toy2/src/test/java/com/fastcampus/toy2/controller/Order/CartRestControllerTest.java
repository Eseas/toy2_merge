//package com.fastcampus.toy2.controller.Order;
//
//import com.fastcampus.toy2.domain.Order.CartItemDto;
//import com.fastcampus.toy2.service.Order.CartService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
//public class CartRestControllerTest {
//
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private CartRestController cartRestController;
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(cartRestController).build();
//    }
//
//    @Test
//    public void testAddToCart_Success() throws Exception {
//        String jsonRequest = "{\"style_num\":\"7KC7620_J08\", \"p_size\":270, \"count\":1}";
//
//        mockMvc.perform(post("/order/addToCart")
//                        .sessionAttr("mbr_id", "test_user")  // 세션 설정
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))  // JSON 요청 본문을 전달
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("text/plain;charset=UTF-8"))
//                .andExpect(content().string("선택하신 상품이 장바구니에 추가되었습니다."));
//    }
//
//    @Test
//    public void testAddToCart_ValidationError() throws Exception {
//        // JSON 요청에서 count 필드를 누락시켜 유효성 검사 오류 발생
//        String jsonRequest = "{\"style_num\":\"7KC7620_J08\", \"p_size\":270}";
//
//        mockMvc.perform(post("/order/addToCart")
//                        .sessionAttr("mbr_id", "test_user")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isBadRequest())
//                .andExpect(content().contentType("text/plain;charset=UTF-8"))
//                .andExpect(content().string("수량을 선택해 주시기 바랍니다."));
//    }
//}