package com.fastcampus.toy2.domain.Product;

import java.sql.Timestamp;
import java.util.Objects;

public class ProductImageDto {
    private String style_num;
    private Integer p_photo_num;
    private String photo_url;
    private Integer width;
    private Integer height;
    private Integer size;
    private char using_state;
    private Integer show_priority;
    private Timestamp created_at;
    private Timestamp updated_at;

    public String getStyle_num() {
        return style_num;
    }

    public void setStyle_num(String style_num) {
        this.style_num = style_num;
    }

    public Integer getP_photo_num() {
        return p_photo_num;
    }

    public void setP_photo_num(Integer p_photo_num) {
        this.p_photo_num = p_photo_num;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public char getUsing_state() {
        return using_state;
    }

    public void setUsing_state(char using_state) {
        this.using_state = using_state;
    }

    public Integer getShow_priority() {
        return show_priority;
    }

    public void setShow_priority(Integer show_priority) {
        this.show_priority = show_priority;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImageDto that = (ProductImageDto) o;
        return using_state == that.using_state && Objects.equals(style_num, that.style_num) && Objects.equals(p_photo_num, that.p_photo_num) && Objects.equals(photo_url, that.photo_url) && Objects.equals(width, that.width) && Objects.equals(height, that.height) && Objects.equals(size, that.size) && Objects.equals(show_priority, that.show_priority) && Objects.equals(created_at, that.created_at) && Objects.equals(updated_at, that.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(style_num, p_photo_num, photo_url, width, height, size, using_state, show_priority, created_at, updated_at);
    }

    @Override
    public String toString() {
        return "ProductImageDto{" +
                "style_num='" + style_num + '\'' +
                ", p_photo_num=" + p_photo_num +
                ", photo_url='" + photo_url + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", size=" + size +
                ", using_state=" + using_state +
                ", show_priority=" + show_priority +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
