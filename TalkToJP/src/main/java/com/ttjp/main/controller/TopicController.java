package com.ttjp.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.ttjp.main.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public interface TopicController {

	String topicList(Model model);

	
}
