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
public class ttjp_Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ttjp_member_idx")
    @SequenceGenerator(name = "ttjp_member_idx", sequenceName = "ttjp_member_idx", allocationSize = 1)
    private Long idx;

    @Column
    private String id;
    
    @Column
    private String pass;
    
    @Column
    private String name;
    
    @Column
    private String email;
    
    @Column
    private String hp;
    
    @Column
    private String admin;

    @Column
    private int ticket;
    
    public ttjp_Member() {
        // 기본 생성자
    }

    public ttjp_Member(Long idx, String id, String pass, String name, String email, String hp, String admin, int ticket) {    	
    	this.idx = idx;
        this.id = id;
        this.pass = pass;
        this.name = name;
        this.email = email;
        this.hp = hp;
        this.admin = admin;
        this.ticket = ticket;
    }

    // Getter와 Setter는 생략
}
