package com.ttjp.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ReservationService {

	String saveReserService(String blocksJson);

	List<String> searchReserService();

	List<String> searchReserTeaService(String id);

	List<String> alreadyReserService(String tid);

	String saveReserStuService(String selectDate, String tid, String tname, int toidx);

}
