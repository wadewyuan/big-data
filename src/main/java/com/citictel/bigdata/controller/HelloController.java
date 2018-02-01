package com.citictel.bigdata.controller;

import com.citictel.bigdata.constants.StatusCodeEnum;
import com.citictel.bigdata.domain.Hello;
import com.citictel.bigdata.domain.ResponseData;
import com.citictel.bigdata.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@Profile("dev")
@RequestMapping("/api/v1/hello")
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping(value = "/{id}")
    @PreAuthorize("#oauth2.hasScope('read')")
    public Hello get(@PathVariable Long id) {
        return helloService.get(id);
    }

    @GetMapping
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseData list(@RequestParam(value = "name", required = false) String name) {
        ResponseData r = new ResponseData();
        if(!StringUtils.isEmpty(name)) {
            r.setData(helloService.findByName(name));
        } else {
            r.setData(helloService.list());
        }
        return r;
    }

    @GetMapping(value = "/search")
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseData searchByNameMatch(@RequestParam(value = "name_containing", required = false) String nameContaining,
                                                           @RequestParam(value = "effective_date", required = false) Date effectiveDate,
                                                           @RequestParam(value = "expiry_date", required = false) Date expiryDate) {
        ResponseData r = new ResponseData();
        r.setData(helloService.findByNameContainingAndEffectiveDateAndExpiryDate(nameContaining, effectiveDate, expiryDate));
        return r;
    }

    @PostMapping
    @PreAuthorize("#oauth2.hasScope('write')")
    public ResponseData add(@RequestBody Hello input) {
        ResponseData r = new ResponseData();
        Hello result = helloService.save(new Hello(input.getName()));
        r.setData(result);
        r.setStatusCodeAndMsg(StatusCodeEnum.CREATED);
        return r;
    }

    @PutMapping
    @PreAuthorize("#oauth2.hasScope('write')")
    public ResponseData update(@RequestBody Hello input) {
        ResponseData r = new ResponseData();
        if (input.getId() != null && helloService.get(input.getId()) != null) {
            helloService.save(input);
            r.setData(input);
        } else {
            r.setStatusCodeAndMsg(StatusCodeEnum.NO_CONTENT);
        }
        return r;
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("#oauth2.hasScope('write')")
    public ResponseData delete(@PathVariable Long id) {
        ResponseData r = new ResponseData();
        if (id != null && helloService.get(id) != null) {
            helloService.delete(id);
            r.setData(ResponseEntity.ok().build());
        } else {
            r.setStatusCodeAndMsg(StatusCodeEnum.NO_CONTENT);
        }
        return r;
    }
}
