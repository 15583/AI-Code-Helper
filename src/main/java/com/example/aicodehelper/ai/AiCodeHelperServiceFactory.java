package com.example.aicodehelper.ai;

import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiCodeHelperServiceFactory {

    private final ChatLanguageModel myQwenChatModel;
    private final ContentRetriever contentRetriever;
    private final McpToolProvider mcpToolProvider;
    private final StreamingChatLanguageModel qwenStreamingChatLanguageModel;

    public AiCodeHelperServiceFactory(
            @Qualifier("myQwenChatModel") ChatLanguageModel myQwenChatModel,
            ContentRetriever contentRetriever,
            McpToolProvider mcpToolProvider,
            @Qualifier("qwenStreamingChatLanguageModel") StreamingChatLanguageModel qwenStreamingChatLanguageModel) {
        this.myQwenChatModel = myQwenChatModel;
        this.contentRetriever = contentRetriever;
        this.mcpToolProvider = mcpToolProvider;
        this.qwenStreamingChatLanguageModel = qwenStreamingChatLanguageModel;
    }

    @Bean
    public AiCodeHelperService aiCodeHelperService() {
        ChatMemoryProvider chatMemoryProvider = memoryId -> MessageWindowChatMemory.withMaxMessages(10);

        return AiServices.builder(AiCodeHelperService.class)
                .chatLanguageModel(myQwenChatModel)
                .streamingChatLanguageModel(qwenStreamingChatLanguageModel)
                .chatMemoryProvider(chatMemoryProvider)
                .contentRetriever(contentRetriever)
                .toolProvider(mcpToolProvider)
                .build();
    }
}
