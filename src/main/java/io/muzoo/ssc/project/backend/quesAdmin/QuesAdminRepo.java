package io.muzoo.ssc.project.backend.quesAdmin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface QuesAdminRepo extends JpaRepository<Question, Long> {

    Question findFirstByQid(long qid);
//
//    Question findFirstByQtext(String qtext);

    void deleteByQid(long qid);
}
