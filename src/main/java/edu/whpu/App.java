package edu.whpu;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.JdkClientHttpRequestFactory;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
@MapperScan("edu.whpu.mapper")
@Slf4j
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }

//    @Bean
//    ChatClient chatClient(ChatClient.Builder builder) {
//        return builder.defaultSystem("你是一个聪明的AI编程助手")
//                .defaultAdvisors(new SimpleLoggerAdvisor())
//                .build();
//    }


//    @Bean
//    RestClientCustomizer aiWiretapCustomizer() {
//        return builder -> builder
//                .requestFactory(new BufferingClientHttpRequestFactory(
//                        new JdkClientHttpRequestFactory()))          // 缓冲响应体,允许读两次
//                .requestInterceptor((request, body, execution) -> {
//                    log.info("AI Request {} {}\n{}", request.getMethod(), request.getURI(),
//                            new String(body, StandardCharsets.UTF_8));
//                    ClientHttpResponse resp = execution.execute(request, body);
//                    log.info("AI Response {}\n{}", resp.getStatusCode(),
//                            new String(resp.getBody().readAllBytes(), StandardCharsets.UTF_8));
//                    return resp;
//                });
//    }
}
