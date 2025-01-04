package com.example.kkubeurakko.domain.address;

import com.example.kkubeurakko.api.controller.address.request.AddressRequest;
import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String addressNickname;
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

    // 기본 주소 업데이트
    public void updatePrimary(boolean isPrimary){
        this.isPrimary = isPrimary;
    }
    public void updateAddress(Long addressId, AddressRequest addressRequest){
        this.id = addressId;
        this.addressNickname = addressRequest.addressNickname();
        this.roadName = addressRequest.roadName();
        this.detailedAddress = addressRequest.detailedAddress();
        this.postalCode = addressRequest.postalCode();
        this.isPrimary = addressRequest.isPrimary();
    }
    @Builder
    Address(String roadName, String addressNickname, String detailedAddress, String postalCode, Boolean isPrimary, User user){
        this.roadName = roadName;
        this.addressNickname = addressNickname;
        this.detailedAddress = detailedAddress;
        this.postalCode = postalCode;
        this.isPrimary = isPrimary;
        this.user = user;
    }
}
