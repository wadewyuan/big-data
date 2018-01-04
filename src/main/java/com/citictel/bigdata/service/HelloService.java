package com.citictel.bigdata.service;

import com.citictel.bigdata.model.Hello;

import java.util.List;

public interface HelloService {

    Hello get(Long id);

    List<Hello> list();

    List<Hello> getByName(String name);
}
