package com.example.kkubeurakko.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kkubeurakko.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserNumber(String userNumber);

	@Query("select case when count(u) > 0 then true else false end from User u where u.userNumber = :userNumber and u.phone is not null")
	boolean existsUserPhoneByUserNumber(@Param("userNumber") String userNumber);
}
