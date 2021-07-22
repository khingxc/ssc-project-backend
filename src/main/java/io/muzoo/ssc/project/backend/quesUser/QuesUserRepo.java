package io.muzoo.ssc.project.backend.quesUser;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuesUserRepo extends JpaRepository<QnA, Long> {

    List<QnA> findAllByDateAndEmail(String Date, String email);

}
