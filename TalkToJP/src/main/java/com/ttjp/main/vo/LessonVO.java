package com.ttjp.main.vo;


import com.ttjp.main.entity.ttjp_Lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LessonVO {

	private int topicidx;
	private String totitle;
    private String teacherid;
    private String teachername;
    private String studentid;
    private String studentname;
    private String lessonyear;
    private String lessondate;
    
	public ttjp_Lesson toEntity() {
		
		return new ttjp_Lesson(null, topicidx, totitle, teacherid, teachername, studentid, studentname, lessonyear, lessondate);
	}
	


}
