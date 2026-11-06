package edu.whpu.controller;

import edu.whpu.entity.Movie;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.template.st.StTemplateRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/demo.st")
    private Resource resource;

//    public ChatController(ChatClient chatClient) {
//        this.chatClient = chatClient;
//    }

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

    @GetMapping(value = "/chatTemplate", produces = "text/html;charset=UTF-8")
    public Flux<String> chatTemplate(String projectName) {
        PromptTemplate promptTemplate = new PromptTemplate("请帮我分析{project}的架构");
        Map<String, Object> promptMap = Map.of("project", projectName);
        String prompt = promptTemplate.render(promptMap);
        return chatClient.prompt(prompt)
                .stream()
                .content();
    }

    @GetMapping(value = "/chatTemplate1", produces = "text/html;charset=UTF-8")
    public Flux<String> chatTemplate1(String projectName) {
        PromptTemplate promptTemplate = PromptTemplate.builder()
                .renderer(StTemplateRenderer.builder().startDelimiterToken('<').endDelimiterToken('>').build())
                .template("请帮我分析<project>的架构")
                .build();
        Map<String, Object> promptMap = Map.of("project", projectName);
        String prompt = promptTemplate.render(promptMap);
        return chatClient.prompt(prompt)
                .advisors(new SimpleLoggerAdvisor())
                .stream()
                .content();
    }

    @GetMapping(value = "/chatTemplate2", produces = "text/html;charset=UTF-8")
    public Flux<String> chatTemplate2(String projectName, String role) {
        String userText = """
           请帮我分析Spring Cloud GateWay的整体架构
           """;

        Message userMessage = new UserMessage(userText);

        String systemText = """
          你是一个{role}
          """;

        SystemPromptTemplate systemPromptTemplate = new SystemPromptTemplate(systemText);
        Message systemMessage = systemPromptTemplate.createMessage(Map.of("projectName", projectName, "role", role));

        Prompt prompt = new Prompt(List.of(userMessage, systemMessage));
        return chatClient.prompt(prompt)
                .stream()
                .content();
    }

    @GetMapping(value = "/chatTemplate3", produces = "text/html;charset=UTF-8")
    public Flux<String> chatTemplate3(String projectName) {
        PromptTemplate promptTemplate = PromptTemplate.builder()
                .resource(resource)
                .variables(Map.of("project", projectName))
                .build();

        return chatClient.prompt(promptTemplate.render())
                .stream()
                .content();
    }

    @GetMapping(value = "/chatTemplate4")
    public Movie chatTemplate4() {
        return chatClient.prompt()
                .user("请推荐一部高分中文电影")
                .advisors(new SimpleLoggerAdvisor())
                .call()
                .entity(Movie.class);

    }

}
