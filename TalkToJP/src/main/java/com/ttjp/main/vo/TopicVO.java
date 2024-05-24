package com.ttjp.main.vo;


import com.ttjp.main.entity.ttjp_Topic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicVO {

    private String totitle;
    private String tocontent;
    private String pircture1;
    private String pircture2;
    private String pircture3;
    private String pircture4;
    private String pircture5;
    private String pircture6;
	
    
	public ttjp_Topic toEntity() {
		
		return new ttjp_Topic(null, totitle, tocontent, pircture1, pircture2, pircture3, pircture4, pircture5, pircture6);
	}
	


}
