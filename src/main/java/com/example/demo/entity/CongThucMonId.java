package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

// PHáº¦N Cá»¦A: NGUYá»„N NGá»ŒC MINH THÆ¯
@Embeddable
public class CongThucMonId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "ma_mon")
    private Integer maMon;

    @Column(name = "nl_id")
    private Integer nlId;

    public CongThucMonId() {
    }

    public CongThucMonId(Integer maMon, Integer nlId) {
        this.maMon = maMon;
        this.nlId = nlId;
    }

    public Integer getMaMon() {
        return maMon;
    }

    public void setMaMon(Integer maMon) {
        this.maMon = maMon;
    }

    public Integer getNlId() {
        return nlId;
    }

    public void setNlId(Integer nlId) {
        this.nlId = nlId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CongThucMonId that = (CongThucMonId) o;
        return Objects.equals(maMon, that.maMon) && Objects.equals(nlId, that.nlId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maMon, nlId);
    }
}

