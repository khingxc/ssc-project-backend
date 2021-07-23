package io.muzoo.ssc.project.backend.quesUser;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.SuperController;
import io.muzoo.ssc.project.backend.quesAdmin.QuesAdminRepo;
import io.muzoo.ssc.project.backend.quesAdmin.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

@RestController
public class QnAController extends SuperController {

    @Autowired
    private QuesUserRepo quesUserRepo;

    @Autowired
    private QuesAdminRepo quesAdminRepo;

    private final Random RANDOM = new Random();

    @PostMapping("/api/show_ques")
    private SimpleResponseDTO showQues(HttpServletRequest request) {
        if (getCurrentUser() == null){
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("haven't logged in yet")
                    .build();
        }
        int tableSize = (int) quesAdminRepo.count();
        int first = 0;
        int second = 0;
        int third = 0;
        while ((second == third) && (first == third)) {
            first = RANDOM.nextInt(tableSize);
            second = RANDOM.nextInt(tableSize);
            third = RANDOM.nextInt(tableSize);
        }
        List<Question> questions = quesAdminRepo.findAll();

        request.setAttribute("ques1", questions.get(first).getQtext());
        request.setAttribute("ques2", questions.get(second).getQtext());
        request.setAttribute("ques3", questions.get(third).getQtext());
        return SimpleResponseDTO.builder()
                .success(true)
                .message("successfully choose 3 questions")
                .build();
    }

    @PostMapping("/api/answer")
    private SimpleResponseDTO answerQ(HttpServletRequest request) {
        if (getCurrentUser() == null){
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("haven't logged in yet")
                    .build();
        }
        Map<String, String> qnamap = new HashMap<>();
        qnamap.put((String) request.getAttribute("ques1"), request.getParameter("ans1"));
        qnamap.put((String) request.getAttribute("ques2"), request.getParameter("ans2"));
        qnamap.put((String) request.getAttribute("ques3"), request.getParameter("ans3"));
        for (Map.Entry<String, String> entry : qnamap.entrySet()) {
            QnA qna = new QnA();
            qna.setQuestion(entry.getKey());
            qna.setAnswer(entry.getValue());
            qna.setDate(LocalDate.now().toString());
            qna.setEmail(getCurrentUser().getEmail());
            quesUserRepo.save(qna);
        }
        return SimpleResponseDTO.builder()
                .success(true)
                .message("user answers 3 ques")
                .build();
    }

    @PostMapping("/api/show_date")
    private SimpleResponseDTO showByDate(HttpServletRequest request) {
        String date = request.getParameter("date");
        List<QnA> qnalist = quesUserRepo.findAllByDateAndEmail(
                date, getCurrentUser().getEmail()
        );

        return null;
    }
}
