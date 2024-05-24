package com.ttjp.main.vo;

import com.ttjp.main.entity.ttjp_Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberVO {

	private String id;
    private String pass;
    private String name;
    private String email;
    private String hp;
    private String admin;
	private int ticket;
    
	public ttjp_Member toEntity() {
		
		return new ttjp_Member(null, id, pass, name, email, hp, admin, 0);
	}
	
	public ttjp_Member toTeaUpdateEntity() {
		
		return new ttjp_Member(null, id, pass, name, email, hp, null, 0);
	}


}
