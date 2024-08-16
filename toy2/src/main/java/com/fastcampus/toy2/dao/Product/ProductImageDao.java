package com.fastcampus.toy2.dao.Product;

import com.fastcampus.toy2.domain.Product.ProductImageDto;
import com.fastcampus.toy2.domain.Product.ProductKindDto;

import java.util.List;

public interface ProductImageDao {

    ProductImageDto selectMainImageByStyleNum(String style_num) throws Exception;
}
