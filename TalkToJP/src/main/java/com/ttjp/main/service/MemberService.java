package com.ttjp.main.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ttjp.main.entity.ttjp_Lesson;
import com.ttjp.main.entity.ttjp_Member;
import com.ttjp.main.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;

@Service		
public interface MemberService {
    String joinService(MemberVO memberVO);
    String loginService(String id, String pass);
	String logoutService();
	String searchMypage(String id, Model model);
	String searchMypageTea(String id, Model model);
	List<ttjp_Lesson> getLessonsByStudentId(String studentId);
	List<ttjp_Lesson> getLessonsByTeacherId(String teacherId);
	ttjp_Member getName(String Id);
	String checkIdService(String id);

}