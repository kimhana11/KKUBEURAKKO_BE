package com.example.kkubeurakko.domain.store.init;

import java.math.BigDecimal;
import java.sql.Time;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.kkubeurakko.domain.menu.init.MenuInit;
import com.example.kkubeurakko.domain.store.Store;
import com.example.kkubeurakko.domain.store.repository.StoreRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StoreInit {
	private final StoreRepository storeRepository;
	private final MenuInit menuInit;

	@PostConstruct
	private void initialize(){
		Store store = initializeStore();
		menuInit.initialize(store);
	}

	@Transactional
	public Store initializeStore(){
		Store store = new Store(
			1L,
			"010-1234-1234",
			Time.valueOf("00:00:00"),
			Time.valueOf("00:00:00"),
			true,
			BigDecimal.valueOf(100),
			3,
			BigDecimal.valueOf(20_000)
		);
		storeRepository.save(store);
		return store;
	}
}
