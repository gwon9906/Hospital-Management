package repository;

import model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {
    // 간단한 쿼리 메서드 작성
    Nurse findByName(String name); // 이름으로 간호사 검색
}