package com.nimsoc.ai.spring.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }


    /**
     * This endpoint uses the ChatClient to send a prompt to the AI model and returns the response.
     */
    @GetMapping("/chat")
    public String chat() {
        return chatClient
        .prompt()
        .user("Tell me an interesting fact about Java?")
        .call()
        .content();
    }


    /**
     * This endpoint uses the ChatClient to send a prompt to the AI model and returns the response as a stream. 
     */
    @GetMapping("/stream")
    public Flux<String> stream() {
        return chatClient
        .prompt()
        .user("I am visiting Romania soon, can you give me 10 places i must see?")
        .stream()
        .content();
    }

    /**
     * This endpoint uses the ChatClient to send a prompt to the AI model and returns the response.
     */
    @GetMapping("/joke")
    public ChatResponse joke() {
        return chatClient
            .prompt()
            .user("Tell me a joke about cats?")
            .call()
            .chatResponse();
    }

}
