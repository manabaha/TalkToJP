package com.ttjp.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttjp.main.dao.LessonRepository;
import com.ttjp.main.entity.ttjp_Lesson;

@Service
public class LessonServiceImpl implements LessonService{

	@Autowired
	LessonRepository lessonRepository;
	
	//레슨 정보 조회(채팅구현용)
    @Override
    public ttjp_Lesson lessonSearch(Long lessonidx) {
    	
    	ttjp_Lesson ttls = lessonRepository.findById(lessonidx).orElse(null);
    	
    	return ttls;
    }
	
}
