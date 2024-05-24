package com.ttjp.main.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ttjp.main.entity.ttjp_teacher_List;

import jakarta.transaction.Transactional;

public interface MyDataRepository extends JpaRepository<ttjp_teacher_List, Long>{
	
	ttjp_teacher_List findByTid(String tid);
	
	@Transactional
	@Modifying
	@Query("UPDATE ttjp_teacher_List t SET t.tname = :tname, t.picture = :picture, t.content = :content WHERE t.idx = :idx")
	int updateTeacher(@Param("idx") Long idx, @Param("tname") String tname, @Param("picture") String picture, @Param("content") String content);

	@Query(value = "SELECT * FROM (SELECT * FROM ttjp_teacher_List ORDER BY idx DESC) WHERE ROWNUM <= 3", nativeQuery = true)
    List<ttjp_teacher_List> findTop3ByOrderByIdxDesc();

	List<ttjp_teacher_List> findAllByOrderByIdxDesc();
}
