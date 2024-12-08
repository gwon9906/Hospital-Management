package com.hospital.management.hospitalmanagement.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "알람_로그")
public class AlarmLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "알람ID")
    private Long id;

    @Column(name = "약품ID", nullable = false)
    private Long stockId;

    @Column(name = "알림내용", nullable = false)
    private String message;

    @Column(name = "생성일자", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
