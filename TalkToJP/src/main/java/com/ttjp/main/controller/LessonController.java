package com.ttjp.main.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ttjp.main.entity.ttjp_chat;

import jakarta.servlet.http.HttpSession;

@Controller
public interface LessonController {

	String showClassRoom(Model model, Long lessonIdx, HttpSession session);

	List<ttjp_chat> getMessages(Long lessonIdx);

	void sendMessage(Long lessonIdx, String teacherId, String studentId, String message, String senderid, String sendername);

}
