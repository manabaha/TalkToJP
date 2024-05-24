package com.ttjp.main.service;

import org.springframework.stereotype.Service;

import com.ttjp.main.entity.ttjp_Lesson;

@Service
public interface LessonService {

	ttjp_Lesson lessonSearch(Long lessonidx);

}
