## Spring 框架知识

** 1. 说一说你对框架的理解 **

关键点：一是代码复用，可以让用户更加专注于业务逻辑，少些很多代码；
二是让项目结构更加合理(约定优于配置)、别人更加易懂；
三是代码更加健壮，在容错性、性能等方面更优。

** 2. 聊一聊spring的IOC机制，spring是如何创建对象的，如何获取bean（除了通过@Autowaired注解） **

关键点：Inversion of Control，译为控制反转，即将创建对象的权力交给框架进行。
通过反射创建对象，默认是单例模式，通过scope属性来指定每一个实例的作用域，如request、session范围，prototype 任何一个实例都是新的实例。<br>
详细创建过程：AnnotationUtils类找到使用相关注解的类，然后一系列的预处理，如是否为单例、是否有AOP相关注解（决定是否创建代理对象）、处理其他的一些注解如是否延迟创建等，如果满足创建条件则通过反射创建<br>
通过实现ApplicationContextAware接口获取bean；

** 3. 聊一聊spring的AOP机制 **

** 4. 使用过spring/spring boot哪些相关的模块 **

关键点：spring-boot-starter-web、spring-boot-starter-test、spring-boot-starter-cache、
spring-boot-starter-security、spring-boot-starter-data-redis、spring-session、spring-jpa、spring-boot-starter-actuator、
spring-boot-starter-thymeleaf

** 5. AOP中的自调用问题 **


** 6. @Transactional 注解及相关参数；事务的实现原理 **
关键点：此注解声明一个事务，参数主要有事务的传播行为、事务的隔离度、超时时间、是否是只读事务、
需要事务回滚的异常类型、事务不回滚但需要抛出的异常类型
实现原理是通过AOP机制


** 7.如何自定义filter，如何启动之后执行一些初始化逻辑 **
关键点：使用@bean注解在方法之上，方法的返回值是FilterRegisterBean的泛型。
启动初始化逻辑通过实现commandLineRunnner接口实现


** 8.用过哪些注解，每个注解的作用 **
关键点：@Qualifier 
