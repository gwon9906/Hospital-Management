package com.hospital.management.hospitalmanagement.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "진료과")
@SequenceGenerator(name = "dept_seq", sequenceName = "DEPT_SEQ", allocationSize = 1)
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept_seq")
    @Column(name = "진료과ID")
    private Long id;  // departmentId -> id로 이름 변경

    @Column(name = "진료과명", nullable = false, length = 50)
    private String name;  // departmentName -> name으로 이름 변경

    @OneToMany(mappedBy = "department")
    private List<Nurse> nurses;

    @OneToMany(mappedBy = "department")
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

    public List<Nurse> getNurses() {
        return nurses;
    }

    public void setNurses(List<Nurse> nurses) {
        this.nurses = nurses;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
