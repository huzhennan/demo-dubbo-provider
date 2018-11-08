package com.example.demo;

import com.example.demo.service.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        System.out.println("-------------------------");
        System.out.println("hello " + name);
        System.out.println("-------------------------");

        return String.format("hello %s!", name);
    }
}
