## Java语言基础相关的面试题
** 1.Java 基础数据类型有哪些？Integer和int的区别？**

关键点：默认值，内存空间，是否可用于集合，自动装包、拆包机制，对小于128的缓存；<br>
Integer一定在堆上，int可能在栈上，也可能在堆上；Integer在内存占用(需要存储引用+对象)以及回收上的耗费大于int（仅仅存储值）；
```JAVA
    Integer i = new Integer(100); int j = 100； System.out.print(i == j); // 拆包之后比较true
	Integer i = new Integer(100); Integer j = 100; System.out.print(i == j); //没有拆包过程，比较两个对象地址 false
	Integer i = 100; Integer j = 100; System.out.print(i == j); //没有new关键字，i j只想缓存中的同一个对象，true
	Integer i = 128; Integer j = 128; System.out.print(i == j); //超过127之后，便不会被缓存 false
```
** 2.有关String的存储，String s = new String("abc") + new String("abc")的内存分配。String&StringBuilder&StringBuffer的区别及应用场景 **

关键点：String采用char[] 数组存储，String对象存储在常量池或者堆上，使用引号的字符串（本质上也是字符串对象）存储在常量池中，通过new关键字创建的对象存放在堆上。
String&StringBuilder&StringBuffer三者的区别在于StringBuilder与StringBuffer与普通对象一样存放于堆上，易被垃圾回收器回收。
如果一个字符串对象需要被频繁的修改，那么最好使用StringBuilder，可以一定程度上节省内存。StringBuilder与StringBuffer的区别在于后者是线程安全的。

** 3.Serializable接口是做什么用的，如果一个类继承了这个接口需要添加什么属性，这个属性时做什么用的。如果某个字段不想被序列化应该怎么处理。
说一说其他经常被override的接口**

关键点：标识接口，允许对象在内存与文件(或者其他形式的数据流)之间进行序列化反序列化；
需要增加long类型的serializeVersionUID属性，主要用于版本控制；如果某个字段不需要序列化，可以使用transient 关键字修饰

** 4.关于注解了解哪些？四个元注解 以及 其他常用注解；这些注解应用的原理（如何在运行时获取代码中的注解）**

关键点：可以将注解理解为描述代码的数据，即元数据。
四个元注解 @Target 作用在何处、@Retention 注解的保留级别、@Documented该注解将被包含在java doc中、@Inherited 子类可以继承父类
注解往往与反射一起应用，通过反射获取注解 实现更多的功能。

** 5. java得到对象的几种方式 **

关键点：new 关键字、Class对象的newInstance方法，Constructor的newInstance方法，对象反序列化、clone方法
```JAVA
Employee emp = Employee.class.newInstance();

Constructor<Employee> constructor = Employee.class.getConstructor();
Employee emp3 = constructor.newInstance();
```

** 6. 内存泄漏举例 **  <br>
什么是内存泄漏，不被使用的对象但不能被GC回收，大部分情况是由于编码错误引起的；比如：带有close方法的对象没有调用close；zk连接注册的监听对象，在不使用之后没有取消监听；
