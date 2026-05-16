package com.example.aicodehelper;

import com.example.aicodehelper.ai.AiCodeHelper;
import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeHelperApplicationTests {

    @Resource
    private AiCodeHelper aiCodeHelper;

    @Test
    void contextLoads() {
    }

    @Test
    void chat() {
        aiCodeHelper.chat("你好");
    }



    @Test
    void chatWithMessage() {
        UserMessage userMessage = UserMessage.from(
                TextContent.from("描述图片"),
                ImageContent.from("https://iphoto.mac89.com/photo/180603/EPS-180603_133/8O0vFa43mR_small.jpg")
        );
        aiCodeHelper.chatWithMessage(userMessage);
    }
}
