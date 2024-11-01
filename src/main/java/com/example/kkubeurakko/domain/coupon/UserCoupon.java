package com.example.kkubeurakko.domain.coupon;

import com.example.kkubeurakko.domain.user.User;
import jakarta.persistence.*;
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
    private Long id;

    private boolean isUsed; // 쿠폰 사용 여부

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 쿠폰 소유자

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon; // 사용자가 소유한 쿠폰

    private LocalDateTime issuedAt; // 쿠폰 발급일자

    // 쿠폰 사용 메서드
    public void useCoupon() {
        if (!isUsed) {
            isUsed = true;
        } else {
            throw new IllegalStateException("쿠폰이 이미 사용되었습니다.");
        }
    }
}