package com.ingsw.restservice;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController	
public class AccommodationController {

	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	private String name="Da peppino";
	@GetMapping("/accommodation")
	public Accommodation accommodation(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Accommodation(counter.incrementAndGet(), String.format(template, name), this.name);
	}
}
