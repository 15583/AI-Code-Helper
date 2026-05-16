package com.example.aicodehelper.ai;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.Result;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AiCodeHelperService {

    @SystemMessage(fromResource = "system-prompt.txt")
    String chat(@MemoryId String userId, @UserMessage String userMessage);

    @SystemMessage(fromResource = "system-prompt.txt")
    Report chatForReport(@MemoryId String userId, @UserMessage String userMessage);

    record Report(String name, List<String> suggestionList) {}

    @SystemMessage(fromResource = "system-prompt.txt")
    Result<String> chatWithRag(@MemoryId String userId, @UserMessage String userMessage);

    @SystemMessage(fromResource = "system-prompt.txt")
    Flux<String> chatStream(@MemoryId String userId, @UserMessage String message);
}
