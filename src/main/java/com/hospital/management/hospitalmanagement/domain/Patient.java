package com.hospital.management.hospitalmanagement.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="환자")
public class Patient {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "이름", nullable = false)
    private String name;

    @Column(name = "성별", nullable = false)
    private String gender;

    @Column(name = "생년월일", nullable = false)
    private String birthDate; // String으로 받아서 날짜 형식 변환

    @Column(name = "전화번호", nullable = false)
    private String phone;

    // 저장 전 생년월일 형식을 변환
    @PrePersist
    @PreUpdate
    public void formatBirthDate() {
        if (this.birthDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
            LocalDate date = LocalDate.parse(this.birthDate, formatter);
            this.birthDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));  // 4자리 연도로 포맷
        }
    }
}