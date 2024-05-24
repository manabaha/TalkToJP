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
public class ttjp_teacher_List {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ttjp_teacher_List_idx")
    @SequenceGenerator(name = "ttjp_teacher_List_idx", sequenceName = "ttjp_teacher_List_idx", allocationSize = 1)
    private Long idx;
	
	@Column
	private String tid;
	
	@Column
    private String tname;
	
	@Column
    private String content;
	
	@Column
    private String picture;
	
	@Column
    private String possible_Date;
    
	public ttjp_teacher_List() {
		//기본 생성자
	}

	public ttjp_teacher_List(Long idx, String tid, String tname, String content, String picture, String possible_Date) {
		
		this.idx = idx;
		this.tid = tid;
		this.tname = tname;
		this.content = content;
		this.picture = picture;
		this.possible_Date = possible_Date;
	}
		
}
