package io.muzoo.ssc.project.backend.quesUser;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="tbl_ques_user")
public class QnA {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private long qaid;

    private String email;

    private String question;

    private String answer;

    private String date;
}
