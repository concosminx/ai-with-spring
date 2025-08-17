package com.nimsoc.ai.spring.metrics;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class MetricChatController {

  private final ChatClient chatClient;

  public MetricChatController(ChatClient.Builder builder) {
    this.chatClient = builder.build();
  }

  @GetMapping
  public String chat(@RequestParam String prompt) {
    return chatClient.prompt()
        .user(prompt)
        .call()
        .content();
  }
}

