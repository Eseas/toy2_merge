package com.fastcampus.toy2.domain.Order;

import com.fastcampus.toy2.domain.Product.ProductColorDto;
import com.fastcampus.toy2.domain.Product.ProductDto;

import java.util.Objects;

public class CartItemInfoDto {
    CartItemDto cartItemDto; // 장바구니 상품 정보
    ProductDto productDto; // 상품 대분류 정보(상품ID, 상품 표기명, 실판가, 상품 판매 상태, 메인 이미지 url)
    ProductColorDto productColorDto; // 상품 색상 정보

    public CartItemInfoDto() {
    }

    public CartItemInfoDto(CartItemDto cartItemDto, ProductDto productDto, ProductColorDto productColorDto) {
        this.cartItemDto = cartItemDto;
        this.productDto = productDto;
        this.productColorDto = productColorDto;
    }

    public CartItemDto getCartItemDto() {
        return cartItemDto;
    }

    public void setCartItemDto(CartItemDto cartItemDto) {
        this.cartItemDto = cartItemDto;
    }

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public ProductColorDto getProductColorDto() {
        return productColorDto;
    }

    public void setProductColorDto(ProductColorDto productColorDto) {
        this.productColorDto = productColorDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemInfoDto that = (CartItemInfoDto) o;
        return Objects.equals(cartItemDto, that.cartItemDto) && Objects.equals(productDto, that.productDto) && Objects.equals(productColorDto, that.productColorDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItemDto, productDto, productColorDto);
    }

    @Override
    public String toString() {
        return "CartItemInfoDto{" +
                "cartItemDto=" + cartItemDto +
                ", productDto=" + productDto +
                ", productColorDto=" + productColorDto +
                '}';
    }
}
