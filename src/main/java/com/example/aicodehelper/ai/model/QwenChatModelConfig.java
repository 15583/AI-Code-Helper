package com.example.aicodehelper.ai.model;

import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.community.model.dashscope.QwenStreamingChatModel;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import jakarta.annotation.Resource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "langchain4j.community.dashscope.chat-model")
@Data
public class QwenChatModelConfig {

    private String modelName;

    private String apiKey;

    @Value("${langchain4j.community.dashscope.streaming-chat-model.api-key}")
    private String streamingApiKey;

    @Value("${langchain4j.community.dashscope.streaming-chat-model.model-name}")
    private String streamingModelName;

    @Resource
    private ChatModelListener chatModelListener;

    @Bean
    public ChatLanguageModel myQwenChatModel() {
        return QwenChatModel.builder()
                .apiKey(apiKey)
                .modelName(modelName)
                .listeners(List.of(chatModelListener))
                .build();
    }

    @Bean
    public StreamingChatLanguageModel qwenStreamingChatLanguageModel() {
        return QwenStreamingChatModel.builder()
                .apiKey(streamingApiKey)
                .modelName(streamingModelName)
                .build();
    }
}
