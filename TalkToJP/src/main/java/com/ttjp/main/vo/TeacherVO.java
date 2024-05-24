package com.ttjp.main.vo;

import com.ttjp.main.entity.ttjp_teacher_List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TeacherVO {

	private String tid;
    private String tname;
    private String content;
    private String picture;
	
	public ttjp_teacher_List toEntity() {
		
		return new ttjp_teacher_List(null, tid, tname, content, picture, "");
	}
	
}
