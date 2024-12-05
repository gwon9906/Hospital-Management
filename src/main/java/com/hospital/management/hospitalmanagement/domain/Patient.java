package com.hospital.management.hospitalmanagement.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "전화번호", nullable = false)
    private String phone;
}

