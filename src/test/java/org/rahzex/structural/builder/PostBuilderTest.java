package org.rahzex.structural.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PostBuilderTest {

  @Test
  void testPostBuilder() {
    String category = "Question";
    String text = "Why does reddit exist?";
    String title = "Random thoughts";
    Post redditPost = Post.builder().title(title).text(text).category(category).build();

    assertEquals(category, redditPost.getCategory());
    assertEquals(text, redditPost.getText());
    assertEquals(title, redditPost.getTitle());
  }
}
