package com.example.builder.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.builder.bean.Greeting;

@RestController
@RequestMapping(value = "greeting")
public class GreetingController {

	private final AtomicInteger count = new AtomicInteger();

	private static final String template = "Hello %s";

	@GetMapping
	public Greeting greetin(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(count.getAndIncrement(), String.format(template, name));
	}
}
