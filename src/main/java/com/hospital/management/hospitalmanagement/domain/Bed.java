package com.hospital.management.hospitalmanagement.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="압원")
public class Bed {
    @Id
    @Column(name = "입원ID", nullable = false)
    private Long id;
}
