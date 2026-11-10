package edu.whpu;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

}
