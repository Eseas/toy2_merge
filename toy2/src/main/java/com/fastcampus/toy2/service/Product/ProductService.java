package com.fastcampus.toy2.service.Product;

import com.fastcampus.toy2.domain.Product.ProductCategoryDto;
import com.fastcampus.toy2.domain.Product.ProductDto;
import com.fastcampus.toy2.domain.Product.ProductKindDto;
import com.fastcampus.toy2.domain.Product.ProductStockDto;

import java.util.HashMap;
import java.util.List;


public interface ProductService {

    static int PAGE_SIZE = 12;

    int getCount() throws Exception;
    int getSaleCount() throws Exception;
    int findProductCategoryPageCount(List<Integer> categories) throws Exception;

    // 찾기
    ProductDto getProductById(String product_id) throws Exception;
    List<ProductDto> findProductCategoryPage(List<Integer> categories, int sort, int page, int size) throws Exception;
    List<ProductStockDto> getProductStockList(String product_id) throws Exception;
    List<Integer> getCategories(int category_id) throws Exception;
    List<ProductKindDto> getProductKindList(String product_id) throws Exception;
    HashMap<Integer, String> getSelectCategory(int category_id) throws Exception;

    //수정하기
    int change_product_sale_state(String product_id) throws Exception;
    String getcurrentCategory(int category_id) throws Exception;
    List<ProductCategoryDto> getUpperEqualsCategory(int category_id) throws Exception;
    boolean ChangeProductSaleCount(String style_num, char reason) throws Exception;
}
