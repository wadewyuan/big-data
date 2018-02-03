package com.citictel.bigdata.service;

import com.citictel.bigdata.domain.Hello;

import java.sql.Date;
import java.util.List;

public interface HelloService {

    Hello get(Long id);

    List<Hello> list();

    List<Hello> findByName(String name);

    Hello save(Hello hello);

    void delete(Long id);

    List<Hello> findByNameContainingAndEffectiveDateAndExpiryDate(String nameContaining, Date effectiveDate, Date expiryDate);
}
