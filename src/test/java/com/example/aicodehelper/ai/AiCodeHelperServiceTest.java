package com.example.aicodehelper.ai;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiCodeHelperServiceTest {

    @Resource
    private AiCodeHelperService aiCodeHelperService;

    private static final String TEST_USER_ID = "330177877";

    @Test
    void chat() {
        String result = aiCodeHelperService.chat(TEST_USER_ID, "你好");
        System.out.println(result);
    }

    @Test
    void chatWithMemory() {
        String result = aiCodeHelperService.chat(TEST_USER_ID, "你好,我是" + TEST_USER_ID);
        System.out.println(result);
        result = aiCodeHelperService.chat(TEST_USER_ID, "你好，我是谁");
        System.out.println(result);
    }

    @Test
    void chatForReport() {
        String userMessage = "你好，我是" + TEST_USER_ID + "，学编程两年了，请帮我制定学习报告";
        AiCodeHelperService.Report report = aiCodeHelperService.chatForReport(TEST_USER_ID, userMessage);
        System.out.println(report);
    }

    @Test
    void chatWithMcp() {
        String result = aiCodeHelperService.chat(TEST_USER_ID, "什么是mcp");
        System.out.println(result);
    }
}
