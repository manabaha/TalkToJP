package com.ttjp.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttjp.main.entity.ttjp_Topic;

public interface TopicRepository extends JpaRepository<ttjp_Topic, Long>{
	
	List<ttjp_Topic> findAllByOrderByToidxAsc();
	
}
