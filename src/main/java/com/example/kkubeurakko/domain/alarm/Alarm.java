package com.example.kkubeurakko.domain.alarm;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.store.Store;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;//공지 내용
    private LocalDateTime startDate; // 공지 시작 날짜
    private LocalDateTime endDate; // 공지 종료 날짜
    private boolean isActive; // 공지 활성화 여부

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
