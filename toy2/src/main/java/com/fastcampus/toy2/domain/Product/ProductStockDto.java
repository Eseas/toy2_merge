package com.fastcampus.toy2.domain.Product;

import java.util.Date;
import java.util.Objects;

public class ProductStockDto {
    private String style_num;
    private Integer p_size;
    private Integer p_stock;
    private String sale_state;
    private Date created_dt;
    private String created_id;
    private Date updated_dt;
    private String updated_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductStockDto that = (ProductStockDto) o;
        return Objects.equals(style_num, that.style_num) && Objects.equals(p_size, that.p_size) && Objects.equals(p_stock, that.p_stock) && Objects.equals(sale_state, that.sale_state) && Objects.equals(created_id, that.created_id) && Objects.equals(updated_id, that.updated_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(style_num, p_size, p_stock, sale_state);
    }

    private ProductStockDto() {
    }

    public ProductStockDto(Builder builder) {
        this.style_num = builder.style_num;
        this.p_size = builder.p_size;
        this.p_stock = builder.p_stock;
        this.sale_state = builder.sale_state;
        this.created_dt = builder.created_dt;
        this.created_id = builder.created_id;
        this.updated_dt = builder.updated_dt;
        this.updated_id = builder.updated_id;
    }

    public String getStyle_num() {
        return style_num;
    }

    public Integer getP_size() {
        return p_size;
    }

    public Integer getP_stock() {
        return p_stock;
    }

    public String getSale_state() {
        return sale_state;
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

    public static class Builder {
        private String style_num;
        private Integer p_size;
        private Integer p_stock;
        private String sale_state;
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
        public Builder p_size(Integer p_size) {
            this.p_size = p_size;
            return this;
        }
        public Builder p_stock(Integer p_stock) {
            this.p_stock = p_stock;
            return this;
        }
        public Builder sale_state(String sale_state) {
            this.sale_state = sale_state;
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
        public ProductStockDto build() {
            return new ProductStockDto(this);
        }
    }

}
