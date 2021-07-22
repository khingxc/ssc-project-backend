package io.muzoo.ssc.project.backend.quesUser;

import io.muzoo.ssc.project.backend.auth.User;
import io.muzoo.ssc.project.backend.auth.UserRepository;
import io.muzoo.ssc.project.backend.quesAdmin.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

@Repository
public interface QuesUserRepo extends JpaRepository<QnA, Long> {



}
