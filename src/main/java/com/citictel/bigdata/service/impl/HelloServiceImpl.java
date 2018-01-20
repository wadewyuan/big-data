package com.citictel.bigdata.service.impl;

import com.citictel.bigdata.domain.Hello;
import com.citictel.bigdata.repository.HelloRepository;
import com.citictel.bigdata.service.HelloService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private HelloRepository helloRepository;

    @Override
    public Hello get(Long id) {
        return helloRepository.findOne(id);
    }

    @Override
    public List<Hello> list() {
        return helloRepository.findAll();
    }

    @Override
    public List<Hello> getByName(String name) {
        return helloRepository.findByName(name);
    }

    @Override
    public Hello save(Hello hello) {
        return helloRepository.save(hello);
    }

    @Override
    public void delete(Long id) {
        helloRepository.delete(id);
    }
}
