package com.ttjp.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ttjp.main.entity.ttjp_Lesson;
import com.ttjp.main.service.MemberService;
import com.ttjp.main.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

	@RequestMapping("/member")
	@Controller
	public class MemberControllerImpl implements MemberController {

		@Autowired
	    private MemberService memberService;
		
	    @Autowired
	    private HttpSession session;
		
	    //회원가입 폼으로 이동
	    @Override
		@GetMapping("/joinForm")
	    public String joinForm() {
	   	        
	        return "join";
	    }
	    
	    //회원가입 등록
  		@Override
  		@PostMapping("/joinMember")
  		public String joinMember(MemberVO memberVO) {
  			
  			//숫자 이외의 문자열 제거(빈공백 문자로 대체)
  			String Hp = memberVO.getHp().replaceAll("[^\\d]", "");
  			
  			memberVO.setHp(Hp);
  			
  			memberService.joinService(memberVO);
  			return "redirect:/main";
  		}
  		
  		//회원가입시 ID 중복확인
  		@Override
  		@PostMapping(value = "/checkId", produces = "text/plain; charset=UTF-8")
  		@ResponseBody
  		public String checkId(@RequestParam("id") String id) {
  			return memberService.checkIdService(id);
  		}
  		
	    
		//로그인 진행
	    @Override
	    @PostMapping(value = "/logingo", produces = "text/plain; charset=UTF-8")
	    @ResponseBody
	    public String loginMember(@RequestParam("id") String id, @RequestParam("pass") String pass) {
	    	
	    	return memberService.loginService(id, pass);
	    }

	    //로그아웃
		@Override
		@GetMapping("/logout")
		public String logoutMember() {
			
			return memberService.logoutService();
		}
		
		//마이페이지
		@Override
		@GetMapping("/myPage")
		public String myPage(Model model) {
			
			String sessionAdmin = (String)session.getAttribute("admin");
        	String sessionId = (String)session.getAttribute("id");
	        model.addAttribute("sessionAdmin", sessionAdmin);	        
	     
	        String page = null;
	        
	        System.out.println(sessionAdmin + "마이페이지진입세션어드민");
	       
	        if(sessionAdmin == null) {
	        	page = "redirect:/main";
	        }else if(sessionAdmin.equals("1") || sessionAdmin.equals("0")) {
	        	page = "myPage";
	        	
	 	        memberService.searchMypage(sessionId, model);
	 	       	        	
	        }else if(sessionAdmin.equals("2")) {
	        										
	        	//회원 테이블 조회하여 모델에 member바인딩
	        	memberService.searchMypage(sessionId, model);
	        	
	        	//선생님 테이블 조회하여 모델에 teacher바인딩
	        	memberService.searchMypageTea(sessionId, model);
	        	
	        	page = "myPageTea";
	        }
	        	return page;
		}
		
		//내수업조회(학생용)
		@Override
		@GetMapping("/myLessonStu")
	    public String myLessonStu(Model model, HttpSession session) {

	        String studentId = (String)session.getAttribute("id");
	        String admin = (String)session.getAttribute("admin");
	        
	        List<ttjp_Lesson> lessons = memberService.getLessonsByStudentId(studentId);

	        model.addAttribute("lessons", lessons);
	        model.addAttribute("sessionAdmin", admin);
	        
	        return "myLessonStu";
	    }
		
		//내수업조회(교사용)
		@Override
		@GetMapping("/myLessonTea")
	    public String myLessonTea(Model model, HttpSession session) {

	        String teacherId = (String)session.getAttribute("id");
	        String admin = (String)session.getAttribute("admin");
	        
	        List<ttjp_Lesson> lessons = memberService.getLessonsByTeacherId(teacherId);

	        model.addAttribute("lessons", lessons);
	        model.addAttribute("sessionAdmin", admin);

	        return "myLessonTea";
	    }
		
		

		
		
	}


