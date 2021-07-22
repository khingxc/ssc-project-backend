package io.muzoo.ssc.project.backend.diary;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name= "tbl_diary")
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private long diaryID; //increase

    private long email; //whoami

    private String diary_text; //text

    private String date;
}
