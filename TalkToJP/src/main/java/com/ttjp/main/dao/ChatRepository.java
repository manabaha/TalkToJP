package com.ttjp.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttjp.main.entity.ttjp_chat;

public interface ChatRepository extends JpaRepository<ttjp_chat, Long> {
	List<ttjp_chat> findByLessonIdxOrderByChatTime(Long lessonIdx);
}
