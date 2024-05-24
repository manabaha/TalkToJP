package com.ttjp.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.ttjp.main.vo.MemberVO;
import com.ttjp.main.vo.TeacherVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public interface MyDataController {
											
	String ticketBuy(int ticket, int price);

	String updateMember(MemberVO memberVO);

	String joinTeaInsert(MultipartFile file, String t_id, String t_name, String content, HttpServletRequest request,
			String admin);

	String joinTea(String id, Model model);

	String updateTeacher(MemberVO memberVO, MultipartFile file, String tname, String content, HttpServletRequest request);




	
}
