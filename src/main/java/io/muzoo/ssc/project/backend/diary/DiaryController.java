package io.muzoo.ssc.project.backend.diary;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.SuperController;
import io.muzoo.ssc.project.backend.auth.User;
import io.muzoo.ssc.project.backend.auth.UserRepository;
import io.muzoo.ssc.project.backend.checklist.Task;
import io.muzoo.ssc.project.backend.checklist.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DiaryController extends SuperController {

    @Autowired
    private DiaryRepository diaryRepository;

    @PostMapping("/api/show_diary_date")
    public SimpleResponseDTO showDiaryByDate(HttpServletRequest req){
        String date = req.getParameter("date");
        if (getCurrentUser() == null){
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("haven't logged in yet")
                    .build();
        }
        User currentUser = getCurrentUser();
        Diary diary = diaryRepository.findDiaryByEmailAndDate(currentUser.getEmail(), date);
        req.setAttribute("diary_text", diary.getDiary_text());
        //      Find a way to give the data back to the user

        return SimpleResponseDTO.builder()
                .success(true)
                .message("successfully show all diaries by date")
                .build();
    }

    @PostMapping("/api/create_diary")
    public SimpleResponseDTO createDiary(HttpServletRequest req) {
        String text = req.getParameter("text");
        String nowTime = LocalDate.now().toString();
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("haven't logged in to create task yet")
                    .build();
        }
        if (diaryRepository.findDiaryByDate(nowTime) == null){
            Diary newDiary = new Diary();
            newDiary.setDiary_text(text);
            newDiary.setDate(nowTime);
            newDiary.setEmail(currentUser.getEmail());

            diaryRepository.save(newDiary);
            return SimpleResponseDTO.builder()
                    .success(true)
                    .message("successfully created a diary")
                    .build();
        }  else {
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("user already created a diary for this day!!")
                    .build();
        }
    }

    @PostMapping("/api/show_all_diaries_email")
    public SimpleResponseDTO showAllDiariesByEmail(HttpServletRequest req){
        User currentUser = getCurrentUser();
        List<Diary> diaries = diaryRepository.findDiariesByEmail(currentUser.getEmail());
        List<String> dlist = new ArrayList<>();
        for (Diary diary: diaries) {
            dlist.add(diary.getDiary_text());
        }
        req.setAttribute("diary_email", dlist);
        return SimpleResponseDTO.builder()
                .success(true)
                .message("successfully show all diaries by email")
                .build();
    }

    @PostMapping("/api/rmv_diary_today")
    public SimpleResponseDTO removeDiaryToday(HttpServletRequest req){
        User currentUser = getCurrentUser();
        String nowTime = LocalDate.now().toString();
        Diary diaryThatDay = diaryRepository.findDiaryByEmailAndDate(currentUser.getEmail(), nowTime);
        if (diaryThatDay != null){
            diaryRepository.delete(diaryThatDay);
            return SimpleResponseDTO.builder()
                    .success(true)
                    .message("successfully delete a diary for today")
                    .build();
        }
        return SimpleResponseDTO.builder()
                .success(false)
                .message("Diary for today has not been created yet")
                .build();
    }

    @PostMapping("/api/rmv_diary_o")
    public SimpleResponseDTO removeDiaryOtherDay(HttpServletRequest req){
        User currentUser = getCurrentUser();
        String date = req.getParameter("date");
        Diary diaryThatDay = diaryRepository.findDiaryByEmailAndDate(currentUser.getEmail(), date);
        if (diaryThatDay != null){
            diaryRepository.delete(diaryThatDay);
            return SimpleResponseDTO.builder()
                    .success(true)
                    .message("successfully delete a diary for other day")
                    .build();
        }
        return SimpleResponseDTO.builder()
                .success(false)
                .message("Diary for today has not been created yet")
                .build();
    }

    @PostMapping("/api/edit_prev")
    public SimpleResponseDTO editDiaryOtherDay(HttpServletRequest req){
        String date = req.getParameter("date");
        String text = req.getParameter("text");
        User currentUser = getCurrentUser();
        Diary diary = diaryRepository.findDiaryByEmailAndDate(currentUser.getEmail(), date);
        if (diary != null){
            diary.setDiary_text(text);
            diaryRepository.save(diary);
            return SimpleResponseDTO.builder()
                    .success(true)
                    .message("Diary for date " + date + " has been edited")
                    .build();

        }
        return SimpleResponseDTO.builder()
                .success(false)
                .message("There is no diary for " + date)
                .build();
    }

    @PostMapping("/api/edit_today")
    public SimpleResponseDTO editDiaryToday(HttpServletRequest req){
        String text = req.getParameter("text");
        String date = LocalDate.now().toString();
        User currentUser = getCurrentUser();
        Diary diary = diaryRepository.findDiaryByEmailAndDate(currentUser.getEmail(), date);
        if (diary != null){
            diary.setDiary_text(text);
            diaryRepository.save(diary);
            return SimpleResponseDTO.builder()
                    .success(true)
                    .message("Diary for date " + date + " has been edited")
                    .build();
        }
        return SimpleResponseDTO.builder()
                .success(false)
                .message("There is no diary for " + date)
                .build();
    }


}


