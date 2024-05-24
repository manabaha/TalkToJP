package com.ttjp.main.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ttjp.main.FileUploadService;
import com.ttjp.main.entity.ttjp_Member;
import com.ttjp.main.service.MemberService;
import com.ttjp.main.service.MyDataService;
import com.ttjp.main.vo.MemberVO;
import com.ttjp.main.vo.TeacherVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

	@RequestMapping("/mydata")
	@Controller
	public class MyDataControllerImpl implements MyDataController {

		@Autowired
	    private MyDataService myDataService;
		
		@Autowired
	    private MemberService memberService;
		
	    @Autowired
	    private FileUploadService fileUploadService; 
	    
	    @Autowired
	    HttpSession session;
	    
	    //티켓구매
		@Override
  		@PostMapping(value = "/ticketBuy", produces = "text/plain; charset=UTF-8")
		@ResponseBody
	    public String ticketBuy(@RequestParam("ticket") int ticket, @RequestParam("price") int price){
																									//모델지움
	    	String sessionCheck = null;     
	    	sessionCheck = (String)session.getAttribute("id");
	    	
		    	if(sessionCheck == null) {
		    		return "미로그인";
		    	}else {
		    		return myDataService.ticketBuyService(sessionCheck, ticket, price);		
		    	}
	    	
	    }

		//선생님 등록 폼 이동
  		@Override
  		@GetMapping("/joinTea")
  		public String joinTea(@RequestParam("id") String id, Model model) {
  			 			
  			model.addAttribute("id", id);
  			
  			return "joinTea";
  		}
		
  		//선생님 등록
  		@Override
  		@PostMapping("/joinTeaInsert")
  		public String joinTeaInsert(@RequestParam("picture") MultipartFile file,
  	            @RequestParam("tid") String tid, @RequestParam("tname") String tname,
  	            @RequestParam("content") String content, HttpServletRequest request, @RequestParam("admin") String admin) {
  				
  			//member테이블 admin2로 업데이트
  			myDataService.stepUpTea(tid, admin);			
  			
  			//teacher테이블 insert 및 파일업로드
  			fileUploadService.serviceInsertTeacher(file, tid, tname, content, request);
  			
  					//어드민 업데이트 드가조ㅑ
  			return "redirect:/main";
  		}
		
  		
		//마이페이지 회원정보 수정
 		@Override
 		@PostMapping("/updateMember")
 		public String updateMember(MemberVO memberVO) {
 			 			
 			//회원정보 업데이트
 			return myDataService.updateService(memberVO);
 		}
  		
 		//선생님 마이페이지 회원정보 수정
 		@Override
 		@PostMapping("/updateTeacher")
 		public String updateTeacher(MemberVO memberVO, @RequestParam("new_picture") MultipartFile file,
 				@RequestParam("tname") String tname, @RequestParam("content") String content, HttpServletRequest request) {

 			System.out.println("마이페이지선생님프로필수정파일체크 " + file);
 			
 			//회원정보 업데이트
 			myDataService.updateService(memberVO);
  			//teacher테이블 update 및 파일업로드
 			if(file.isEmpty()) {
 				System.out.println("선생수정파일x");
 				myDataService.updateTeaService(memberVO.getId(), tname, content);
 			}else {
 				System.out.println("선생수정파일o");
 				fileUploadService.serviceUpdateTeacher(file, memberVO.getId(), tname, content, request);
 			}
 			
  			return "redirect:/main";
  			
 		}
 		
 		
	}


