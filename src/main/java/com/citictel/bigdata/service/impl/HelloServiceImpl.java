package com.citictel.bigdata.service.impl;

import com.citictel.bigdata.dao.HelloDao;
import com.citictel.bigdata.dao.impl.HelloDaoImpl;
import com.citictel.bigdata.model.Hello;
import com.citictel.bigdata.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HelloServiceImpl implements HelloService {

//    @Autowired
    private HelloDao helloDao = new HelloDaoImpl(null);

    @Override
    public Hello get(int id) {
        return helloDao.getById(id);
    }

    @Override
    public List<Hello> list() {
        return helloDao.listAll();
    }
}
