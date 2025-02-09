package com.example.kkubeurakko.domain.alarm;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.store.Store;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "alarm_id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @NotNull
    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default false")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;
}
