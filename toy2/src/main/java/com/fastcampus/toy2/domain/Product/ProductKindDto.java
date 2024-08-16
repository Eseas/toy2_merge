package com.fastcampus.toy2.domain.Product;

import java.util.Date;
import java.util.Objects;

public class ProductKindDto {
    private String style_num;
    private String product_id;
    private String color_code;
    private String sale_state;
    private Long sale_count;
    private Date created_dt;
    private String created_id;
    private Date updated_dt;
    private String updated_id;

    private ProductKindDto() {
    }

    public ProductKindDto(Builder builder) {
        this.style_num = builder.style_num;
        this.product_id = builder.product_id;
        this.color_code = builder.color_code;
        this.sale_state = builder.sale_state;
        this.sale_count = builder.sale_count;
        this.created_dt = builder.created_dt;
        this.created_id = builder.created_id;
        this.updated_dt = builder.updated_dt;
        this.updated_id = builder.updated_id;
    }

    public String getStyle_num() {
        return style_num;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getColor_code() {
        return color_code;
    }

    public String getSale_state() {
        return sale_state;
    }

    public Long getSale_count() {
        return sale_count;
    }

    public Date getCreated_dt() {
        return created_dt;
    }

    public String getCreated_id() {
        return created_id;
    }

    public Date getUpdated_dt() {
        return updated_dt;
    }

    public String getUpdated_id() {
        return updated_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductKindDto that = (ProductKindDto) o;
        return Objects.equals(style_num, that.style_num) &&
                Objects.equals(product_id, that.product_id) &&
                Objects.equals(color_code, that.color_code) &&
                Objects.equals(sale_state, that.sale_state) &&
                Objects.equals(sale_count, that.sale_count) &&
                Objects.equals(created_dt, that.created_dt) &&
                Objects.equals(created_id, that.created_id) &&
                Objects.equals(updated_dt, that.updated_dt) &&
                Objects.equals(updated_id, that.updated_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(style_num, product_id, color_code, sale_state, sale_count, created_dt, created_id, updated_dt, updated_id);
    }

    @Override
    public String toString() {
        return "ProductKindDto{" +
                "style_num='" + style_num + '\'' +
                ", product_id='" + product_id + '\'' +
                ", color_code='" + color_code + '\'' +
                ", sale_state='" + sale_state + '\'' +
                ", sale_count=" + sale_count +
                ", created_dt=" + created_dt +
                ", created_id='" + created_id + '\'' +
                ", updated_dt=" + updated_dt +
                ", updated_id='" + updated_id + '\'' +
                '}';
    }

    public static class Builder {
        private String style_num;
        private String product_id;
        private String color_code;
        private String sale_state;
        private Long sale_count;
        private Date created_dt;
        private String created_id;
        private Date updated_dt;
        private String updated_id;

        public Builder() {
        }

        public Builder style_num(String style_num) {
            this.style_num = style_num;
            return this;
        }

        public Builder product_id(String product_id) {
            this.product_id = product_id;
            return this;
        }

        public Builder color_code(String color_code) {
            this.color_code = color_code;
            return this;
        }

        public Builder sale_state(String sale_state) {
            this.sale_state = sale_state;
            return this;
        }

        public Builder sale_count(Long sale_count) {
            this.sale_count = sale_count;
            return this;
        }

        public Builder created_dt(Date created_dt) {
            this.created_dt = created_dt;
            return this;
        }

        public Builder created_id(String created_id) {
            this.created_id = created_id;
            return this;
        }

        public Builder updated_dt(Date updated_dt) {
            this.updated_dt = updated_dt;
            return this;
        }

        public Builder updated_id(String updated_id) {
            this.updated_id = updated_id;
            return this;
        }

        public ProductKindDto build() {
            return new ProductKindDto(this);
        }
    }
}
