package edu.whpu.controller;

import edu.whpu.entity.Movie;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .build();
    }

    @GetMapping("/ai")
    public String generate(@RequestParam String message) {
        return chatClient.prompt()  // 提示词
                .user(message)
                .advisors(new SimpleLoggerAdvisor())
                .call()
                .content();
    }

    @GetMapping(value = "/chat", produces = "text/html;charset=UTF-8")
    public Flux<String> chat(String message) {
        return chatClient.prompt()
                .user(message)
                .stream()
                .content();
    }

    @GetMapping(value = "/chat1", produces = "text/html;charset=UTF-8")
    public Flux<String> chat1(String message) {
        Prompt userPrompt = Prompt.builder()
                .messages(new UserMessage(message))
                .build();
        return chatClient.prompt(userPrompt)
                .stream()
                .content();
    }

}
