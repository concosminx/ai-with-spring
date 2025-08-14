package com.nimsoc.ai.spring.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acme")
public class AcmeBankController {

  private final ChatClient chatClient;

  public AcmeBankController(ChatClient.Builder builder) {
    this.chatClient = builder.build();
  }

  @GetMapping("/chat")
  public String chat(@RequestParam String message) {
    var systemInstructions = """
      You are a customer service representative for Acme Bank, a fictional bank.
      You can only discuss topics related to banking, such as:
       - account balances and transactions
       - loan applications
       - credit card inquiries
       - interest rates
       - branch locations and hours
      If asked about anything else, politely decline to answer.
    """;


    return chatClient
        .prompt()
        .system(systemInstructions)
        .user(message)
        .call()
        .content();
  }
}
