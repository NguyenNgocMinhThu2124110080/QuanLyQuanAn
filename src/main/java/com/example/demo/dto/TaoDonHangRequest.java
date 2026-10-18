package com.example.demo.dto;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// DTO yêu cầu mở bàn và tạo đơn hàng mới
public class TaoDonHangRequest {
    private Integer maBan;
    private Integer maNv;

    public TaoDonHangRequest() {
    }

    public TaoDonHangRequest(Integer maBan, Integer maNv) {
        this.maBan = maBan;
        this.maNv = maNv;
    }

    public Integer getMaBan() {
        return maBan;
    }

    public void setMaBan(Integer maBan) {
        this.maBan = maBan;
    }

    public Integer getMaNv() {
        return maNv;
    }

    public void setMaNv(Integer maNv) {
        this.maNv = maNv;
    }
}
