package com.hospital.management.hospitalmanagement.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "진료")
public class MedicalRecord {
    @Id
    @Column(name = "진료ID", nullable = false)
    private Long id;

    @Column(name = "진료일자", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "수납비", nullable = false)
    private int fee;

    @Column(name = "병명", nullable = false)
    private String diagnosis;

    @Column(name = "치료방법", nullable = false)
    private String prescription;

    // 환자 엔티티와 매핑
    @ManyToOne
    @JoinColumn(name = "환자ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private Patient patient;
}
