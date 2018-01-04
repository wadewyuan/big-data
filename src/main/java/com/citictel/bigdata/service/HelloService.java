package com.citictel.bigdata.service;

import com.citictel.bigdata.model.Hello;

import java.util.List;

public interface HelloService {

    Hello get(int id);

    List<Hello> list();

}
