package com.fastcampus.toy2.domain.Product;

import java.util.Objects;

public class ProductSizeDto {
    private String style_num;
    private Integer p_size;
    private Integer p_stock;
    private char sale_state;

    public String getStyle_num() {
        return style_num;
    }

    public void setStyle_num(String style_num) {
        this.style_num = style_num;
    }

    public Integer getP_size() {
        return p_size;
    }

    public void setP_size(Integer p_size) {
        this.p_size = p_size;
    }

    public Integer getP_stock() {
        return p_stock;
    }

    public void setP_stock(Integer p_stock) {
        this.p_stock = p_stock;
    }

    public char getSale_state() {
        return sale_state;
    }

    public void setSale_state(char sale_state) {
        this.sale_state = sale_state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductSizeDto that = (ProductSizeDto) o;
        return Objects.equals(style_num, that.style_num) && Objects.equals(p_size, that.p_size) && Objects.equals(p_stock, that.p_stock) && Objects.equals(sale_state, that.sale_state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(style_num, p_size, p_stock, sale_state);
    }

    @Override
    public String toString() {
        return "ProductSizeDto{" +
                "style_num='" + style_num + '\'' +
                ", p_size='" + p_size + '\'' +
                ", p_stock='" + p_stock + '\'' +
                ", sale_state='" + sale_state + '\'' +
                '}';
    }
}
