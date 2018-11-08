package com.example.demo;

import com.example.demo.service.FooService;
import org.springframework.stereotype.Service;

@Service
public class FooServiceImpl implements FooService {
    @Override
    public String doSomething() {
        return "something";
    }
}
