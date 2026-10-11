package com.example.demo.entity;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;
@Embeddable
@Data
public class CongThucMonId implements Serializable {
    @jakarta.persistence.Column(name = "ma_mon")
    private Integer maMon;
    
    @jakarta.persistence.Column(name = "nl_id")
    private Integer nlId;
}
