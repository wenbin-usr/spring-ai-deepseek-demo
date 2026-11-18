package edu.whpu.controller;

import edu.whpu.service.BookTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
public class ToolsController {

    private final ChatClient chatClient;

    @Autowired
    private BookTools bookTools;

    public ToolsController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .build();
    }

    /**
     * 流式
     */
    @GetMapping(value = "/toolChat", produces = "text/html;charset=UTF-8")
    public Flux<String> toolChat(String bookName) {
        PromptTemplate promptTemplate = new PromptTemplate("请帮我查询{bookName}的价格");
        Map<String, Object> promptMap = Map.of("bookName", bookName);
        String prompt = promptTemplate.render(promptMap);
        return chatClient.prompt(prompt)
                .tools(bookTools)
                .advisors(new SimpleLoggerAdvisor())
                .stream()
                .content();
    }

    /**
     * 非流式
     */
    @GetMapping("/toolChat1")
    public String toolChat1(String bookName) {
        PromptTemplate promptTemplate = new PromptTemplate("请帮我查询{bookName}的价格");
        Map<String, Object> promptMap = Map.of("bookName", bookName);
        String prompt = promptTemplate.render(promptMap);
        return chatClient.prompt(prompt)  // 提示词
                .tools(bookTools)
                .advisors(new SimpleLoggerAdvisor())
                .call()
                .content();
    }

}
