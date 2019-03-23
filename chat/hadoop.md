## Haddop相关
**1.Hadoop是什么**

关键点：为大数据提供存储、管理、计算功能分布式服务；

**2.HDFS与一般测存储系统相比，有哪些特点**

关键点：架构实现上来讲 - 分布式、容错性、可以运行在比较廉价的硬件之上同时兼容很多类型的硬件

应用场景上来说 - 大数据、可以提供高吞吐流式访问、延时性略差、特别适用于一次写入多次读取的场景（可以对文件进行追加、截断，但修改不太方便）

**3.详细描述一下MapReduce的过程**

Map：完成集合到集合的映射，集合的元素是(key,value);这个映射可以是一对一、一对多的，映射前后key/value值类型也可以不同。

详细的流程：map->group->sorted-partitioned 最终partition之后的数量一般于reducer需要执行的task的数量相等（注意不是和reduceer数量相等）。其中group过程是将转换后的key/value 集合按照key进行聚合，然后再排序，分区。

Map方法：map(WritableComparable, Writable, Context) ；

Job.setCombinerClass(Class)可以设置再Map端进行一些reduce操作，减少网络传输的数据量。
