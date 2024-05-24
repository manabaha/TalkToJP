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
public class ttjp_Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ttjp_topic_toidx")
    @SequenceGenerator(name = "ttjp_topic_toidx", sequenceName = "ttjp_topic_toidx", allocationSize = 1)
    private Long toidx;

    @Column
    private String totitle;
    
    @Column
    private String tocontent;
    
    @Column
    private String picture1;
    
    @Column
    private String picture2;
    
    @Column
    private String picture3;
    
    @Column
    private String picture4;
    
    @Column
    private String picture5;
    
    @Column
    private String picture6;

    
    
      
    public ttjp_Topic() {
        // 기본 생성자
    }


	public ttjp_Topic(Long toidx, String totitle, String tocontent, String picture1, String picture2,
			String picture3, String picture4, String picture5, String picture6) {
		
		this.toidx = toidx;
		this.totitle = totitle;
		this.tocontent = tocontent;
		this.picture1 = picture1;
		this.picture2 = picture2;
		this.picture3 = picture3;
		this.picture4 = picture4;
		this.picture5 = picture5;
		this.picture6 = picture6;
	}


    

    
    

    // Getter와 Setter는 생략
}
