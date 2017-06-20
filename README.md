# Java学习
Java核心技术结合源码验证

主要是对Java基础有更深入的认识，不仅仅是应用，而要结合源码和应用场景。

包含集合，多线程，反射等内容。


## 简介

### 基础
- [FinalDemo](src/main/java/com/iknowers/learning/base/FinalDemo/java) final关键字用法
- [FinallyReturnDemo](src/main/java/com/iknowers/learning/base/FinallyReturnDemo/java) finally返回优先
- [StackDeepDemo](src/main/java/com/iknowers/learning/base/StackDeepDemo/java) 测试栈的深度
- [StringJoinDemo](src/main/java/com/iknowers/learning/base/StringJoinDemo/java) String#join()
- [SingletonDemo](src/main/java/com/iknowers/learning/base/SingletonDemo.java) 基于静态内部类的单例

### 注解
- [InterceptorParseDemo](src/main/java/com/iknowers/learning/annotation/interceptor/InterceptorParseDemo/java) 基于注解的拦截器实现

### 集合
- [ConcurrentModificationExceptionDemo](src/main/java/com/iknowers/learning/collection/ConcurrentModificationExceptionDemo.java) 并发修改异常模拟
- [MapDemo](src/main/java/com/iknowers/learning/collection/MapDemo.java) Map#computeIfAbsent() 测试
- [SimpleBlockingQueueDemo](src/main/java/com/iknowers/learning/collection/SimpleBlockingQueueDemo.java)  一个简单的阻塞队列的实现
- [WeakHashMapDemo](src/main/java/com/iknowers/learning/collection/WeakHashMapDemo.java) WeakHashMap 测试

### 并发
- [CompletableFutureDemo](src/main/java/com/iknowers/learning/concurrent/CompletableFutureDemo.java) CompletableFuture
- [CountDownLatchDemo](src/main/java/com/iknowers/learning/concurrent/CountDownLatchDemo.java) 闭锁应用
- [CyclicBarrierDemo](src/main/java/com/iknowers/learning/concurrent/CyclicBarrierDemo.java) CyclicBarrier 应用
- [ExecutorServiceDemo](src/main/java/com/iknowers/learning/concurrent/ExecutorServiceDemo.java) 线程池应用
- [MutexAQS](src/main/java/com/iknowers/learning/concurrent/MutexAQS.java) 基于AQS实现的互斥锁
- [ReadWriteLockDemo](src/main/java/com/iknowers/learning/concurrent/ReadWriteLockDemo.java) 读写锁实例
- [ReentrantLockDemo](src/main/java/src/main/java/com/iknowers/learning/concurrent/ReentrantLockDemo.java) 重入锁实例
- [ScheduledExecutorServiceDemo](src/main/java/com/iknowers/learning/concurrent/ScheduledExecutorServiceDemo.java) 调度服务
- [ThreadLocalDemo](src/main/java/com/iknowers/learning/concurrent/ThreadLocalDemo.java) ThreadLocal实例
- [UnsafeDemo](src/main/java/com/iknowers/learning/concurrent/UnsafeDemo.java) 通过反射获得Unsafe实例
- [WaitNotifyDemo](src/main/java/com/iknowers/learning/concurrent/WaitNotifyDemo.java) 基于Object#wait(), notify()的生产者和消费者案例
- [YieldDemo](src/main/java/com/iknowers/learning/concurrent/YieldDemo.java) Thread#yield() 与优先级相关

### 枚举
- [EnumDemo](src/main/java/com/iknowers/learning/enuming/EnumDemo.java) 枚举 Enum#values() Enum#valueOf()应用

### GC
- [GCDemo](src/main/java/com/iknowers/learning/gc/GCDemo.java) 设定不同VM参数，观察不同GC算法日志，minor GC和Full GC的分析

### 泛型
- [GenericParamDemo](src/main/java/com/iknowers/learning/generic/GenericParamDemo.java) 泛型PECS
- [SimpleDemo](src/main/java/com/iknowers/learning/generic/SimpleDemo.java) Class对象比较

### OOM
- [OOMDemo](src/main/java/com/iknowers/learning/oom/OOMDemo.java) 触发OOM，通过Eclipse MAT分析dump分析Root Cause
- [DirectMemoryOOMDemo](src/main/java/com/iknowers/learning/oom/DirectMemoryOOMDemo.java) 直接内存溢出

### 反射
- [ReferenceDemo](src/main/java/com/iknowers/learning/reflect/ReferenceDemo.java) 软引用，弱引用和虚引用测试
- [ArrayDemo](src/main/java/com/iknowers/learning/reflect/ArrayDemo.java) Array反射
- [ClassLoadDemo](src/main/java/com/iknowers/learning/reflect/ClassLoadDemo.java) 类加载测试
- [ConstructorDemo](src/main/java/com/iknowers/learning/reflect/ConstructorDemo.java) Constructor反射
- [FieldDemo](src/main/java/com/iknowers/learning/reflect/FieldDemo.java) Field反射
- [JdkProxyDemo](src/main/java/com/iknowers/learning/reflect/JdkProxyDemo.java) JDK动态代理
- [CglibProxyDemo](src/main/java/com/iknowers/learning/reflect/CglibProxyDemo.java) CGLIB动态代理
- [MethodDemo](src/main/java/com/iknowers/learning/reflect/MethodDemo.java) Method反射
- [PathClassLoaderDemo](src/main/java/com/iknowers/learning/reflect/PathClassLoaderDemo.java) 自定义类加载器
- [RefectionInfoDemo](src/main/java/com/iknowers/learning/reflect/RefectionInfoDemo.java) 类反射信息查看
- [DynamicProxyPerformanceCompDemo](src/main/java/com/iknowers/learning/reflect/DynamicProxyPerformanceCompDemo.java) 动态代理性能比较

### 流
- [StreamDemo](src/main/java/com/iknowers/learning/stream/StreamDemo.java) 基于流的统计
- [StreamMethodDemo](src/main/java/com/iknowers/learning/stream/StreamMethodDemo.java) 流方法练习

### 线程
- [DifferentMonitorDemo](src/main/java/com/iknowers/learning/thread/DifferentMonitorDemo.java) 监视锁测试
- [InterruptDemo](src/main/java/com/iknowers/learning/thread/InterruptDemo.java) 中断的捕获和处理
- [ScheduledDemo](src/main/java/com/iknowers/learning/thread/ScheduledDemo.java) ScheduledExecutorService 与 Timer 的区别
- [ThreadStateDemo](src/main/java/com/iknowers/learning/thread/ThreadStateDemo.java) Java线程各个状态模拟
- [TimeoutDemo](src/main/java/com/iknowers/learning/thread/TimeoutDemo.java) 线程超时检验
- [WaitInterruptDemo](src/main/java/com/iknowers/learning/thread/WaitInterruptDemo.java) 线程中断状态测试
- [WaitNotifyDemo](src/main/java/com/iknowers/learning/thread/WaitNotifyDemo.java) Object#wait(), Object#notify() 协作
- [WaitNotifyTimeoutDemo](src/main/java/com/iknowers/learning/thread/WaitNotifyTimeoutDemo.java) Object#wait(timeout)测试
- [ThreadStackTraceDemo](src/main/java/com/iknowers/learning/thread/ThreadStackTraceDemo.java) 查看JVM线程栈


持续更新中...

我的博客： http://www.iknowers.com
























