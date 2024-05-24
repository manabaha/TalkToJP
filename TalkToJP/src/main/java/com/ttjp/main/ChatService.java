package com.ttjp.main;

import com.ttjp.main.dao.ChatRepository;
import com.ttjp.main.entity.ttjp_chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

	@Autowired
    private ChatRepository chatRepository;    

    public void sendMessage(Long lessonIdx, String teacherId, String studentId, String message, String senderId, String senderName) {
        ttjp_chat chat = new ttjp_chat();
        chat.setLessonIdx(lessonIdx);
        chat.setTeacherId(teacherId);
        chat.setStudentId(studentId);
        chat.setSenderId(senderId);
        chat.setSenderName(senderName);
        chat.setMessage(message);
        
        // 채팅 저장
        chatRepository.save(chat);
    }

    public List<ttjp_chat> getMessagesByLessonIdx(Long lessonIdx) {
        return chatRepository.findByLessonIdxOrderByChatTime(lessonIdx);
    }
}
