package com.hospital.management.hospitalmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Nurse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String gender;
    private String phoneNumber;
    private LocalDate birthDate;
    private String address;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Getters and Setters

}
