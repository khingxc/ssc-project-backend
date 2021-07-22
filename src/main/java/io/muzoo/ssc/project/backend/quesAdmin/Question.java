package io.muzoo.ssc.project.backend.quesAdmin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "tbl_ques_admin")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private long qid;

    private String qtext;

    private String date_add;

}
