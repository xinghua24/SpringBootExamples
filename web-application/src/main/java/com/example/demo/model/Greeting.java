package com.example.demo.model;


import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.lang3.StringUtils;
import lombok.Data;

@Data
public class Greeting {
	private static final AtomicLong counter = new AtomicLong(0);
	private long id;
	private final String content;

	public Greeting() {
		super();
		this.id = counter.incrementAndGet();
		content = StringUtils.EMPTY;
	}
	
	public Greeting(String content) {
		super();
		this.id = counter.incrementAndGet();
		this.content = content;
	}
}
