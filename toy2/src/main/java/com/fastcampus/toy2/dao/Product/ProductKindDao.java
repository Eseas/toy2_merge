package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductKindDto;

import java.util.List;

public interface ProductKindDao {
    int count() throws Exception;
    ProductKindDto selectById(String style_num) throws Exception;
    List<ProductKindDto> selectListByProductId(String product_id) throws Exception;
    List<ProductKindDto> selectAll() throws Exception;
    int insert(ProductKindDto productKindDto) throws Exception;
    int insertAll(List<ProductKindDto> productKindDtos) throws Exception;
    int update(ProductKindDto productKindDto) throws Exception;
    int delete(ProductKindDto productKindDto) throws Exception;
    int deleteAll() throws Exception;

    int updateSaleCount(String style_num, char reason) throws Exception;
}
