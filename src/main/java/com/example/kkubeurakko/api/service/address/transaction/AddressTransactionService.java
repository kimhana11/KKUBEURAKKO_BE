package com.example.kkubeurakko.api.service.address.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.kkubeurakko.api.exception.address.AddressNotFoundException;
import com.example.kkubeurakko.domain.address.Address;
import com.example.kkubeurakko.domain.address.AddressRepository;
import com.example.kkubeurakko.domain.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressTransactionService {
	private final AddressRepository addressRepository;

	//새로 저장하는 주소가 기본주소로 설정한다면 기존 기본주소를 해제
	//트랜잭션 범위에 둠으로써 영속성 컨테이너에 포함시켜 필요한 필드만 더티체킹을 통해 업데이트
	//트랜잭션을 최대한 짧게 가져가기 위해
	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePrimary(User user){
		Address address = addressRepository.findPrimaryAddressByUser(user)
			.orElseThrow(()->new AddressNotFoundException());
		address.updatePrimary(!address.getIsPrimary());
	}

}
