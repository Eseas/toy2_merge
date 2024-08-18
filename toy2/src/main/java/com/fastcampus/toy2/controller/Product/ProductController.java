
package com.fastcampus.toy2.controller.Product;

import com.fastcampus.toy2.domain.Product.ProductDto;
import com.fastcampus.toy2.domain.Product.ProductKindDto;
import com.fastcampus.toy2.domain.Product.ProductStockDto;
import com.fastcampus.toy2.service.Product.ProductService;
import com.fastcampus.toy2.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;
    
    static int PAGE_SIZE = 12;

//    @ExceptionHandler(Exception.class)
//    public String exception(Model model, Exception e) {
//        e.printStackTrace();
//        model.addAttribute("message", e.getMessage());
//        return "error";
//    }

    // try catch > exception handler > controllerAdvice

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") Integer category_id,
                       @RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "2") int sort, Model m, HttpServletRequest request) throws Exception {
        /**
         * 1. 페이지가 있다면, 페이지로, 없다면 1페이지를 현재 페이지로 저장한다.
         * 2. 페이지에 해당하는 판매중인 상품을 가져온다.
         * 3. 모델에 페이지에 대한 정보와 물품 리스트를 저장한다.
         * 4. 물품 리스트 뷰를 불러온다.
         *
         * 에러가 발생하면?
         * 1. 만약 이상한 category_id가 들어온다면?
         * 2. 만약 페이지가 전체 페이지보다 크거나 0보다 작다면?
         * 3. 정렬 기준이 이상하게 들어온다면?
         * 4. 만약 보여줄 상품이 하나도 없다면?
         */
        List<Integer> categories = productService.getCategories(category_id);
        int total_count = productService.findProductCategoryPageCount(categories);
        int maxPage = (total_count / PAGE_SIZE) + (total_count % PAGE_SIZE == 0 ? 0 : 1);

        int prevPage = page - 1;
        int nextPage = page + 1;
        List<ProductDto> productDtos;
        try {
            productDtos = productService.findProductCategoryPage(categories, sort, page, PAGE_SIZE);
        } catch (NullPointerException e) {
            e.printStackTrace();
            String msg = "상품이 없습니다.";
            return "error?msg=" + msg;
        }
        String current_category = productService.getcurrentCategory(category_id);
        HashMap<Integer, String> select_category = new HashMap<>();
        if(category_id != 1) {
            select_category = productService.getSelectCategory(category_id);
        }

        m.addAttribute("current_category", current_category);
        m.addAttribute("select_category", select_category);
        m.addAttribute("category_id", category_id);
        m.addAttribute("products", productDtos);
        m.addAttribute("total_count", total_count);
        m.addAttribute("sort", sort);
        m.addAttribute("page", page);
        m.addAttribute("prevPage", prevPage);
        m.addAttribute("nextPage", nextPage);
        m.addAttribute("maxPage", maxPage);

        return "product/productlist";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(name="id") String product_id,
                         @RequestParam(name="color_code", required=false) String color_code,
                         Model model) throws Exception {
        ProductDto productDto = productService.getProductById(product_id);
        List<ProductKindDto> productKindDtoList = productService.getProductKindList(product_id);
        String style_num = product_id + "_" + color_code;
        System.out.println("style_num = " + style_num);
        List<ProductStockDto> productStockDtoList = new ArrayList<>();
        if(color_code != null) {
        	System.out.println("color_code is not null");
            productStockDtoList = productService.getProductStockList(style_num);
        }
        System.out.println("productStockDtoList : " + productStockDtoList);
        model.addAttribute("product", productDto);
        model.addAttribute("productkind", productKindDtoList);
        model.addAttribute("selectedColorCode", color_code); // 현재 선택된 색상 코드
        model.addAttribute("productStock", productStockDtoList);

        return "product/productDetail";
    }

    @PostMapping("/add")
    public String add(ProductDto productDto, Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        /**
         *
         */
        HttpSession httpSession = request.getSession(false);
        if (httpSession != null) {
            String msg = "상품을 등록할 권한이 없습니다.";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/product/list";
        }

        if(check_product_info(productDto)) {
            String msg = "유효하지 않은 값이 입력되었습니다.";
            redirectAttributes.addAttribute("msg", msg);

            return "redirect:/product/list";
        }

        //int result_insert = productService.addProduct(productDto);
        /*
        if(result_insert == 1) {
            String msg = "상품을 등록했습니다.";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/product/list";
        } else {
            String msg = "상품 등록에 실패했습니다.";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/product/list";
        }
        */

        return "redirect:/product/list";
    }

    @PostMapping("/modify")
    public String modify(ProductDto productDto, Model m, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {

        HttpSession httpSession = request.getSession();
        if(httpSession != null) {
            return "redirect:/login";
        }

        String id = httpSession.getId();

        if(id == null || id.isEmpty()) {
            return "redirect:/login";
        }
        /*if(adminService.getAdmin(id).getId() == null) {
            String msg = "수정 권한이 없습니다.";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/product/list";
        }*/
        /*
        int result_modify = productService.modify(productDto);
        if(result_modify != 1) {
            String msg = "상품 정보 수정에 실패했습니다.";
            redirectAttributes.addAttribute("msg", msg);
            return "redirect:/product/list";
        }

        String msg = "상품 정보를 수정했습니다.";
        redirectAttributes.addAttribute("msg", msg);
        */
        return "redirect:/product/list";
    }

    private boolean check_product_info(ProductDto productDto) {
        if(productDto.getProduct_id() == null || productDto.getProduct_id().isEmpty() ||
                productDto.getP_name() == null || productDto.getP_name().isEmpty() ||
                productDto.getP_gender() == null || productDto.getP_gender().isEmpty() ||
                productDto.getP_origin_price() <= 0 || productDto.getP_origin_price() == null ||
                productDto.getP_sale_price() <= 0 || productDto.getP_sale_price() == null ||
                productDto.getP_discount_per() == null || productDto.getP_discount_per() < 0 || productDto.getP_discount_per() > 100 ||
                productDto.getMember_benefit_price() == 0 || productDto.getMember_benefit_price() == null ||
                productDto.getP_brief_text() == null || productDto.getP_brief_text().isEmpty() ||
                productDto.getMaterials_care_methods() == null || productDto.getMaterials_care_methods().isEmpty() ||
                productDto.getSale_state() == null || productDto.getSale_state().isEmpty() ||
                productDto.getMain_image_url() == null || productDto.getMain_image_url().isEmpty() ||
                productDto.getP_season() == null || productDto.getP_season().isEmpty() ||
                productDto.getCategory_id() == null || productDto.getCategory_id() <= 0 ||
                productDto.getCreated_id() == null || productDto.getCreated_id().isEmpty() ||
                productDto.getUpdated_id() == null || productDto.getUpdated_id().isEmpty()
        ) {
            return false;
        }
        return true;
    }
}