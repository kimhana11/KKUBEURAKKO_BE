package com.example.kkubeurakko.domain.address;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.kkubeurakko.domain.user.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	Optional<List<Address>> findAllByUser(User user);
	@Query("select a from Address a where a.user = :user and a.isPrimary = true")
	Optional<Address> findPrimaryAddressByUser(@Param("user") User user);

	boolean existsByUser(@Param("user") User user);
}
