package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductStockDto;

import java.util.List;

public interface ProductStockDao {
    public List<ProductStockDto> getStockList(String style_num) throws Exception;
    int changeStock(String style_num, int stock) throws Exception;
}
