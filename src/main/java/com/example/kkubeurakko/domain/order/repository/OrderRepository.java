package com.example.kkubeurakko.domain.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kkubeurakko.domain.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
