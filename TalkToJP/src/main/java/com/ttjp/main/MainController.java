package com.ttjp.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ttjp.main.entity.ttjp_teacher_List;
import com.ttjp.main.service.MyDataService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	MyDataService myDataService;
	
	@RequestMapping(value="/")
    public String home() {
             
        return "redirect:/main"; 
    }   
	
	//미로그인시 메인
    @GetMapping("/main")
    public String main(Model model) {
    	
    	if(session.getAttribute("id") == null) {   	
        
        return "main"; // main.html을 반환
        
    	}else {
    		  		
    		return "redirect:/mainMember";
    	}
    	
    	
    }    
       
    @GetMapping("/layout")
    public String layout(Model model) {
             
        return "layout"; 
    }    
    
    //로그인시 메인
    @GetMapping("/mainMember")
    public String index(Model model) {
		
		if(session.getAttribute("id") == null) {      
	        	return "redirect:/main";	        
	    }else {
		    
	    	String admin = (String)session.getAttribute("admin");
	    	
	    	List<ttjp_teacher_List> top3Teachers = myDataService.getTop3Teachers();
	        model.addAttribute("top3Teachers", top3Teachers);
	        model.addAttribute("sessionAdmin", admin);
	        
	    		return "mainMember";
	    }   

    }
    
    //로그인 폼 이동
    @GetMapping("/login")
    public String login(Model model) {
             
        return "login";
    }
    
    @GetMapping("/ticketPage")
    public String ticketPage(Model model){
       
    	String sessionCheck = null;
      
    	sessionCheck = (String)session.getAttribute("admin");
        
        model.addAttribute("sessionCheck", sessionCheck);

        return "ticketPage";
    }
    
    @GetMapping("/ticketPageGuest")
    public String ticketPageGuest(Model model){
       
        return "ticketPageGuest";
    }
 
    //선생님 일정 설정
    @GetMapping("/yoyakuTea")
    public String yoyakuTea(Model model) {
    	
    	String tid = (String)session.getAttribute("id");
    	model.addAttribute("tid", tid);
    	
        return "yoyakuTea";
    }
    

    
    
}
