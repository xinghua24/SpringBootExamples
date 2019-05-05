package com.example.demo.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Greeting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="GreetingAPI")
@RequestMapping("/greeting")
public class MyController {
	// simple GET
	// GET localhost:8080/greeting/hello
	@RequestMapping(value = "/hello",
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="simple hello")
	public ResponseEntity<Greeting> hello() {
		return ResponseEntity.status(HttpStatus.OK).body(new Greeting("Hello"));
	}


	// GET with query parameter
	// GET localhost:8080/greeting/sayHello?name=world
	@RequestMapping(value = "/sayHello", 
			params = "name",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return ResponseEntity.status(HttpStatus.OK).body(new Greeting("Hello"));
	}

	// GET with path parameter
	// GET localhost:8080/greeting/sayHelloPath/{name}
	@RequestMapping(value = "/sayHelloPath/{name}", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> sayHelloPath(@PathVariable(value = "name") String name) {
		return ResponseEntity.status(HttpStatus.OK).body(new Greeting("Hello"));
	}
	
	// GET with query parameters as Map
	// GET localhost:8080/greeting/sayHelloToAll?test1=1&test2&test3
	@RequestMapping(value = "/sayHelloToAll", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Greeting>> sayHelloToAll(@RequestParam Map<String, String> names) {
		List<Greeting> greetings = new ArrayList<Greeting>();
		names.keySet().forEach(key -> {
			greetings.add(new Greeting(key));
		});
		return ResponseEntity.status(HttpStatus.OK).body(greetings);
	}

	// POST localhost:8080/greeting/postGreeting
	// @RequestMapping(value = "/postGreeting", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(value = "/postGreeting", 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Greeting> postGreeting(@RequestBody Greeting greeting) {
		if( StringUtils.isBlank(greeting.getContent())) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // use build() to create responseEntity without body
		}
		return ResponseEntity.status(HttpStatus.OK).body(greeting);
	}
	
	// return empty body(response entity is void
	// http://localhost:8080/greeting/returnvoid
	@RequestMapping(value = "/returnempty", 
			method = RequestMethod.GET)
	public ResponseEntity<Void> returnempty() {
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
