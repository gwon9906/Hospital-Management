package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientsController {


    @GetMapping("/patients/search")
    public String searchPatients(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String id,
                                 @RequestParam(required = false) String birthdate,
                                 Model model) {
        // 환자 검색 로직 (DB 연동 부분)
        List<String> patients = new ArrayList<>();

        // 간단한 예제 로직: 모든 필드가 입력되지 않으면 결과 없음
        if ((name != null && !name.isEmpty()) ||
                (id != null && !id.isEmpty()) ||
                (birthdate != null && !birthdate.isEmpty())) {
            patients.add("홍길동 (ID: 12345)");
            patients.add("김철수 (ID: 67890)");
        }

        // 검색 결과를 모델에 추가
        model.addAttribute("patients", patients);

        // 같은 페이지로 이동
        return "patients";
    }
}
