package com.citictel.bigdata.controller;

import com.citictel.bigdata.dao.HelloDao;
import com.citictel.bigdata.dao.impl.HelloDaoImpl;
import com.citictel.bigdata.model.Hello;
import com.citictel.bigdata.service.HelloService;
import com.citictel.bigdata.service.impl.HelloServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

//    @Autowired
//    private HelloService helloService = new HelloServiceImpl();

    private HelloDao helloDao = new HelloDaoImpl();

    @RequestMapping("/")
    public Hello greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Hello(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Hello get(@PathVariable int id) {
//        return helloService.get(id);
        return helloDao.getById(id);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Hello> list() {
//        return helloService.list();
        return helloDao.listAll();
    }
}
