package com.example.RestTemplateExample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class MyServiceTest {
  private static RestTemplate restTemplate;
  private static MyService myService;

  @BeforeEach
  public void init () {
    restTemplate = mock(RestTemplate.class);
    myService = new MyService(restTemplate);
  }

  @Test
  public void testSimpleGet() {
    Post responseBody = new Post();
    responseBody.setId(1);
    responseBody.setTitle("Post Title");
    responseBody.setBody("Some Content");
    ResponseEntity<Post> response = ResponseEntity.ok(responseBody);
    when(restTemplate.getForEntity( anyString(), eq(Post.class))).thenReturn(response);
    Post post = myService.simpleGet();
    assertEquals(1, post.getId());
    assertEquals("Post Title", post.getTitle());
  }

  @Test
  public void testExchangePost() {
    Post responseBody = new Post();
    responseBody.setId(1);
    responseBody.setTitle("Post Title");
    responseBody.setBody("Some Content");
    ResponseEntity<Post> response = ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    when(restTemplate.exchange( anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(Post.class))).thenReturn(response);

    // check response
    Post post = myService.exchangePost();
    assertEquals(1, post.getId());
    assertEquals("Post Title", post.getTitle());

    // check request eneity argument that is passed to restTemplate method.
    ArgumentCaptor<HttpEntity> argCaptor = ArgumentCaptor.forClass(HttpEntity.class);
    verify(restTemplate).exchange( anyString(), eq(HttpMethod.POST), argCaptor.capture(), eq(Post.class));
    Post reqBody = (Post) argCaptor.getValue().getBody();
    assertEquals("Test", reqBody.getTitle());
    assertTrue(argCaptor.getValue().getHeaders().getAccept().stream()
        .anyMatch( type -> type.toString().equals(MediaType.APPLICATION_JSON_VALUE.toString())));
  }
}
