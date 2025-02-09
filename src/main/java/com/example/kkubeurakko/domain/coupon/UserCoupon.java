package com.example.kkubeurakko.domain.coupon;

import com.example.kkubeurakko.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_coupon_id", updatable = false, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "is_used", nullable = false, columnDefinition = "boolean default false")
    private boolean isUsed;

    @NotNull
    @Column(name = "issued_at", nullable = false)
    private LocalDateTime issuedAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // 쿠폰 소유자

    @ManyToOne
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    // 쿠폰 사용 메서드
    public void useCoupon() {
        if (!isUsed) {
            isUsed = true;
        } else {
            throw new IllegalStateException("쿠폰이 이미 사용되었습니다.");
        }
    }
}