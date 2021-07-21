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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long taskID; //increase

    private String email; //whoami

    private String taskDescription; //text

    private String completed; //done

    private String date; //date (not implemented yet)
}
