package io.muzoo.ssc.project.backend.quesUser;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.auth.User;
import io.muzoo.ssc.project.backend.auth.UserRepository;
import io.muzoo.ssc.project.backend.quesAdmin.QuesAdminRepo;
import io.muzoo.ssc.project.backend.quesAdmin.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;

@RestController
public class QnAController {

    @Autowired
    private QuesUserRepo quesUserRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuesAdminRepo quesAdminRepo;

    private Random RANDOM = new Random();

    private User getCurrentUser(){
        User u = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
            u = userRepository.findFirstByEmail(user.getUsername());
        }
        return u;
    }

    @PostMapping("/api/show_ques")
    private SimpleResponseDTO showQues(HttpServletRequest request){
        List<Question> qlist = new ArrayList<>();
        int tableSize = (int) quesAdminRepo.count();
        int first = 0;
        int second = 0;
        int third = 0;
        while ((first != second ) && (second != third) && (first != third)){
            first = RANDOM.nextInt(tableSize);
            second = RANDOM.nextInt(tableSize);
            third = RANDOM.nextInt(tableSize);
        }
        qlist.add(quesAdminRepo.findFirstByQid(first));
        qlist.add(quesAdminRepo.findFirstByQid(second));
        qlist.add(quesAdminRepo.findFirstByQid(third));
//        request.setAttribute();
        return SimpleResponseDTO.builder()
                .success(true)
                .message("successfully choose 3 questions")
                .build();
    }

    @PostMapping("/api/answer")
    private SimpleResponseDTO answerQ(HttpServletRequest request){

        Map<String, String> qnamap = new HashMap<>();

        qnamap.put(request.getParameter("ques1"), request.getParameter("ans1"));
        qnamap.put(request.getParameter("ques2"), request.getParameter("ans2"));
        qnamap.put(request.getParameter("ques3"), request.getParameter("ans3"));

        for (Map.Entry<String, String> entry : qnamap.entrySet()){
            QnA qna = new QnA();
            qna.setQuestion(entry.getKey());
            qna.setAnswer(entry.getValue());
            qna.setDate(LocalDate.now().toString());
            qna.setEmail(getCurrentUser().getEmail());
            quesUserRepo.save(qna);
        }
        return null;
    }

}