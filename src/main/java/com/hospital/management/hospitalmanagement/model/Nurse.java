package com.hospital.management.hospitalmanagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "간호사")
@SequenceGenerator(name = "nurse_seq", sequenceName = "NURSE_SEQ", allocationSize = 1)
public class Nurse {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "nurse_seq")
    @Column(name = "간호사ID")
    private Long id;  // nurseId -> id로 변경

    @Column(name = "이름", nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)  // Lazy 로딩 설정
    @JoinColumn(name = "진료과ID", nullable = false)  // 진료과ID 필수로 설정
    private Department department;

    @OneToMany(mappedBy = "nurse")  // 간호사와 스케줄의 관계 설정
    private List<Schedule> schedules;

    // Getter와 Setter 추가
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
