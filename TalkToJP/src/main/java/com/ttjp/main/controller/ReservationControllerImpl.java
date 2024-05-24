package com.ttjp.main.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttjp.main.entity.ttjp_teacher_List;
import com.ttjp.main.service.MemberService;
import com.ttjp.main.service.MyDataService;
import com.ttjp.main.service.ReservationService;

import jakarta.servlet.http.HttpServletResponse;

@RequestMapping("/reser")
@Controller
public class ReservationControllerImpl implements ReservationController{
		
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	MyDataService myDataService;
	
	//선생님 본인 일정 설정 정보 조회
	@Override
	@GetMapping("/searchSchedule")
	@ResponseBody
    public List<String> searchSchedule() {
    			
        return reservationService.searchReserService();
    }
	
	//선생님의 일정 설정 조회하여 학생 예약 화면에 업데이트 하는 용도(ajax)
	@Override
	@GetMapping("/searchTeaScheTostu")
	@ResponseBody
    public List<String> searchTeaScheTostu(@RequestParam("tid") String tid) {
    			
		System.out.println(tid);
		
        return reservationService.searchReserTeaService(tid);
    }
	
	//레슨 테이블 조회하여 선생님의 이미 예약된 일정 정보를 학생 예약 화면에 업데이트 하는 용도(ajax)
	@Override
	@GetMapping("/alreadyReser")
	@ResponseBody
    public List<String> alreadyReser(@RequestParam("tid") String tid) {
    			
        return reservationService.alreadyReserService(tid);
    }
	
	@Override
	@PostMapping("/saveSchedule")
    public String saveReservation(@RequestBody String[] blocks) {
        // JSON 배열로 받은 선택된 블럭의 정보를 문자열로 변환하여 DB에 저장합니다.
        String blocksJson = String.join(",", blocks);
        
        System.out.println(blocksJson + "블럭");
           
        return reservationService.saveReserService(blocksJson);
    }
	
	//학생의 수업 예약에 필요한 선생님 리스트 조회
	@Override
    @GetMapping("/teacherList")
    public String yoyakuStu(Model model, @RequestParam("toidx") int toidx) {
             
    	List<ttjp_teacher_List> allTeachers = myDataService.getAllTeachers();
        model.addAttribute("tealist", allTeachers);
    	model.addAttribute("toidx", toidx);
        
        return "teacherList";
    }
	
	@Override
	@GetMapping("/yoyakuStu")
	public String yoyakuStuView(@RequestParam("tid") String tid, @RequestParam("tname") String tname,
								@RequestParam("toidx") int toidx, Model model) {
		
		model.addAttribute("tid", tid);
		model.addAttribute("tname", tname);
		model.addAttribute("toidx", toidx);
		
		return "yoyakuStu";
	}
	
	@Override
	@PostMapping(value = "/saveLesson", produces = "text/plain; charset=UTF-8")
	@ResponseBody
    public String saveLesson(@RequestParam("selectDate") String selectDate, @RequestParam("tid") String tid,
    		 				 @RequestParam("tname") String tname, @RequestParam("toidx") int toidx) {
        // JSON 배열로 받은 선택된 블럭의 정보를 문자열로 변환하여 DB에 저장합니다.
        
		System.out.println(tname + "선생이름");
        System.out.println(selectDate + "날짜정보 체크");
        System.out.println(toidx + "토픽인덱스체크");
     
        String result = null;
        PrintWriter out = null;
		
				//티켓차감실패 or 티켓차감성공
        result = myDataService.ticketMinusService();
        
        if(result.equals("티켓차감성공")) {
        		//예약성공
        	result = reservationService.saveReserStuService(selectDate, tid, tname, toidx); 
        }
        
        return result;
        
    }
	
}
