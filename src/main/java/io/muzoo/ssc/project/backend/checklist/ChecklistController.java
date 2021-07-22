package io.muzoo.ssc.project.backend.checklist;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.SuperController;
import io.muzoo.ssc.project.backend.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ChecklistController extends SuperController {

    /**
     * HOW DO WE GET TASK BACK TO USER???
     * need frontend
     */

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("/api/create_task")
    public SimpleResponseDTO createTask(HttpServletRequest req){
        String text = req.getParameter("text");

        User currentUser = getCurrentUser();
        if (currentUser == null){
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("haven't logged in yet")
                    .build();
        }
        Task newTask = new Task();

        newTask.setEmail(currentUser.getEmail());
        newTask.setTaskDescription(text);
        newTask.setCompleted("false");
        newTask.setDate(LocalDate.now().toString());

        taskRepository.save(newTask);
        return SimpleResponseDTO.builder()
                .success(true)
                .message("successfully created a task")
                .build();
    }


    @PostMapping("/api/show_current_tasks")
    public SimpleResponseDTO showCurrentTasks(HttpServletRequest req){

        User currentUser = getCurrentUser();
        if (currentUser == null){
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("haven't logged in yet")
                    .build();
        }
        List<Task> tasks = taskRepository.findTasksByEmailAndCompleted(currentUser.getEmail(), "false");

//        req.setAttribute("current_tasks", tasks);
//      Find a way to give the data back to the user
        return SimpleResponseDTO.builder()
                .success(true)
                .message("successfully show user their current tasks")
                .build();
    }


    @PostMapping("/api/show_all_tasks")
    public SimpleResponseDTO showAllTasks(HttpServletRequest req){
        User currentUser = getCurrentUser();
        if (currentUser == null){
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("haven't logged in yet")
                    .build();
        }
        List<Task> tasks = taskRepository.findTasksByEmail(currentUser.getEmail());

//      Find a way to give the data back to the user
        return SimpleResponseDTO.builder()
                .success(true)
                .message("successfully show user all of their tasks")
                .build();
    }

    @PostMapping("/api/finish_task")
    public SimpleResponseDTO completeTask(HttpServletRequest req){
        String text = req.getParameter("text");

        if (getCurrentUser() == null){
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("haven't logged in yet")
                    .build();
        }
        Task task = taskRepository.findFirstByTaskDescriptionAndEmail(
                text, getCurrentUser().getEmail()
        );
        task.setCompleted("true");

//      Find a way to give the data back to the user
        return SimpleResponseDTO.builder()
                .success(true)
                .message("successfully change task to completed")
                .build();
    }

}
