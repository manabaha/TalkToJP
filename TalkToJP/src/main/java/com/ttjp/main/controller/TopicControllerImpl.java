package com.ttjp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ttjp.main.entity.ttjp_Topic;
import com.ttjp.main.service.MemberService;
import com.ttjp.main.service.TopicService;
import com.ttjp.main.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

	@RequestMapping("/topic")
	@Controller
	public class TopicControllerImpl implements TopicController {

		@Autowired
	    private TopicService topicService;
		
	    //수업 예약시 토픽 리스트
		@Override
	    @GetMapping("/topicList")
	    public String topicList(Model model) {
	    	
			List<ttjp_Topic> allTopic = topicService.getAllTopic();
	    	model.addAttribute("allTopic", allTopic);
			
	        return "topicList";
	    }
		
	
		
		
		
	}


