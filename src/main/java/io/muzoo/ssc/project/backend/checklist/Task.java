package io.muzoo.ssc.project.backend.checklist;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "tbl_tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private long task_id; //increase

    private String email;

    private String taskDescription; //text

    private String completed; //done

    private String date; //date
}
