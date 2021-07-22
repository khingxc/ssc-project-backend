package io.muzoo.ssc.project.backend.diary;

import io.muzoo.ssc.project.backend.checklist.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findDiariesByEmail(String email);

    Diary findDiaryByDate(String Date);

    Diary findDiaryByEmailAndDate(String email, String date);

}
