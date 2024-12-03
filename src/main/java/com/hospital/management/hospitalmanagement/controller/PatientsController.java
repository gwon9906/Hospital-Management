//package controller;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class PatientsController {
//
//    @GetMapping("/patients/search")
//    public List<Patient> searchPatients(
//            @RequestParam(required = false) String name,
//            @RequestParam(required = false) String id,
//            @RequestParam(required = false) String birthdate) {
//
//        // 더미 데이터베이스
//        List<Patient> allPatients = List.of(
//                new Patient("John Doe", "123", "1990-01-01"),
//                new Patient("Jane Smith", "456", "1992-02-02"),
//                new Patient("Alice Johnson", "789", "1985-03-03")
//        );
//
//        // 검색 필터 적용
//        List<Patient> result = new ArrayList<>();
//        for (Patient patient : allPatients) {
//            if ((name == null || patient.getName().contains(name)) &&
//                    (id == null || patient.getId().equals(id)) &&
//                    (birthdate == null || patient.getBirthdate().equals(birthdate))) {
//                result.add(patient);
//            }
//        }
//        return result;
//    }
//}
//
//// 환자 객체 클래스
//class Patient {
//    private String name;
//    private String id;
//    private String birthdate;
//
//    // 생성자
//    public Patient(String name, String id, String birthdate) {
//        this.name = name;
//        this.id = id;
//        this.birthdate = birthdate;
//    }
//
//    // Getter/Setter
//    public String getName() {
//        return name;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public String getBirthdate() {
//        return birthdate;
//    }
//}
