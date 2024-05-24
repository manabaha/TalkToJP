package com.ttjp.main.controller;

import com.ttjp.main.ChatService;
import com.ttjp.main.entity.ttjp_Lesson;
import com.ttjp.main.entity.ttjp_Member;
import com.ttjp.main.entity.ttjp_chat;
import com.ttjp.main.service.LessonService;
import com.ttjp.main.service.MemberService;
import com.ttjp.main.service.TopicService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lesson")
public class LessonControllerImpl implements LessonController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private MemberService memberService;
    
    @Autowired
    private TopicService topicService;
    
    @Override
    @GetMapping("/classRoom")
    public String showClassRoom(Model model, @RequestParam(name = "lessonIdx", required = false) Long lessonIdx,  HttpSession session) {
  	
    	ttjp_Lesson ttls = lessonService.lessonSearch(lessonIdx);
    	model.addAttribute("ttls", ttls);   	
    	model.addAttribute("topic", topicService.getTopicImages(Long.valueOf(ttls.getTopicidx())));
    	
    	String myId = (String)session.getAttribute("id");
    	String admin = (String)session.getAttribute("admin");
    	ttjp_Member member = memberService.getName(myId);
    	model.addAttribute("member", member);
    	model.addAttribute("sessionAdmin", admin);
    	

      	
        return "classRoom"; // 템플릿 이름 반환
    }

    @Override
    @PostMapping("/sendMessage")
    @ResponseBody
    public void sendMessage(
        @RequestParam(name = "lessonIdx") Long lessonIdx, 
        @RequestParam(name = "teacherId") String teacherId, 
        @RequestParam(name = "studentId") String studentId, 
        @RequestParam(name = "message") String message,
        @RequestParam(name = "senderId") String senderId,
        @RequestParam(name = "senderName") String senderName) {
        chatService.sendMessage(lessonIdx, teacherId, studentId, message, senderId, senderName);
    }



    @Override
    @GetMapping("/getMessages")
    @ResponseBody
    public List<ttjp_chat> getMessages(@RequestParam(name = "lessonIdx") Long lessonIdx) {
        return chatService.getMessagesByLessonIdx(lessonIdx);
    }

}
