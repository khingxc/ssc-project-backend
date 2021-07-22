package io.muzoo.ssc.project.backend.checklist;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.auth.User;
import io.muzoo.ssc.project.backend.auth.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ChecklistController {

    /**
     * HOW DO WE GET TASK BACK TO USER???
     * need frontend
     */

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    private User getCurrentUser(){
        User u = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) principal;
            u = userRepository.findFirstByEmail(user.getUsername());
        }
        return u;
    }

    @PostMapping("/api/create_task")
    public SimpleResponseDTO createTask(HttpServletRequest req){
        String text = req.getParameter("text");
        String completed = req.getParameter("done");

        User currentUser = getCurrentUser();
        if ( currentUser == null){
            return SimpleResponseDTO.builder()
                    .success(false)
                    .message("haven't logged in to create task yet")
                    .build();
        }
        Task newTask = new Task();

        newTask.setEmail(currentUser.getEmail());
        newTask.setTaskDescription(text);
        newTask.setCompleted(completed);
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
        Task task = taskRepository.findFirstByTaskDescription(text);
        task.setCompleted("true");

//      Find a way to give the data back to the user
        return SimpleResponseDTO.builder()
                .success(true)
                .message("successfully change task to completed")
                .build();
    }

//    UserService userService = UserService.getInstance();
//    List<User> userList = userService.listAll();
//            req.setAttribute("all_users", userList);
//
//    RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/home.jsp");
//            requestDispatcher.include(req, resp);

}
