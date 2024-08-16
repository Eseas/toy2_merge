package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductDto;

import java.util.List;

public interface ProductDao {
    int count() throws Exception;
    int findProductCategoryPageCount(List<Integer> categories) throws Exception;
    int salecount() throws Exception;
    ProductDto selectById(String product_id) throws Exception;
    List<ProductDto> selectAll() throws Exception;
    List<ProductDto> selectSale() throws Exception;
    int insert(ProductDto productDto) throws Exception;
    int update(ProductDto productDto) throws Exception;
    int updateSaleState(String product_id) throws Exception;
    int updateSaleCount(String product_id, char reason) throws Exception;
    int delete(ProductDto productDto) throws Exception;
    int deleteAll() throws Exception;

    List<ProductDto> findProductpage(int page, int size) throws Exception;
    List<ProductDto> findProductCategoryPage(List<Integer> categories, int sort, int page, int size) throws Exception;
    public List<ProductDto> selectByKeyword(String keyword, int page, int size) throws Exception;
}