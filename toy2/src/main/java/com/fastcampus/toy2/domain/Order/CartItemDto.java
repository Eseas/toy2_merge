package com.fastcampus.toy2.domain.Order;

import java.sql.Timestamp;
import java.util.Objects;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CartItemDto {
    private Long crt_id;
    private Integer crt_seq;

    @NotEmpty(message = "상품 정보가 올바르지 않습니다.")
    private String style_num;

    @NotNull(message = "사이즈를 선택해 주시기 바랍니다.")
    private Integer p_size;

    @NotNull(message = "수량을 선택해 주시기 바랍니다.")
    private Integer count;

    private Timestamp crt_item_date;
    private Timestamp crt_item_update;
    private Timestamp created_dt;
    private String created_id;
    private Timestamp updated_dt;
    private String updated_id;

    public CartItemDto() {
    }

    public CartItemDto(Long crt_id, Integer crt_seq, String style_num, Integer p_size, Integer count) {
        this.crt_id = crt_id;
        this.crt_seq = crt_seq;
        this.style_num = style_num;
        this.p_size = p_size;
        this.count = count;
    }

    public Long getCrt_id() {
        return crt_id;
    }

    public Integer getCrt_seq() {
        return crt_seq;
    }

    public String getStyle_num() {
        return style_num;
    }

    public Integer getP_size() {
        return p_size;
    }

    public Integer getCount() {
        return count;
    }

    public Timestamp getCrt_item_date() {
        return crt_item_date;
    }

    public Timestamp getCrt_item_update() {
        return crt_item_update;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemDto that = (CartItemDto) o;
        return Objects.equals(crt_id, that.crt_id) && Objects.equals(crt_seq, that.crt_seq) && Objects.equals(style_num, that.style_num) && Objects.equals(p_size, that.p_size) && Objects.equals(count, that.count) && Objects.equals(crt_item_date, that.crt_item_date) && Objects.equals(crt_item_update, that.crt_item_update);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crt_id, crt_seq, style_num, p_size, count, crt_item_date, crt_item_update);
    }

    @Override
    public String toString() {
        return "CartItemDto{" +
                "crt_id=" + crt_id +
                ", crt_seq=" + crt_seq +
                ", style_num='" + style_num + '\'' +
                ", p_size=" + p_size +
                ", count=" + count +
                ", crt_item_date=" + crt_item_date +
                ", crt_item_update=" + crt_item_update +
                '}';
    }

    public void setCrt_id(Long crt_id) {
        this.crt_id = crt_id;
    }

    public void setCrt_seq(Integer crt_seq) {
        this.crt_seq = crt_seq;
    }

    public void setStyle_num(String style_num) {
        this.style_num = style_num;
    }

    public void setP_size(Integer p_size) {
        this.p_size = p_size;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setCrt_item_update(Timestamp crt_item_update) {
        this.crt_item_update = crt_item_update;
    }

    public void setCrt_item_date(Timestamp crt_item_date) {
        this.crt_item_date = crt_item_date;
    }
}
