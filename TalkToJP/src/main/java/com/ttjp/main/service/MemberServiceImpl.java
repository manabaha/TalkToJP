package com.ttjp.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ttjp.main.dao.LessonRepository;
import com.ttjp.main.dao.MemberRepository;
import com.ttjp.main.dao.MyDataRepository;
import com.ttjp.main.entity.ttjp_Lesson;
import com.ttjp.main.entity.ttjp_Member;
import com.ttjp.main.entity.ttjp_teacher_List;
import com.ttjp.main.vo.MemberVO;

import jakarta.servlet.http.HttpSession;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MyDataRepository myDataRepository;
    
    @Autowired
    private LessonRepository lessonRepository;
    
    
    @Autowired
    private HttpSession session;
    
    //회원가입
    @Override
    public String joinService(MemberVO memberVO) {
    	
    	ttjp_Member member = memberVO.toEntity();
    	
    	ttjp_Member save = memberRepository.save(member);
    	
		session.setAttribute("id", member.getId());
		session.setAttribute("admin", member.getAdmin());
    	
        return "";
    }
    
    @Override
    public String checkIdService(String id) {
    	
    	ttjp_Member member = memberRepository.findById(id);
    	
    	if(member != null) {
    		return "ID있음";
    	}else {
    		return "ID없음";
    	}
    	
    }
    
    //로그인
    @Override
    public String loginService(String id, String pass) {
    	
    						//일치하는게 없으면 null 반환
    	ttjp_Member member = memberRepository.findByIdAndPass(id, pass); 
 
    	if(member != null) {
    		System.out.println("로그인 가능");
    		session.setAttribute("id", id);
    		session.setAttribute("admin", member.getAdmin());
    		return "로그인";
    		
    	}else {
    		System.out.println("로그인 정보 불일치");
    		return "로그인실패";
    	}
    	
    	
    }
    
    //로그아웃
    @Override
    public String logoutService() {
    	
    	session.invalidate();
    	
    	return "redirect:/main";
    }
    
    //마이페이지 회원정보 조회
    @Override
    public String searchMypage(String id, Model model) {
    	    
    	ttjp_Member member = memberRepository.findById(id);
    	
    	model.addAttribute("member", member);
    	
    	return "redirect:/main";
    }
    
    //마이페이지 선생님 정보 조회
    @Override
    public String searchMypageTea(String id, Model model) {
    	    
    	ttjp_teacher_List teacher = myDataRepository.findByTid(id);

    	model.addAttribute("teacher", teacher);
    	
    	return "redirect:/main";
    }
    
    //내 수업리스트 조회(학생용)
    @Override
    public List<ttjp_Lesson> getLessonsByStudentId(String studentId) {
        return lessonRepository.findByStudentidOrderByIdxDesc(studentId);
    }
    
    //내 수업리스트 조회(학생용)
    @Override
    public List<ttjp_Lesson> getLessonsByTeacherId(String teacherId) {
        return lessonRepository.findByTeacheridOrderByIdxDesc(teacherId);
    }
    
    //아이디로 이름 조회
    @Override
    public ttjp_Member getName(String Id) {
        return memberRepository.findById(Id);
    }
    
    
}