package com.hospital.management.hospitalmanagement.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "재고") // 테이블 이름 "재고"
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "약품ID", nullable = false) // 약품ID 매핑
    private Long id;

    @Column(name = "약품명", nullable = false, length = 255) // 약품명 매핑
    private String name;

    @Column(name = "수량", nullable = false) // 수량 매핑
    private int quantity;

    @Column(name = "유효기간", nullable = false) // 유효기간 매핑
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @Column(name = "보관위치", length = 255) // 보관위치 매핑
    private String location;

    // Getter 및 Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
