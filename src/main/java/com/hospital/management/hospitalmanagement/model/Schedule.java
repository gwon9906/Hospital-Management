package com.hospital.management.hospitalmanagement.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "스케줄")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "스케줄ID")
    private Long id;  // scheduleId -> id로 변경

    @Column(name = "근무일자", nullable = false)
    private LocalDateTime workDate; // 근무일자

    @Column(name = "시작시간", nullable = false)
    private LocalDateTime startTime; // 근무 시작 시간

    @Column(name = "종료시간", nullable = false)
    private LocalDateTime endTime; // 근무 종료 시간

    @Column(name = "근무유형", length = 10, nullable = false)
    private String workType; // 근무 유형 ("Day", "Evening", "Night")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "간호사ID") // 간호사ID 설정
    private Nurse nurse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "진료과ID") // 진료과ID 설정
    private Department department;  // Department와의 관계 추가

    // 기본 생성자
    public Schedule() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getWorkDate() {
        return workDate;
    }

    public void setWorkDate(LocalDateTime workDate) {
        this.workDate = workDate;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
