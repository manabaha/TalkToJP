package com.ttjp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ttjp.main.dao.MemberRepository;
import com.ttjp.main.dao.MyDataRepository;
import com.ttjp.main.entity.ttjp_Member;
import com.ttjp.main.entity.ttjp_teacher_List;
import com.ttjp.main.vo.MemberVO;
import com.ttjp.main.vo.TeacherVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class MyDataServiceImpl implements MyDataService {

	@Autowired
    private MyDataRepository myDataRepository;
	
	@Autowired
	private MemberRepository memberRepository;

    @Autowired
    private HttpSession session;
	
	//티켓구매서비스
    @Override
    public String ticketBuyService(String id, int ticket, int price) {
    											//ticketPage에서는 bun인데 레파지토리때문에 바꿈
    	
    	ttjp_Member searchData = memberRepository.findById(id);
    	int originalTicket = searchData.getTicket(); //멤버 id기준으로 기존 데이터 가져와서 기존티켓값 얻음
    	
    	System.out.println(originalTicket + "원래티켓수");
    	System.out.println(ticket + "새로구매한티켓");    	
    	int resultTicket = originalTicket + ticket; //티켓수 더함
    	System.out.println(resultTicket + "원래티켓수+새로구매한티켓수");
    	searchData.setTicket(resultTicket); //다시 테이블에 티켓값 셋팅
    	
    	ttjp_Member result = memberRepository.save(searchData);

    	if(result != null) {
    		System.out.println("티켓구매성공");
    		return "티켓구매성공";
    		
    	}else {
    		System.out.println("티켓구매실패");
    		return "티켓구매실패";
    	}
    }
    
    //수업 예약 후 수강권 1개 차감 서비스
    @Override
    public String ticketMinusService() {
    											//ticketPage에서는 bun인데 레파지토리때문에 바꿈
    	String id = (String)session.getAttribute("id");
    	
    	ttjp_Member searchData = memberRepository.findById(id);
    	int originalTicket = searchData.getTicket(); //멤버 id기준으로 기존 데이터 가져와서 기존티켓값 얻음
    	
    	System.out.println(originalTicket + "원래티켓수");
    	int resultTicket = originalTicket - 1; //티켓 1차감
    	System.out.println(resultTicket + "원래티켓수+1차감한티켓수");
    	
    	if(resultTicket < 0) {
    		//음수가 되는 경우 리턴
    		return "티켓차감실패";
    	}else {
    		searchData.setTicket(resultTicket);//멀쩡한 경우 계산한 티켓값 셋팅하여 저장
        	ttjp_Member result = memberRepository.save(searchData);
        	return "티켓차감성공";
    	}
 
    }
    
    //마이페이지 회원정보 수정 후 업데이트
    @Override
    public String updateService(MemberVO memberVO) {
    	
    	ttjp_Member member = memberRepository.findById(memberVO.getId());
    	member.setPass(memberVO.getPass());
    	member.setEmail(memberVO.getEmail());
    	member.setHp(memberVO.getHp());
    	   	
    	memberRepository.save(member);
    	
    	return "redirect:/main";
    }
        
    //선생님으로 어드민 데이터 변경 후 세션 재설정(2로 변경)
    @Override
    public String stepUpTea(String id, String admin) {
    	
    	ttjp_Member member = memberRepository.findById(id);
    	member.setAdmin(admin);
    	   	
    	memberRepository.save(member);
    	
    	session.removeAttribute("admin");
    	session.setAttribute("admin", admin);
    	
    	return "redirect:/main";
    }
    
  //선생님 정보 등록
    @Override
    public String joinTeacher(TeacherVO teacherVO) {
    	
    	ttjp_teacher_List teacherList = teacherVO.toEntity();
    	
       	myDataRepository.save(teacherList);
    	
    	return "redirect:/main";
    }
    
    //선생님 정보 업데이트(파일 제외)
    @Override
    public String updateTeaService(String tid, String tname, String content) {
    	
    	ttjp_teacher_List ttl = myDataRepository.findByTid(tid);
    	
    	ttl.setTname(tname);
    	ttl.setContent(content);
    	
    	myDataRepository.save(ttl);
    	
		return "";
    	
    }
    
    @Override
    //메인에서 보여줄 선생님 3명의 정보 조회 
    public List<ttjp_teacher_List> getTop3Teachers() {
        return myDataRepository.findTop3ByOrderByIdxDesc();
    }
    
    @Override
    //예약에서 보여줄 모든 선생님 정보 조회
    public List<ttjp_teacher_List> getAllTeachers() {
        return myDataRepository.findAllByOrderByIdxDesc();
    }
    
    
    
    
}