package com.citictel.bigdata.dao;

import com.citictel.bigdata.model.Hello;

import java.util.List;

public interface HelloDao {

    Hello getById(int id);

    List<Hello> listAll();
}
