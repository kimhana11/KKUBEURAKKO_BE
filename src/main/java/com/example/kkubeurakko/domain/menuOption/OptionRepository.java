package com.example.kkubeurakko.domain.menuOption;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OptionRepository extends JpaRepository<MenuOptionDetail, Long> {

    @Query("SELECT m.optionValue FROM MenuOptionDetail m WHERE m.id IN :ids")
    List<String> findLabelsByOptionIds(@Param("ids") List<Long> ids);

    @Query("SELECT SUM(m.additionalPrice) FROM MenuOptionDetail m WHERE m.id IN :ids")
    BigDecimal findTotalAdditionalPriceByOptionIds(@Param("ids") List<Long> ids);
}