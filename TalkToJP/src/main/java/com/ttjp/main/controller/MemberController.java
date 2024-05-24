package com.ttjp.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ttjp.main.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public interface MemberController {
	String joinMember(MemberVO memberVO);
	String myPage(Model model);
	String loginMember(String id, String pass);
	String logoutMember();
	String joinForm();
	String myLessonStu(Model model, HttpSession session);
	String myLessonTea(Model model, HttpSession session);
	String checkId(String id);
	
}
