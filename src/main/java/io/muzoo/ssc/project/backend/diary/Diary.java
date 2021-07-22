package io.muzoo.ssc.project.backend.diary;

import io.muzoo.ssc.project.backend.SimpleResponseDTO;
import io.muzoo.ssc.project.backend.auth.User;
import io.muzoo.ssc.project.backend.checklist.Task;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name= "tbl_diary")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private long diary_id; //increase

    private String email; //whoami

    private String diary_text; //text

    private String date;


}
