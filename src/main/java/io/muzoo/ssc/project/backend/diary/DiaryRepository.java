package io.muzoo.ssc.project.backend.diary;

import io.muzoo.ssc.project.backend.checklist.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
//    List<Task> findTasksByEmail(String email);
//
//    List<Task> findTasksByEmailAndCompleted(String email, String completed);
//
//    Task findFirstByTaskDescription(String taskDesc);


}
