package com.citictel.bigdata.controller;

import com.citictel.bigdata.domain.Hello;
import com.citictel.bigdata.service.HelloService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public List<Hello> list(@RequestParam(value = "name", required = false) String name) {
        if(!StringUtils.isEmpty(name)) {
            return helloService.getByName(name);
        }
        return helloService.list();
    }

    @PostMapping
    public ResponseEntity<Hello> add(@RequestBody Hello input) {
        Hello result = helloService.save(new Hello(input.getName()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(result);
    }

    @PutMapping
    public ResponseEntity<Hello> update(@RequestBody Hello input) {

        if (input.getId() != null && helloService.get(input.getId()) != null) {
            helloService.save(input);
            return ResponseEntity.ok(input);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Hello> delete(@PathVariable Long id) {
        if (id != null && helloService.get(id) != null) {
            helloService.delete(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
