package com.ttjp.main.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ttjp.main.entity.ttjp_Member;
import com.ttjp.main.service.MemberService;
import com.ttjp.main.service.NaverService;
import com.ttjp.main.vo.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class NaverController {

    @Autowired
    private NaverService naverService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private Gson gson;
    
    @GetMapping("/naverJoin")
    public String naverJoin(Model model) {
    	
    	return "naverJoin";
    }
    
    @PostMapping("/accessToken")
    @ResponseBody
    public String naverJoin(@RequestParam("accessToken") String accessToken) {
    	
    	
    	String token = accessToken; // 네이버 로그인 접근 토큰
	    String header = "Bearer " + token; // Bearer 다음에 공백 추가	
	    String apiURL = "https://openapi.naver.com/v1/nid/me";
		
	    Map<String, String> requestHeaders = new HashMap<>();
	    requestHeaders.put("Authorization", header);
	    requestHeaders.put("Accept-Charset", "UTF-8");
	    //이름 값을 한글로 받아오기 위해서 설정해주어야 함
	    
	    //회원 프로필 정보(id, gender 등)가 저장되어 있는 json데이터 형식의 문자열
	    String responseBody = NaverService.get(apiURL,requestHeaders);
	
	    System.out.println("API 반환 데이터" + responseBody);

        JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        
        //JSON데이터의 "response" 객체 내부의 데이터 추출
        JsonObject responseData = jsonObject.getAsJsonObject("response");
         
        String jsonString = responseData.toString();
        
        //필요한 데이터 추출
        String hp = responseData.get("mobile").getAsString().replace("-", "");
        String name = responseData.get("name").getAsString();

        // 계정이 DB에 존재하는지 확인
        ttjp_Member ttm = naverService.serviceCheckAccount(name, hp);
        
        if (ttm == null) {
            return jsonString;
        } else {
            naverService.NaverLogin(ttm.getId(), ttm.getAdmin());
        	return "이미가입됨";

        }
    
    }
    
    //네이버 계정으로 회원가입
    @PostMapping("/naverInsert")
    public String naverInsert(MemberVO memberVO) {
        // 숫자 이외의 문자열 제거(빈공백 문자로 대체)
        String Hp = memberVO.getHp().replaceAll("[^\\d]", "");
        memberVO.setHp(Hp);
        
        memberService.joinService(memberVO);
        
        return "redirect:/mainMember";
    }
}
