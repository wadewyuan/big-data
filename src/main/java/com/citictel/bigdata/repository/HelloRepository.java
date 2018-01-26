package com.citictel.bigdata.repository;


import com.citictel.bigdata.domain.Hello;
import java.sql.Date;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Profile("dev")
public interface HelloRepository extends JpaRepository<Hello, Long> {

    @Query("select h from Hello h where h.name = ?1 order by h.id")
    List<Hello> findByName(String name);

    @Query("select h from Hello h " +
            "where (?1 is null or lower(h.name) like lower(?1))" +
            "and (?2 is null or h.effectiveDate >= ?2) " +
            "and (?3 is null or h.expiryDate <= ?3)")
    List<Hello> findByNameContainingAndEffectiveDateAndExpiryDate(String nameContaining, Date effectiveDate, Date expiryDate);

}