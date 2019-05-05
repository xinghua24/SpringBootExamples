package com.example.demo.controller;


import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@RestController
public class DateController {

	/**
	 * GET http://localhost:8080/testLong?start=1527814861000 Here we pass number of
	 * milliseconds since the standard base time known as "the epoch". 
	 * use https://www.epochconverter.com/ to convert to epoch time
	 * 
	 * @param start
	 * @return
	 */
	@RequestMapping("testLong")
	public ResponseEntity<Date> testLong(long start) {
		Date startDate = new Date(start);
		return ResponseEntity.ok().body(startDate);
	}

	// GET http://localhost:8080/testDate?start=2018-01-01
	@RequestMapping("testDate")
	public ResponseEntity<Date> testDate(
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start) {
		return ResponseEntity.ok().body(start);
	}

	// GET http://localhost:8080/testDatePattern?start=2018-03EST
	// GET http://localhost:8080/testDatePattern?start=2018-03EST
	// pattern follows java.text.SimpleDateFormat
	@RequestMapping("testDatePattern")
	public ResponseEntity<Date> testDatePattern(
			@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MMz") Date start) {
		return ResponseEntity.ok().body(start);
	}

	// GET http://localhost:8080/testDateTime?start=2018-10-31T01:30:00.000-05:00
	// GET http://localhost:8080/testDateTime?start=2018-10-31T01:30:00.000Z
	@RequestMapping("testDateTime")
	public ResponseEntity<Date> testDateTime(
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date start) {
		return ResponseEntity.ok().body(start);
	}
	
	// GET http://localhost:8080/testZonedDateTime?start=2018-10-31T01:30:00.000Z
	// GET http://localhost:8080/testZonedDateTime?start=2018-10-31T01:30:00.000%2B01:00 // 2018-10-31T01:30+01:00 
	// '+' URL encoded => '%2B'
	@RequestMapping("testZonedDateTime")
	public ResponseEntity<String> testZonedDateTime(
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime start) {
		return ResponseEntity.ok().body(start.toString());
	}
	
	// GET http://localhost:8080/testLocalDateTime?start=2018-10-31T01:30:00.000Z
	// GET http://localhost:8080/testLocalDateTime?start=2018-10-31T01:30:00.000%2B01:00 // 2018-10-31T01:30
	@RequestMapping("testLocalDateTime")
	public ResponseEntity<String> testLocalDateTime(
			@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start) {
		return ResponseEntity.ok().body(start.toString());
	}
	
	// POST localhost:8080/testDateContainer
	// body {"date": "2018-01-01", "dateTime": "2018-07-04T12:08:56.235-0000"}
	@PostMapping("testDateContainer")
	public ResponseEntity<DateContainer> testDateContainer(@RequestBody DateContainer dataContainer) {
		return ResponseEntity.ok().body(dataContainer);
	}
	
	@Data
	private static class DateContainer {
		@JsonFormat(pattern="yyyy-MM-dd")
		Date date;
		
		// pattern follows java.text.SimpleDateFormat
		@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSSZ")
		Date dateTime;
	}
}
