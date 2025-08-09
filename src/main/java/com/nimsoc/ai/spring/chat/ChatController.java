package com.nimsoc.ai.spring.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    @GetMapping("/chat")
    /**
     * This endpoint uses the ChatClient to send a prompt to the AI model and returns the response.
     */
    public String chat() {
        return chatClient
        .prompt()
        .user("Tell me a joke?")
        .call()
        .content();
    }

    @GetMapping("/stream")
    /**
     * This endpoint uses the ChatClient to send a prompt to the AI model and returns the response as a stream. 
     */
    public Flux<String> stream() {
        return chatClient
        .prompt()
        .user("Tell me 10 jokes?")
        .stream()
        .content();
    }

}
