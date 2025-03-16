package com.example.kkubeurakko.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kkubeurakko.domain.store.Store;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
