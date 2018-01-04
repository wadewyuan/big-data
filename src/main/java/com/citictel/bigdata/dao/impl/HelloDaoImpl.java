package com.citictel.bigdata.dao.impl;

import com.citictel.bigdata.dao.HelloDao;
import com.citictel.bigdata.model.Hello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class HelloDaoImpl implements HelloDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HelloDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hello getById(int id) {
        return new Hello(id, "Testing");
    }

    @Override
    public List<Hello> listAll() {
        List<Hello> list = new ArrayList<Hello>();
        for (int i = 0; i < 5; i++) {
            list.add(new Hello(i, "Testing" + i));
        }
        return list;
    }
}
