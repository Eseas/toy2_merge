package com.fastcampus.toy2.domain.Order;

import java.sql.Timestamp;
import java.util.Objects;

public class CartItemHistoryDto {
    private Long crt_id;
    private Integer crt_seq;
    private String mbr_id;
    private String style_num;
    private Integer p_size;
    private Integer count;
    private Timestamp hist_date;
    private Timestamp hist_update;
    private char state;
    private Timestamp created_dt;
    private String created_id;
    private Timestamp updated_dt;
    private String updated_id;

    public CartItemHistoryDto() {
    }

    public CartItemHistoryDto(Long crt_id, Integer crt_seq, String mbr_id, String style_num, Integer p_size, Integer count, Timestamp hist_date, Timestamp hist_update, char state) {
        this.crt_id = crt_id;
        this.crt_seq = crt_seq;
        this.mbr_id = mbr_id;
        this.style_num = style_num;
        this.p_size = p_size;
        this.count = count;
        this.hist_date = hist_date;
        this.hist_update = hist_update;
        this.state = state;
    }

    public Long getCrt_id() {
        return crt_id;
    }

    public void setCrt_id(Long crt_id) {
        this.crt_id = crt_id;
    }

    public Integer getCrt_seq() {
        return crt_seq;
    }

    public void setCrt_seq(Integer crt_seq) {
        this.crt_seq = crt_seq;
    }

    public String getMbr_id() {
        return mbr_id;
    }

    public void setMbr_id(String mbr_id) {
        this.mbr_id = mbr_id;
    }

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Timestamp getHist_date() {
        return hist_date;
    }

    public void setHist_date(Timestamp hist_date) {
        this.hist_date = hist_date;
    }

    public Timestamp getHist_update() {
        return hist_update;
    }

    public void setHist_update(Timestamp hist_update) {
        this.hist_update = hist_update;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemHistoryDto that = (CartItemHistoryDto) o;
        return state == that.state && Objects.equals(crt_id, that.crt_id) && Objects.equals(crt_seq, that.crt_seq) && Objects.equals(mbr_id, that.mbr_id) && Objects.equals(style_num, that.style_num) && Objects.equals(p_size, that.p_size) && Objects.equals(count, that.count) && Objects.equals(hist_date, that.hist_date) && Objects.equals(hist_update, that.hist_update);
    }

    @Override
    public int hashCode() {
        return Objects.hash(crt_id, crt_seq, mbr_id, style_num, p_size, count, hist_date, hist_update, state);
    }

    @Override
    public String toString() {
        return "CartItemHistoryDto{" +
                "crt_id=" + crt_id +
                ", crt_seq=" + crt_seq +
                ", mbr_id='" + mbr_id + '\'' +
                ", style_num='" + style_num + '\'' +
                ", p_size=" + p_size +
                ", count=" + count +
                ", hist_date=" + hist_date +
                ", hist_update=" + hist_update +
                ", state=" + state +
                '}';
    }
}
