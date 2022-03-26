package tn.esprit.spring.womanarea.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tn.esprit.spring.womanarea.demo.Services.IRoleService;

import javax.annotation.PostConstruct;

@Component
public class Monitor {
    @Autowired
    IRoleService iroleservice;

    @PostConstruct
    public void init(){
        iroleservice.initRoles();
    }
}
