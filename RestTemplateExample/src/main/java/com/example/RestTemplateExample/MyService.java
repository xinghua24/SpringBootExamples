package com.example.RestTemplateExample;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MyService {

  public final RestTemplate restTemplate;

  public MyService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Post simpleGet() {
    log.info("------------RestTemplate.getForEntity()-----------");
    try {
      ResponseEntity<Post> response = restTemplate.getForEntity(
          "https://jsonplaceholder.typicode.com/posts/1", Post.class);
      if(response.getStatusCode() == HttpStatus.OK) {
        log.info("Response body: " + response.getBody());
        return response.getBody();
      }
    } catch(RestClientException e ) {
      log.error("Fail to retrieve Post", e);
      throw e;
    }
    return null;
  }

  public Post[] exchangeWithUriVariables() {
    log.info("------------RestTemplate.getForEntity() getting Array -----------");
    try {
      Map<String,String> uriVariables = new HashMap<>();
      uriVariables.put("key1", "value1");
      uriVariables.put("key2", "value2");

      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
      HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

      ResponseEntity<Post[]>  response = restTemplate.exchange(
          "https://jsonplaceholder.typicode.com/posts", HttpMethod.GET, requestEntity, Post[].class, uriVariables );
      if(response.getStatusCode() == HttpStatus.OK) {
        log.info("Response body size: " + response.getBody().length);
        return response.getBody();
      }
    } catch(RestClientException e ) {
      log.error("Fail to retrieve Post array", e);
      throw e;
    }
    return null;
  }

  public Post simplePost() {
    log.info("------------RestTemplate.postForEntity()-----------");
    try {
      Post post = new Post();
      post.setTitle("Test");
      post.setBody("some content");
      post.setId(333);
      post.setUserId(234);
      HttpEntity<Post> requestEntity = new HttpEntity<>(post);
      ResponseEntity<Post> response = restTemplate.postForEntity(
          "https://jsonplaceholder.typicode.com/posts", requestEntity, Post.class);
      if(response.getStatusCode() == HttpStatus.CREATED) {
        log.info("Response body: " + response.getBody());
        return response.getBody();
      }
    } catch(RestClientException e ) {
      log.error("Fail to post post", e);
      throw e;
    }
    return null;
  }

  public Post exchangePost() {
    log.info("------------RestTemplate.exchange() POST-----------");
    try {
      Post post = new Post();
      post.setTitle("Test");
      post.setBody("some content");
      post.setId(333);
      post.setUserId(234);

      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
      headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

      HttpEntity<Post> requestEntity = new HttpEntity<>(post, headers);
      ResponseEntity<Post> response = restTemplate.exchange(
          "https://jsonplaceholder.typicode.com/posts", HttpMethod.POST, requestEntity, Post.class);
      if(response.getStatusCode() == HttpStatus.CREATED) {
        log.info("Response body: " + response.getBody());
        return response.getBody();
      }
    } catch(RestClientException e ) {
      log.error("Fail to retrieve post", e);
      throw e;
    }
    return null;
  }


  public Post exchangePut() {
    log.info("------------RestTemplate.exchange() PUT-----------");
    try {
      Post post = new Post();
      post.setTitle("Test");
      post.setBody("some content");
      post.setId(333);
      post.setUserId(234);
      HttpEntity<Post> requestEntity = new HttpEntity<>(post);
      ResponseEntity<Post> response = restTemplate.exchange(
          "https://jsonplaceholder.typicode.com/posts/1", HttpMethod.PUT, requestEntity,
          Post.class);
      if(response.getStatusCode() == HttpStatus.OK) {
        log.info("Response body: " + response.getBody());
        return response.getBody();
      }
    } catch(RestClientException e ) {
      log.error("Fail to put Post", e);
      throw e;
    }
    return null;
  }

  public void simpleDelete() {
    log.info("------------RestTemplate.delete()-----------");
    try {
      restTemplate.delete("https://jsonplaceholder.typicode.com/posts/1");
      log.info("delete successfully");
    } catch (RestClientException e) {
      log.error("Fail to delete post", e);
      throw e;
    }
  }
}
