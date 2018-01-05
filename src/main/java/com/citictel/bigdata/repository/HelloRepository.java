package com.citictel.bigdata.repository;


import com.citictel.bigdata.domain.Hello;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepository extends JpaRepository<Hello, Long> {

    @Query("select h from Hello h where h.name = ?1 order by h.id")
    List<Hello> findByName(String name);

}