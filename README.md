# AI Code Helper

AI 编程小助手 —— 基于 Spring Boot + Vue 3 的智能编程问答助手，支持流式对话，内置编程学习、求职面试等领域知识。

## 技术栈

| 层级 | 技术 |
|------|------|
| 后端框架 | Spring Boot 4.0.6 (Java 17) |
| AI 框架 | LangChain4j 1.0.0-beta3 |
| AI 模型 | 通义千问 Qwen-Max (DashScope) |
| 前端框架 | Vue 3 + Vite 5 |
| 通信方式 | SSE (Server-Sent Events) 流式响应 |

## 项目结构

```
ai-code-helper/
├── src/main/java/com/example/aicodehelper/
│   ├── AiCodeHelperApplication.java      # 应用入口
│   ├── ai/
│   │   ├── AiCodeHelper.java             # AI 对话接口
│   │   ├── AiCodeHelperService.java      # 聊天服务实现
│   │   ├── AiCodeHelperServiceFactory.java
│   │   ├── listener/                     # 聊天模型监听器
│   │   ├── mcp/                          # MCP 协议配置
│   │   ├── model/                        # 模型配置 (Qwen)
│   │   └── rag/                          # RAG 检索增强配置
│   ├── config/                           # 跨域等配置
│   └── controller/                       # API 控制器
├── src/main/resources/
│   ├── application.yml                   # 通用配置 (不含密钥)
│   ├── application-local.yml             # 本地敏感配置 (已 gitignore)
│   ├── system-prompt.txt                 # 系统提示词
│   └── docs/                             # 内置知识文档
├── ai-code-helper-frontend/
│   └── src/
│       ├── components/ChatRoom.vue       # 聊天界面主组件
│       └── api/chat.js                   # SSE 通信层
└── pom.xml
```

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- Node.js 18+

### 1. 配置 API Key

创建 `src/main/resources/application-local.yml`（已 gitignore，不会提交到仓库）：

```yaml
langchain4j:
  community:
    dashscope:
      chat-model:
        api-key: <你的 DashScope API Key>
      embedding-model:
        api-key: <你的 DashScope API Key>
      streaming-chat-model:
        api-key: <你的 DashScope API Key>
bigmodel:
  api-key: <你的 BigModel API Key>
```

也可以设置环境变量：

```bash
export DASHSCOPE_API_KEY=<你的 DashScope API Key>
export BIGMODEL_API_KEY=<你的 BigModel API Key>
```

### 2. 启动后端

```bash
cd ai-code-helper
./mvnw spring-boot:run
```

后端运行在 `http://localhost:8081`，上下文路径为 `/api`。

### 3. 启动前端

```bash
cd ai-code-helper-frontend
npm install
npm run dev
```

前端运行在 `http://localhost:5173`，API 请求自动代理到后端。

### 4. 访问

打开浏览器访问 `http://localhost:5173`，即可与 AI 编程小助手对话。

## API 接口

| 方法 | 路径 | 说明 |
|------|------|------|
| GET | `/api/ai/chat?memoryId=xxx&message=xxx` | SSE 流式聊天 |

## 特性

- 流式对话输出 (SSE)，打字机效果
- 多轮对话记忆 (基于 memoryId)
- 内置编程学习路线、面试题等知识文档
- MCP 协议支持，可扩展工具调用
- RAG 检索增强生成
- 通义千问 Qwen-Max 模型驱动
