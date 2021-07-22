package io.muzoo.ssc.project.backend.checklist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findTasksByEmail(String email);

    List<Task> findTasksByEmailAndCompleted(String email, String completed);

    Task findFirstByTaskDescriptionAndEmail(String taskDesc, String email);
}
