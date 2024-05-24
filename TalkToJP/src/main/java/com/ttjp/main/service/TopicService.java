package com.ttjp.main.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ttjp.main.entity.ttjp_Topic;
import com.ttjp.main.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;

@Service		
public interface TopicService {

	List<ttjp_Topic> getAllTopic();
	
	ttjp_Topic getTopicImages(Long toidx);


}