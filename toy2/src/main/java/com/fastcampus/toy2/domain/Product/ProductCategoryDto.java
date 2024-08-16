package com.fastcampus.toy2.domain.Product;

import java.util.Date;
import java.util.Objects;

public class ProductCategoryDto {
    private Integer category_id;
    private String category;
    private Integer upper_category_id;
    private String using_state;
    private Date created_dt;
    private String created_id;
    private Date updated_dt;
    private String updated_id;

    private ProductCategoryDto() {
    }

    @Override
    public String toString() {
        return "ProductCategoryDto{" +
                "category_id=" + category_id +
                ", category='" + category + '\'' +
                ", upper_category_id=" + upper_category_id +
                ", using_state='" + using_state + '\'' +
                ", created_dt=" + created_dt +
                ", created_id='" + created_id + '\'' +
                ", updated_dt=" + updated_dt +
                ", updated_id='" + updated_id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCategoryDto that = (ProductCategoryDto) o;
        return Objects.equals(category_id, that.category_id) && Objects.equals(category, that.category) && Objects.equals(upper_category_id, that.upper_category_id) && Objects.equals(using_state, that.using_state) && Objects.equals(created_id, that.created_id) && Objects.equals(updated_id, that.updated_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category_id, category, upper_category_id, using_state);
    }

    public ProductCategoryDto(Builder builder) {
        this.category_id = builder.category_id;
        this.category = builder.category;
        this.upper_category_id = builder.upper_category_id;
        this.using_state = builder.using_state;
        this.created_dt = builder.created_dt;
        this.created_id = builder.created_id;
        this.updated_dt = builder.updated_dt;
        this.updated_id = builder.updated_id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public String getCategory() {
        return category;
    }

    public Integer getUpper_category_id() {
        return upper_category_id;
    }

    public String getUsing_state() {
        return using_state;
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
        private Integer category_id;
        private String category;
        private Integer upper_category_id;
        private String using_state;
        private Date created_dt;
        private String created_id;
        private Date updated_dt;
        private String updated_id;

        public Builder() {
        }

        public Builder category_id(Integer category_id) {
            this.category_id = category_id;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }

        public Builder upper_category_id(Integer upper_category_id) {
            this.upper_category_id = upper_category_id;
            return this;
        }

        public Builder using_state(String using_state) {
            this.using_state = using_state;
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

        public ProductCategoryDto build() {
            return new ProductCategoryDto(this);
        }
    }
}
