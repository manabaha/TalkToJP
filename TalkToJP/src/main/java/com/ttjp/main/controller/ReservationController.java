package com.ttjp.main.controller;

import java.util.List;

import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletResponse;

public interface ReservationController {

	List<String> searchSchedule();

	String saveReservation(String[] blocks);

	List<String> searchTeaScheTostu(String tid);

	String yoyakuStuView(String tid, String tname, int toidx, Model model);

	String yoyakuStu(Model model, int toidx);

	List<String> alreadyReser(String tid);

	String saveLesson(String selectDate, String tid, String tname, int toidx);



}
