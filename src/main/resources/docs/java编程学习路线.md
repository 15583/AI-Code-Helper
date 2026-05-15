# Java 编程学习路线 (详细增强版)

## 阶段一：Java 基础 (入门阶段 - 预计 1-2 个月)
*此阶段目标：掌握 Java 语法，能够编写简单的控制台程序，理解面向对象思想。*

- **环境搭建与初探**
    - JDK 8/11/17 (建议直接学习 17) 的安装与环境变量配置。
    - IntelliJ IDEA 的快捷键、Debug 技巧、Maven 集成。
    - 编写第一个 Hello World，理解 `public static void main` 的含义。
- **基本语法深度掌握**
    - **变量与常量**：作用域、命名规范。
    - **数据类型**：基本类型 vs 引用类型，自动装箱与拆箱。
    - **流程控制**：switch-case 的新语法 (Java 12+)，循环的性能差异。
- **面向对象 (OOP) 核心**
    - **类与对象**：构造方法、静态变量/方法 (static)。
    - **三大特性**：封装 (private/getter/setter)、继承 (extends/super)、多态 (向上转型/向下转型)。
    - **高级特性**：内部类、匿名内部类、Lambda 表达式基础。
- **核心类库与集合**
    - **String 深度解析**：字符串常量池、`intern()` 方法。
    - **集合框架 (重点)**：
        - `ArrayList` 扩容机制、`LinkedList` 结构。
        - `HashMap` 原理 (1.7 vs 1.8)、`ConcurrentHashMap`。
        - `Stream API`：filter, map, reduce, collect 等常用操作。
- **异常处理**
    - 异常体系：`Throwable`, `Error`, `Exception` (Checked vs Runtime)。
    - `try-with-resources` 自动资源管理。

## 阶段二：Java 进阶 (提升阶段 - 预计 2-3 个月)
*此阶段目标：理解 Java 底层机制，掌握并发编程，具备处理复杂业务逻辑的能力。*

- **多线程与并发编程 (JUC)**
    - **基础**：线程状态切换、`wait/notify`、`join`。
    - **并发工具类**：`CountDownLatch`, `CyclicBarrier`, `Semaphore`。
    - **线程池**：`ThreadPoolExecutor` 七大参数、四大拒绝策略。
    - **锁机制**：`synchronized` 锁升级过程、`ReentrantLock`、`AQS` 原理。
    - **原子类**：`CAS` 思想、`AtomicInteger`、`LongAdder`。
- **JVM 核心原理**
    - **内存区域**：堆、栈、方法区、程序计数器。
    - **垃圾回收 (GC)**：分代收集算法、常用收集器 (G1, ZGC)。
    - **类加载**：双亲委派机制、自定义类加载器。
- **Java 高级特性**
    - **反射**：动态获取类信息、操作属性和方法。
    - **注解**：自定义注解、元注解 (`@Retention`, `@Target`)。
    - **泛型**：通配符 (`? extends T`, `? super T`)、类型擦除。
- **网络编程与 IO**
    - **BIO/NIO/AIO**：理解同步阻塞 vs 异步非阻塞。
    - **Netty 基础**：Channel, EventLoop, Pipeline。

## 阶段三：主流框架与中间件 (应用阶段 - 预计 3-4 个月)
*此阶段目标：能够独立开发企业级后端应用，熟悉数据库设计与框架整合。*

- **数据库与持久层**
    - **MySQL**：复杂 SQL 编写、索引失效场景、事务 ACID、MVCC 原理。
    - **MyBatis/MyBatis-Plus**：动态 SQL、关联查询、分页插件。
- **Spring 家族全家桶**
    - **Spring 核心**：Bean 的生命周期、循环依赖解决方法、AOP 动态代理实现。
    - **Spring Boot**：`@SpringBootApplication` 原理、自动装箱原理、Starter 机制。
    - **Spring Security**：认证 (Authentication) 与授权 (Authorization) 流程。
- **中间件实战**
    - **Redis**：五大数据类型、持久化 (RDB/AOF)、缓存雪崩/击穿/穿透解决方案。
    - **消息队列 (MQ)**：RabbitMQ/RocketMQ/Kafka，解决削峰填谷、系统解耦。

## 阶段四：分布式与架构 (架构阶段 - 预计 3-6 个月)
*此阶段目标：理解分布式架构设计，掌握微服务治理，具备处理高可用、高并发系统的能力。*

- **微服务治理 (Spring Cloud Alibaba)**
    - **注册与配置**：Nacos 的使用与原理。
    - **服务调用**：OpenFeign 的配置与优化。
    - **熔断限流**：Sentinel 的流控规则、热点参数限流。
    - **分布式事务**：Seata 的三种模式 (AT, TCC, Saga)。
- **容器化与运维**
    - **Docker**：Dockerfile 编写、镜像管理。
    - **K8s**：Pod, Service, Deployment 基础概念。
- **系统架构设计**
    - **分层设计**：领域驱动设计 (DDD) 初探。
    - **性能调优**：JVM 参数调优、慢 SQL 诊断。

## 学习建议
1. **多写代码**：理论看一遍不如上手敲一遍。
2. **多看源码**：从 JDK 源码 (HashMap, String) 开始，再看 Spring 源码。
3. **保持好奇**：遇到问题深挖底层，不要只停留在“能跑通”。
4. **定期总结**：建议写技术博客或笔记。
