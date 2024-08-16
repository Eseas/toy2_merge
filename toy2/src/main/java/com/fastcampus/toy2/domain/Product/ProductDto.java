package com.fastcampus.toy2.domain.Product;

import java.util.Date;
import java.util.Objects;

public class ProductDto {
	private String product_id;
    private String p_name;
    private String p_gender;
    private Integer p_origin_price;
    private Integer p_sale_price;
    private Integer p_discount_per;
    private Integer member_benefit_price;
    private Float p_average_grade;
    private String p_brief_text;
    private String materials_care_methods;
    private String sale_state;
    private String main_image_url;
    private String p_season;
    private Integer category_id;
    private Long sale_count;
    private Date created_dt;
    private String created_id;
    private Date updated_dt;
    private String updated_id;

    @Override
    public String toString() {
        return "ProductDto{" +
                "product_id='" + product_id + '\'' +
                ", p_name='" + p_name + '\'' +
                ", p_gender='" + p_gender + '\'' +
                ", p_origin_price=" + p_origin_price +
                ", p_sale_price=" + p_sale_price +
                ", p_discount_per=" + p_discount_per +
                ", member_benefit_price=" + member_benefit_price +
                ", p_average_grade=" + p_average_grade +
                ", p_brief_text='" + p_brief_text + '\'' +
                ", materials_care_methods='" + materials_care_methods + '\'' +
                ", sale_state='" + sale_state + '\'' +
                ", sale_count='" + sale_count + '\'' +
                ", main_image_url='" + main_image_url + '\'' +
                ", p_season='" + p_season + '\'' +
                ", category_id=" + category_id +
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
        ProductDto productDto = (ProductDto) o;
        return
                Objects.equals(product_id, productDto.getProduct_id()) &&
                Objects.equals(p_name, productDto.getP_name()) &&
                Objects.equals(p_gender, productDto.getP_gender()) &&
                Objects.equals(p_origin_price, productDto.getP_origin_price()) &&
                Objects.equals(p_sale_price, productDto.getP_sale_price()) &&
                Objects.equals(p_discount_per, productDto.getP_discount_per()) &&
                Objects.equals(member_benefit_price, productDto.getMember_benefit_price()) &&
                Objects.equals(p_average_grade, productDto.getP_average_grade()) &&
                Objects.equals(p_brief_text, productDto.getP_brief_text()) &&
                Objects.equals(materials_care_methods, productDto.getMaterials_care_methods()) &&
                Objects.equals(sale_state, productDto.getSale_state()) &&
                Objects.equals(main_image_url, productDto.getMain_image_url()) &&
                Objects.equals(p_season, productDto.getP_season()) &&
                Objects.equals(category_id, productDto.getCategory_id()) &&
                Objects.equals(sale_count, productDto.getSale_count()) &&
                Objects.equals(created_id, productDto.getCreated_id()) &&
                Objects.equals(updated_id, productDto.getUpdated_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, p_name, p_origin_price);
    }

    private ProductDto() {
    }

    public ProductDto(Builder builder) {
        this.product_id = builder.product_id;
        this.p_name = builder.p_name;
        this.p_gender = builder.p_gender;
        this.p_origin_price = builder.p_origin_price;
        this.p_sale_price = builder.p_sale_price;
        this.p_discount_per = builder.p_discount_per;
        this.member_benefit_price = builder.member_benefit_price;
        this.p_average_grade = builder.p_average_grade;
        this.p_brief_text = builder.p_brief_text;
        this.materials_care_methods = builder.materials_care_methods;
        this.sale_state = builder.sale_state;
        this.main_image_url = builder.main_image_url;
        this.p_season = builder.p_season;
        this.category_id = builder.category_id;
        this.sale_count = builder.sale_count;
        this.created_dt = builder.created_dt;
        this.created_id = builder.created_id;
        this.updated_dt = builder.updated_dt;
        this.updated_id = builder.updated_id;
    }

    public static class Builder {
        private String product_id;
        private String p_name;
        private String p_gender;
        private Integer p_origin_price;
        private Integer p_sale_price;
        private Integer p_discount_per;
        private Integer member_benefit_price;
        private Float p_average_grade;
        private String p_brief_text;
        private String materials_care_methods;
        private String sale_state;
        private String main_image_url;
        private String p_season;
        private Integer category_id;
        private Long sale_count;
        private Date created_dt;
        private String created_id;
        private Date updated_dt;
        private String updated_id;

        public Builder() {
        }

        public Builder product_id(String product_id) {
            this.product_id = product_id;
            return this;
        }

        public Builder p_name(String p_name) {
            this.p_name = p_name;
            return this;
        }

        public Builder p_gender(String p_gender) {
            this.p_gender = p_gender;
            return this;
        }

        public Builder p_origin_price(Integer p_origin_price) {
            this.p_origin_price = p_origin_price;
            return this;
        }

        public Builder p_sale_price(Integer p_sale_price) {
            this.p_sale_price = p_sale_price;
            return this;
        }

        public Builder p_discount_per(Integer p_discount_per) {
            this.p_discount_per = p_discount_per;
            return this;
        }

        public Builder member_benefit_price(Integer member_benefit_price) {
            this.member_benefit_price = member_benefit_price;
            return this;
        }

        public Builder p_average_grade(Float p_average_grade) {
            this.p_average_grade = p_average_grade;
            return this;
        }

        public Builder p_brief_text(String p_brief_text) {
            this.p_brief_text = p_brief_text;
            return this;
        }

        public Builder materials_care_methods(String materials_care_methods) {
            this.materials_care_methods = materials_care_methods;
            return this;
        }

        public Builder sale_state(String sale_state) {
            this.sale_state = sale_state;
            return this;
        }

        public Builder main_image_url(String main_image_url) {
            this.main_image_url = main_image_url;
            return this;
        }

        public Builder p_season(String p_season) {
            this.p_season = p_season;
            return this;
        }

        public Builder category_id(Integer category_id) {
            this.category_id = category_id;
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

        public ProductDto build() {
            return new ProductDto(this);
        }
    }


    public String getProduct_id() {
        return product_id;
    }

    public String getP_name() {
        return p_name;
    }

    public String getP_gender() {
        return p_gender;
    }

    public Integer getP_origin_price() {
        return p_origin_price;
    }

    public Integer getP_sale_price() {
        return p_sale_price;
    }

    public Integer getP_discount_per() {
        return p_discount_per;
    }

    public Integer getMember_benefit_price() {
        return member_benefit_price;
    }

    public Float getP_average_grade() {
        return p_average_grade;
    }

    public String getP_brief_text() {
        return p_brief_text;
    }

    public String getMaterials_care_methods() {
        return materials_care_methods;
    }

    public String getSale_state() {
        return sale_state;
    }

    public String getMain_image_url() {
        return main_image_url;
    }

    public String getP_season() {
        return p_season;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public Long getSale_count() {
        return sale_count;
    }

    public Date getCreated_dt() {
        return created_dt;
    }

    public Date getUpdated_dt() {
        return updated_dt;
    }

    public String getCreated_id() {
        return created_id;
    }

    public String getUpdated_id() {
        return updated_id;
    }
}