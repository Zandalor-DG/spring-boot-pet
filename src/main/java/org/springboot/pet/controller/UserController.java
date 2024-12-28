package org.springboot.pet.controller;

import org.springboot.pet.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.atomic.AtomicLong;

public class GreetingController {
 private static final String template = "Hello, %s!";
 private final AtomicLong counter = new AtomicLong();

 @GetMapping("/user")
    public User get_user(@RequestParam(value = "name", defaultValue = "World") String name) {
     return new User(counter.incrementAndGet(), String.format(template, name));
 }
}
