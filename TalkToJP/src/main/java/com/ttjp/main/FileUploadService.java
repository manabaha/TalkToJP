package com.ttjp.main;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ttjp.main.dao.MyDataRepository;
import com.ttjp.main.entity.ttjp_teacher_List;
import com.ttjp.main.vo.TeacherVO;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class FileUploadService {

    @Autowired
    private MyDataRepository myDataRepository;

    @Value("${file.upload.path}")
    private String uploadPath;

    public ResponseEntity<String> serviceInsertTeacher(@RequestParam("picture") MultipartFile file,
                                                     @RequestParam("tid") String tid, @RequestParam("tname") String tname,
                                                     @RequestParam("content") String content, HttpServletRequest request) {
        try {
            TeacherVO vo = new TeacherVO();
            vo.setTid(tid);
            vo.setTname(tname);
            vo.setPicture(file.getOriginalFilename());
            vo.setContent(content);

            ttjp_teacher_List teacherList = vo.toEntity();

            ttjp_teacher_List tt = myDataRepository.save(teacherList);

            //인덱스 번호 가져옴
            String t_idx = String.valueOf(tt.getIdx());

            // 업로드할 파일 경로 지정
            String directoryPath = uploadPath + "/" + t_idx; // t_idx 변수를 사용하여 경로 생성
            File uploadDir = new File(directoryPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs(); // 디렉토리가 존재하지 않으면 생성
            }

            // 파일 저장
            File destFile = new File(uploadDir, file.getOriginalFilename());
            file.transferTo(destFile);

            // 파일 저장 후에는 파일 경로를 반환하여 클라이언트에게 전달하거나, DB에 저장하는 등의 작업을 수행할 수 있습니다.
            String fileUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/"
                    + uploadDir.getName() + "/" + file.getOriginalFilename();

            return ResponseEntity.ok(fileUrl);
        } catch (IOException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일명이 동일하거나, 업로드 오류입니다.");
        }
    }
    
    public ResponseEntity<String> serviceUpdateTeacher(@RequestParam("picture") MultipartFile file,
            @RequestParam("tid") String tid, @RequestParam("tname") String tname,
            @RequestParam("content") String content, HttpServletRequest request) {
    	
		try {
			
			ttjp_teacher_List teacherList = myDataRepository.findByTid(tid);		
			Long idx = teacherList.getIdx();
			
			int check = myDataRepository.updateTeacher(idx, tname, file.getOriginalFilename(), content);
	
			System.out.println("파일업데이트서비스 업데이트행갯수"+check);
			
			//업로드할 파일 경로 지정
			String directoryPath = uploadPath + "/" + String.valueOf(idx); // t_idx 변수를 사용하여 경로 생성
			File uploadDir = new File(directoryPath);

			if (!uploadDir.exists()) {
				uploadDir.mkdirs(); // 디렉토리가 존재하지 않으면 생성
			}

			// 파일 저장
			File destFile = new File(uploadDir, file.getOriginalFilename());
			file.transferTo(destFile);

			// 파일 저장 후에는 파일 경로를 반환하여 클라이언트에게 전달하거나, DB에 저장하는 등의 작업을 수행할 수 있습니다.
			String fileUrl = request.getRequestURL().toString().replace(request.getRequestURI(), "") + "/"
					+ uploadDir.getName() + "/" + file.getOriginalFilename();

			return ResponseEntity.ok(fileUrl);
		} catch (IOException ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일명이 동일하거나, 업로드 오류입니다.");
		}
	}
    
    
}
