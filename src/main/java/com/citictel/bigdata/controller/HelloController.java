package com.citictel.bigdata.controller;

import com.citictel.bigdata.domain.Hello;
import com.citictel.bigdata.service.HelloService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/{id}")
    public Hello get(@PathVariable Long id) {
        return helloService.get(id);
    }

    @GetMapping
    public List<Hello> list() {
        return helloService.list();
    }

    @GetMapping(params = "name")
    public List<Hello> list(@RequestParam("name") String name) {
        return helloService.getByName(name);
    }
}
