package io.muzoo.ssc.project.backend.Question;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "tbl_ques")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long questionID; //increase

    private String email; //whoami

    private String question;

    private String answer;

    private String date;
}
