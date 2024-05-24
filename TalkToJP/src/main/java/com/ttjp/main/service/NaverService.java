package com.ttjp.main.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttjp.main.dao.MemberRepository;
import com.ttjp.main.entity.ttjp_Member;

import jakarta.servlet.http.HttpSession;

@Service
public class NaverService {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public static String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
				con.setRequestProperty("Content-Type", "text/json;charset=utf-8");
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	public static HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	public static String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

	//이미 가입되었을 시 네이버 로그인시킴
	public void NaverLogin(String m_id, String m_admin) {

		session.setAttribute("id", m_id);
		session.setAttribute("admin", m_admin);

	}

	//계정이 테이블에 존재하는지 여부 확인
	public ttjp_Member serviceCheckAccount(String name, String hp) {

		ttjp_Member ttm = memberRepository.findByNameAndHp(name, hp);
		
		return ttm;

	}
	
	
}
