package com.ttjp.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ttjp_Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ttjp_lesson_idx")
    @SequenceGenerator(name = "ttjp_lesson_idx", sequenceName = "ttjp_lesson_idx", allocationSize = 1)
    private Long idx;

    @Column
    private int topicidx;
    
    @Column
    private String totitle;
    
    @Column
    private String teacherid;
    
    @Column
    private String teachername;
    
    @Column
    private String studentid;
    
    @Column
    private String studentname;
    
    @Column
    private String lessonyear;
    
    @Column
    private String lessondate;
    
    
    public ttjp_Lesson() {
        // 기본 생성자
    }


	public ttjp_Lesson(Long idx, int topicidx, String totitle, String teacherid, String teachername,
			String studentid, String studentname, String lessonyear, String lessondate) {
		
		this.idx = idx;
		this.topicidx = topicidx;
		this.totitle = totitle;
		this.teacherid = teacherid;
		this.teachername = teachername;
		this.studentid = studentid;
		this.studentname = studentname;
		this.lessonyear = lessonyear;
		this.lessondate = lessondate;
	}

    
    
    

    // Getter와 Setter는 생략
}
