package com.ttjp.main.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ttjp.main.dao.LessonRepository;
import com.ttjp.main.dao.MemberRepository;
import com.ttjp.main.dao.MyDataRepository;
import com.ttjp.main.dao.TopicRepository;
import com.ttjp.main.entity.ttjp_Lesson;
import com.ttjp.main.entity.ttjp_Member;
import com.ttjp.main.entity.ttjp_Topic;
import com.ttjp.main.entity.ttjp_teacher_List;
import com.ttjp.main.vo.LessonVO;

import jakarta.servlet.http.HttpSession;


@Service
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	MyDataRepository myDataRepository;
	
	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@Autowired
	TopicRepository topicRepository;
	
	@Autowired
	HttpSession session;
	
	//선생님 일정 조회(선생님 일정 설정용)
	@Override
	public List<String> searchReserService() {
       
		String id = (String)session.getAttribute("id");
		
        ttjp_teacher_List tttl = myDataRepository.findByTid(id);
        List<String> selectedBlocks = new ArrayList();
        
        selectedBlocks.add(tttl.getPossible_Date());
        
        return selectedBlocks;
    }
	
	//선생님 일정 조회(학생 예약용)
	@Override
	public List<String> searchReserTeaService(String id) {
		
		ttjp_teacher_List tttl = myDataRepository.findByTid(id);
		List<String> selectedBlocks = new ArrayList();
		
		String date = tttl.getPossible_Date();
		
		selectedBlocks.add(date);
        
        return selectedBlocks;
		
	}
	
	//레슨 테이블에서 선생님의 예약된 수업 일정 조회(학생 예약화면 반영용)
	@Override
	public List<String> alreadyReserService(String tid) {
		
		//선생님의 모든 수업 일정 조회
	    List<ttjp_Lesson> lessonList = lessonRepository.findByTeacheridOrderByIdxDesc(tid);
		
	    List<String> selectedBlocks = new ArrayList<>();

	    //모든 수업 일정을 하나의 리스트로 합치기
	    for (ttjp_Lesson lesson : lessonList) {
	        selectedBlocks.add(lesson.getLessondate());
	    }
        
        return selectedBlocks;
		
	}
	
	//학생의 수업 예약 신청 저장
	@Override
	public String saveReserStuService(String selectDate, String tid, String tname, int toidx) {
		
		//예약하는 학생의 아이디, 이름
		String sid = (String)session.getAttribute("id");
        ttjp_Member ttm = memberRepository.findById(sid);
        String sname = ttm.getName();
        
        //날짜 정보 설정
        String year = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.(E)_HHmm", Locale.KOREAN);
        try {
            //주어진 날짜
            Date givenDate = dateFormat.parse(selectDate);
            SimpleDateFormat givenDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            String givenDateString = givenDateFormat.format(givenDate);
            System.out.println("주어진 날짜: " + givenDateString);

            //현재 날짜
            Date currentDate = new Date();
            SimpleDateFormat currentDateFormat = new SimpleDateFormat("MM-dd HH:mm");
            String currentDateString = currentDateFormat.format(currentDate);
            System.out.println("현재 날짜: " + currentDateString);

            //연도 정보 비교           
            if (givenDate.before(currentDate)) {
                System.out.println("쳌1");
                year = String.valueOf(currentDate.getYear() + 1900);
            } else {
                year = String.valueOf(currentDate.getYear() + 1 + 1900);
                System.out.println("쳌2");
            }
            //연도 출력
            System.out.println("저장할 연도 정보: " + year);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        //토픽 제목 추출용
        ttjp_Topic topic = topicRepository.findById((long)toidx).orElseGet(null);
        String totitle = topic.getTotitle();
        
        										//토픽제목
    	LessonVO lessonVO = new LessonVO(toidx, totitle, tid, tname, sid, sname, year, selectDate);
    	ttjp_Lesson lesson = lessonVO.toEntity();
        
    	ttjp_Lesson lessonCheck = lessonRepository.save(lesson);
            	
    	if(lessonCheck.getLessonyear() != null) {
    		System.out.println("데이터저장성공");
    	}
    	
        return "예약성공";
	}
	
	
	//선생님의 수업 가능 일정 저장
	@Override
	public String saveReserService(String blocksJson) {
		
		String id = (String)session.getAttribute("id");
		
        ttjp_teacher_List tttl = myDataRepository.findByTid(id);
        
        tttl.setPossible_Date(blocksJson);
        
        myDataRepository.save(tttl);
        
        return "redirect:/main";
	}
	
}
