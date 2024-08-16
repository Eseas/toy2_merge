package com.fastcampus.toy2.domain.Order;

import com.fastcampus.toy2.domain.Product.ProductColorDto;

import java.util.Objects;

// product_kind, product, product_image, product_size 조인
public class ProductDetailDto {
    private String style_num; // 스타일 번호     (product_kind)
    private String product_id; // 상품 번호     (product_kind)
    private String color_code; // 색상 코드     (product_kind)
    private Integer p_sale_price; // 실판가     (product)

    private String english_color; // 색상 영문 명칭                  (product_color)
    private String korean_color; // 색상 한글 명칭                   (product_color)

    private String photo_url; // 상품 사진 url                      (product_image)

/*
    //    private List<ProductSizeDto> productSizeDtoList;        (product_size -> product_stock)
    private Integer p_size; // 상품 사이즈       (product_size -> product_stock)
    private Integer p_stock; // 재고 수량       (product_size -> product_stock)
    private String sale_state; // 판매 상태 코드  (product_size -> product_stock)
*/

    public String getStyle_num() {
        return style_num;
    }

    public void setStyle_num(String style_num) {
        this.style_num = style_num;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public Integer getP_sale_price() {
        return p_sale_price;
    }

    public void setP_sale_price(Integer p_sale_price) {
        this.p_sale_price = p_sale_price;
    }

    public String getEnglish_color() {
        return english_color;
    }

    public void setEnglish_color(String english_color) {
        this.english_color = english_color;
    }

    public String getKorean_color() {
        return korean_color;
    }

    public void setKorean_color(String korean_color) {
        this.korean_color = korean_color;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetailDto that = (ProductDetailDto) o;
        return Objects.equals(style_num, that.style_num) && Objects.equals(product_id, that.product_id) && Objects.equals(color_code, that.color_code) && Objects.equals(p_sale_price, that.p_sale_price) && Objects.equals(english_color, that.english_color) && Objects.equals(korean_color, that.korean_color) && Objects.equals(photo_url, that.photo_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(style_num, product_id, color_code, p_sale_price, english_color, korean_color, photo_url);
    }

    @Override
    public String toString() {
        return "ProductDetailDto{" +
                "style_num='" + style_num + '\'' +
                ", product_id='" + product_id + '\'' +
                ", color_code='" + color_code + '\'' +
                ", p_sale_price=" + p_sale_price +
                ", english_color='" + english_color + '\'' +
                ", korean_color='" + korean_color + '\'' +
                ", photo_url='" + photo_url + '\'' +
                '}';
    }
}

