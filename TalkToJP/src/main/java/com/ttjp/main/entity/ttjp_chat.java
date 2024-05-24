package com.ttjp.main.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ttjp_chat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ttjp_chat_seq")
    @SequenceGenerator(name = "ttjp_chat_seq", sequenceName = "TTJP_CHAT_SEQ", allocationSize = 1)
    private Long id;

    @Column
    private Long lessonIdx;

    @Column
    private String teacherId;

    @Column
    private String studentId;
    
    @Column
    private String senderId;

    @Column
    private String senderName;
    

    @Column(columnDefinition = "VARCHAR2(1000)")
    private String message;

    @CreationTimestamp
    @Column(name = "chattime", nullable = false, updatable = false)
    private LocalDateTime chatTime;
}
