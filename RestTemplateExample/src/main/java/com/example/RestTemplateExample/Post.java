package com.example.RestTemplateExample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {
  private int id;
  private int userId;
  private String title;
  private String body;
}
