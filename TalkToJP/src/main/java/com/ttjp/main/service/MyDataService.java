package com.ttjp.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ttjp.main.entity.ttjp_teacher_List;
import com.ttjp.main.vo.MemberVO;
import com.ttjp.main.vo.TeacherVO;

@Service		
public interface MyDataService {
	
    String ticketBuyService(String id, int ticket, int price);

	String updateService(MemberVO memberVO);

	String joinTeacher(TeacherVO teacherVO);

	String stepUpTea(String id, String admin);

	String updateTeaService(String tid, String tname, String content);

	List<ttjp_teacher_List> getTop3Teachers();

	List<ttjp_teacher_List> getAllTeachers();

	String ticketMinusService();
}