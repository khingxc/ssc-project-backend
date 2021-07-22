package io.muzoo.ssc.project.backend.quesAdmin;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.SuperController;
import io.muzoo.ssc.project.backend.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RestController
public class QuestionController extends SuperController {

    @Autowired
    private QuesAdminRepo quesAdminRepo;

    private boolean isAdmin(User user){
        return user.getRole().equals("Admin");
    }

    @PostMapping("/api/add_ques")
    public SimpleResponseDTO addQuestion(HttpServletRequest request){

        String qtext = request.getParameter("qtext");
        if (isAdmin(getCurrentUser())){
            Question q = new Question();
            q.setQtext(qtext);
            q.setDate_add(LocalDate.now().toString());
            quesAdminRepo.save(q);

            return SimpleResponseDTO.builder()
                    .message("successfully add new ques")
                    .success(true)
                    .build();
        } else {
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("failed to add new ques")
                    .build();
        }
    }

    @PostMapping("/api/rmv_ques")
    public SimpleResponseDTO removeQuestion(HttpServletRequest request){
        long qid = Long.parseLong(request.getParameter("qid"));
        if (isAdmin(getCurrentUser())){
            quesAdminRepo.deleteByQid(qid);
            return SimpleResponseDTO.builder()
                    .message("successfully remove ques")
                    .success(true)
                    .build();
        } else {
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("failed to remove ques")
                    .build();
        }
    }
}
