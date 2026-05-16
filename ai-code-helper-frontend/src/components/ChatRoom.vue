<template>
  <div class="chat-room">
    <header class="chat-header">
      <h1>AI 编程小助手</h1>
      <span class="room-id">会话: {{ memoryId }}</span>
    </header>

    <div class="chat-messages" ref="messagesContainer">
      <div
        v-for="(msg, index) in messages"
        :key="index"
        :class="['message-row', msg.role]"
      >
        <div class="avatar">
          {{ msg.role === 'user' ? '我' : 'AI' }}
        </div>
        <div class="bubble">
          <div class="bubble-content">{{ msg.content }}</div>
          <div v-if="msg.role === 'assistant' && msg.streaming" class="cursor-blink">|</div>
        </div>
      </div>
    </div>

    <div class="chat-input-area">
      <textarea
        v-model="inputText"
        class="chat-input"
        placeholder="输入你的问题，按 Enter 发送，Shift+Enter 换行"
        :disabled="isStreaming"
        rows="3"
        @keydown.enter.exact.prevent="handleSend"
      ></textarea>
      <button
        class="send-btn"
        :disabled="!inputText.trim() || isStreaming"
        @click="handleSend"
      >
        {{ isStreaming ? '回复中...' : '发送' }}
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, nextTick, onMounted, onBeforeUnmount } from 'vue'
import { getMemoryId, sendMessage } from '../api/chat.js'

const memoryId = ref('')
const messages = ref([])
const inputText = ref('')
const isStreaming = ref(false)
const messagesContainer = ref(null)
let currentEventSource = null

onMounted(() => {
  memoryId.value = getMemoryId()
  messages.value.push({
    role: 'assistant',
    content: '你好！我是 AI 编程小助手，很高兴为你服务。\n\n我可以帮你解答编程学习、面试求职相关的问题，也可以陪你一起阅读项目代码、分析技术方案。有什么想问的，随时告诉我！'
  })
})

onBeforeUnmount(() => {
  if (currentEventSource) {
    currentEventSource.close()
  }
})

function scrollToBottom() {
  nextTick(() => {
    if (messagesContainer.value) {
      messagesContainer.value.scrollTop = messagesContainer.value.scrollHeight
    }
  })
}

function handleSend() {
  const text = inputText.value.trim()
  if (!text || isStreaming.value) return

  messages.value.push({ role: 'user', content: text })
  messages.value.push({ role: 'assistant', content: '', streaming: true })
  inputText.value = ''
  isStreaming.value = true
  scrollToBottom()

  const assistantMsg = messages.value[messages.value.length - 1]

  currentEventSource = sendMessage(text, {
    onMessage(chunk) {
      assistantMsg.content += chunk
      scrollToBottom()
    },
    onComplete() {
      assistantMsg.streaming = false
      isStreaming.value = false
      currentEventSource = null
    },
    onError(err) {
      assistantMsg.content = assistantMsg.content || '请求失败，请重试'
      assistantMsg.streaming = false
      isStreaming.value = false
      currentEventSource = null
      console.error(err)
    }
  })
}
</script>

<style scoped>
.chat-room {
  display: flex;
  flex-direction: column;
  height: 100vh;
  max-width: 900px;
  margin: 0 auto;
  background: #f5f5f5;
}

.chat-header {
  background: #409eff;
  color: #fff;
  padding: 16px 24px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  flex-shrink: 0;
}

.chat-header h1 {
  margin: 0;
  font-size: 20px;
}

.room-id {
  font-size: 12px;
  opacity: 0.8;
  font-family: monospace;
}

.chat-messages {
  flex: 1;
  overflow-y: auto;
  padding: 20px 24px;
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.message-row {
  display: flex;
  gap: 10px;
  max-width: 75%;
}

.message-row.user {
  align-self: flex-end;
  flex-direction: row-reverse;
}

.message-row.assistant {
  align-self: flex-start;
}

.avatar {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 13px;
  font-weight: bold;
  flex-shrink: 0;
  color: #fff;
}

.message-row.user .avatar {
  background: #409eff;
}

.message-row.assistant .avatar {
  background: #67c23a;
}

.bubble {
  background: #fff;
  padding: 12px 16px;
  border-radius: 12px;
  font-size: 14px;
  line-height: 1.7;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.08);
  white-space: pre-wrap;
  word-break: break-word;
}

.message-row.user .bubble {
  background: #409eff;
  color: #fff;
}

.bubble-content {
  display: inline;
}

.cursor-blink {
  display: inline;
  animation: blink 0.8s infinite;
  color: #409eff;
}

@keyframes blink {
  0%, 100% { opacity: 1; }
  50% { opacity: 0; }
}

.chat-input-area {
  padding: 16px 24px;
  background: #fff;
  border-top: 1px solid #e8e8e8;
  display: flex;
  gap: 12px;
  align-items: flex-end;
  flex-shrink: 0;
}

.chat-input {
  flex: 1;
  padding: 10px 14px;
  border: 1px solid #dcdfe6;
  border-radius: 8px;
  font-size: 14px;
  resize: none;
  outline: none;
  font-family: inherit;
  line-height: 1.5;
}

.chat-input:focus {
  border-color: #409eff;
}

.send-btn {
  padding: 10px 24px;
  background: #409eff;
  color: #fff;
  border: none;
  border-radius: 8px;
  font-size: 14px;
  cursor: pointer;
  white-space: nowrap;
  flex-shrink: 0;
}

.send-btn:hover:not(:disabled) {
  background: #337ecc;
}

.send-btn:disabled {
  background: #a0cfff;
  cursor: not-allowed;
}
</style>
