let memoryId = null

export function getMemoryId() {
  if (!memoryId) {
    memoryId = `chat_${Date.now()}_${Math.random().toString(36).substring(2, 9)}`
  }
  return memoryId
}

export function sendMessage(message, { onMessage, onError, onComplete }) {
  const id = getMemoryId()
  const url = `/api/ai/chat?memoryId=${encodeURIComponent(id)}&message=${encodeURIComponent(message)}`

  const eventSource = new EventSource(url)

  eventSource.onmessage = (event) => {
    onMessage(event.data)
  }

  eventSource.onerror = () => {
    eventSource.close()
    if (eventSource.readyState === EventSource.CLOSED) {
      onComplete()
    } else {
      onError(new Error('SSE 连接异常'))
    }
  }

  return eventSource
}
