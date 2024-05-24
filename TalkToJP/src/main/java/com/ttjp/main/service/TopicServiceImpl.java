package com.ttjp.main.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.ttjp.main.dao.TopicRepository;
import com.ttjp.main.entity.ttjp_Topic;


@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;
        
    //모든 토픽 조회
    @Override
    public List<ttjp_Topic> getAllTopic() {
        return topicRepository.findAllByOrderByToidxAsc();
    }
    
    //토픽 인덱스로 토픽 이미지 조회
    @Override
    public ttjp_Topic getTopicImages(Long toidx) {
        ttjp_Topic tttp = topicRepository.findById(toidx).orElse(null);
        return tttp;
    
    }
    
    
}