package edu.whpu.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class McpClientController {

//    @Autowired
//    private SyncMcpToolCallbackProvider toolCallbackProvider;
//
//    private final ChatClient chatClient;
//
//    public McpClientController(ChatClient.Builder chatClientBuilder) {
//        ToolCallback[] toolCallbacks = toolCallbackProvider.getToolCallbacks();
//        this.chatClient = chatClientBuilder
//                .defaultToolCallbacks(toolCallbacks)
//                .build();
//    }
//
//    @GetMapping(value = "/mcpInvoke")
//    public String chat(String message) {
//        return chatClient.prompt()
//                .user(message)
//                .call()
//                .content();
//    }

}
