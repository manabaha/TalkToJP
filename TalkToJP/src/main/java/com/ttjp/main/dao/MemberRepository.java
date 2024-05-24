package com.ttjp.main.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ttjp.main.entity.ttjp_Member;
import com.ttjp.main.vo.MemberVO;

public interface MemberRepository extends JpaRepository<ttjp_Member, Long>{
	
	ttjp_Member findByIdAndPass(String id, String pass);
	
	ttjp_Member findById(String id);
	
	ttjp_Member findByNameAndHp(String name, String hp);
	
}
