package com.example.kkubeurakko.domain.address;

import com.example.kkubeurakko.api.controller.address.request.AddressRequest;
import com.example.kkubeurakko.domain.BaseEntity;
import com.example.kkubeurakko.domain.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @Column(name = "id", updatable = false)
    private Long id;

    @NotNull
    @Column(name = "address_nickname", length = 30)
    private String addressNickname;

    @NotNull
    @Column(name = "road_name", length = 50)
    private String roadName;

    @NotNull
    @Column(name = "detail_address", length = 20)
    private String detailedAddress;

    @NotNull
    @Column(name = "postal_code", length = 5)
    private String postalCode;

    @NotNull
    @Column(name = "is_primary", columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isPrimary;

    @NotNull
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
