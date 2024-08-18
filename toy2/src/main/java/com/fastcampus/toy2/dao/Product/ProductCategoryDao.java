package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductCategoryDto;

import java.util.List;

public interface ProductCategoryDao {
    int insertProductCategory(ProductCategoryDto productCategoryDto) throws Exception;
    ProductCategoryDto selectById(Integer category_id) throws Exception;
    List<ProductCategoryDto> selectList() throws Exception;
    List<ProductCategoryDto> selectUpperEquals(Integer upper_category_id) throws Exception;
    int deleteAll() throws Exception;
}
