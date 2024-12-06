package com.hospital.management.hospitalmanagement.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="입원")
public class Bed {
    @Id
    @Column(name = "입원ID", nullable = false)
    private Long id;

    @Column(name = "입원일자", nullable = false)
    private Date dayIn;

    @Column(name = "퇴원일자", nullable = false)
    private Date dayOut;

    @Column(name = "환자ID", nullable = false)
    private Long pId;

    @Column(name = "병상번호", nullable = false)
    private Integer bedNumber;

    @Column(name = "병실번호", nullable = false)
    private Integer roomNumber;

    // 환자 엔티티와 매핑
    @ManyToOne
    @JoinColumn(name = "환자ID", referencedColumnName = "ID", insertable = false, updatable = false)
    private Patient patient;
}
