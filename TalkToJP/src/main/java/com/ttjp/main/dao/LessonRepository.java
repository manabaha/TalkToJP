package com.ttjp.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ttjp.main.entity.ttjp_Lesson;

public interface LessonRepository extends JpaRepository<ttjp_Lesson, Long>{
	
	List<ttjp_Lesson> findByTeacheridOrderByIdxDesc(String teacherid);
	
	List<ttjp_Lesson> findByStudentidOrderByIdxDesc(String studentId);

}
