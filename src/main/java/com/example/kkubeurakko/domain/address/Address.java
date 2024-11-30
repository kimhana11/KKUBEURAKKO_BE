package com.example.kkubeurakko.domain.address;

import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roadName;
    private String detailedAddress;
    private String postalCode;
    private Boolean isPrimary;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 기본 주소 선택
    public void setSelected(Boolean selected) {
        this.isPrimary = selected;
    }
}
