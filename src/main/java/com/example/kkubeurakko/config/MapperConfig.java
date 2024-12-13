package com.example.kkubeurakko.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.kkubeurakko.api.address.mapper.AddressMapper;
import com.example.kkubeurakko.api.address.mapper.AddressMapperImpl;

@Configuration
public class MapperConfig {
	@Bean
	public AddressMapper addressMapper(){
		return new AddressMapperImpl();
	}
}
