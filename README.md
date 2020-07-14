# java8实战学习

## 1. lambda表达式
### 1.1 什么是lambda表达式
### 1.2 什么样的场景能使用lambda表达式
### 1.3 lambda表达式实现一个接口的四种写法


## 2. 函数式编程
### 2.1  什么是函数式编程

### 2.2 什么是命令式编程

### 2.3 什么是函数式接口 FunctionalInterface

### 2.4 什么是 default 方法

### 2.5 default方法的意义

### 2.6 java8内置的常用函数式接口
#### (1). Predicate<T> 
断言 -> 输入T, 输出 boolean
#### (2). Consumer<T> 
消费一个输入 -> 输入T, 不输出(void)
#### (3). Supplier<T>
生产一个输出 -> 不输入, 输出T
#### (4). Function<T, R> 
 输入T, 输出R的函数
#### (5). UnaryOperator<T>
一元函数: 输入1个输出 1个:  类型都是T
#### (6). BinaryOperatior<T>
二元函数: 输入2个输出1个: 类型都是T
#### (7). BiFunction<T, U, R>
输入两个输出一个: 输入 T, U 输出 R, 常用于 reduce/sort等操作,

### 2.7 方法引用
#### (1). 静态方法的方法引用
#### (2). 实例方法的方法引用
#### (3). 构造方法的方法引用

### 2.8. 变量引用和隐式final
#### 为何内部类使用外部变量要是final的

## 3. stream流式编程

### 3.1. 外部迭代和内部迭代
#### (1). 什么是外部迭代
#### (2).  什么是内部迭代

### 3.2. 中间操作/终止操作/惰性求值
#### (1). 什么是中间操作?
返回还是流stream的操作, 就是中间操作, 例如 map操作
##### map
##### flatMap
##### distinct
##### limit
##### skip
##### filter
##### peek
##### sorted

##### (1.1) 有状态操作
有前后顺序依赖关系的操作, 比如 
`distinct`(前后不能相同) 
`sorted`(前后顺序要求)  
`limit`/`skip`(截取前面的/跳过前面的)

##### (1.2) 无状态操作
无前后依赖关系的操作: 
`map`/`mapToInt`/`flatMap`/`flatMapToInt`
`filter`
`peek`

#### (2). 什么是终止操作?
返回一个结果的(求和/汇总)等, 例如 sum/max/min/avg等
##### forEach
##### collect
##### reduce
##### toArray
##### min/max/count
##### findAny
##### findFirst
##### allMatch
##### anyMatch
##### noneMatch
##### (1.1) 短路操作
`findFirst`
`findAny`
`allMatch`
`anyMatch`
`noneMatch`
##### (1.2) 非短路操作
`forEach`
`forEachOrdered` 并行流中保证顺序的
`collect`
`toArray`
`reduce` 输入: BinaryOperator<T>
`min`
`max`
`count`

#### (3). 什么是惰性求值?
如果没有终止操作, 中间的操作动作都不会实际执行, 这就是惰性求值;

### 3.3 stream流的创建
#### (1) 集合
collection.stream
collection.parallelStream

#### (2) 数组
array.stream
Stream.of

#### (3). 数字
##### IntStream
IntStream.range
IntStream.rangeClosed
##### LongStream
LongStream.range
LongStream.rangeClosed
##### Random
Random.longs
Random.ints
Random.doubles

#### (4).自己创建
Stream.generate
Stream.iterate

### 3.4 并行流

#### (1). parallel()
#### (2). 并行流默认使用的线程池
ForkJoinPool.commonPool
#### (3). 自定义线程池
如何自定义线程池, 并使用到流并行时?

### 3.5 收集器 collect

#### (1). 收集对象的属性列表为一个新集合
collect(Collectors.toList())
#### (2). 统计信息汇总
IntSummaryStatistics ageSumStats = students.stream().collect(Collectors.summarizingInt(Student::getAge))
#### (3). 分块:使用断言(一分为二)
Map<Boolean, List<Student>> genders = students.stream().collect(Collectors.partitioningBy(s->s.getGender() == Gender.MALE))
#### (4). 分组:使用属性(一分为N, 类似sql groupby)
Map<Grade, List<Student>> grades = students.stream().collect(Collectors.groupingBy(Student::getGrade))
#### (5). 分组并汇总计数
Map<Grade, Long> gradeCount = students.stream().collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
counting()可以替换为同类的max/min/avg等
#### (6). 小结
##### Collectors.toList()
##### Collectors.toCollection()
##### Collectors.summarizingInt(x)
##### Collectors.partitioningBy(x)
##### Collectors.groupingBy(x)
##### Collectors.counting()

### 3.6 stream运行机制

#### (1). 链式调用
所有操作链式调用, 1个元素只迭代一次;
#### (2). 属性sourceStage
每一个中间操作返回一个新的stream流, 流内有一个属性: sourceStage; 指向同一个地方: 即 链表的头 Head
#### (3). Head -> nextStage -> nextStage -> null 
stream接口的实现类 
`java.util.stream.AbstractPipeline`
`java.util.stream.ReferencePipeline`
属性: sourceStage --> ReferencePipeline$Head
![Stream流继承关系](https://image-static.segmentfault.com/427/965/4279653472-5f0d44f5ccc7b)
#### (4). 无状态操作运行机制
完全链式调用: s1.a操作完->s1.b
#### (5). 有状态操作运行机制

```
public void testStream() {
    Random r = new Random();
    Stream.generate(() -> r.nextInt()).limit(500)
            .peek(s -> System.out.println("peek: " + s)) // 1
            .filter(s -> {  // 2
                System.out.println("filter: " + s);
                return s > 1000000;
            })
            .sorted((i1, i2) -> { // 3.
                System.out.println("排序:" + i1 + ", " + i2);
                return i1.compareTo(i2);
            })
            .peek(s -> System.out.println("peek2:" + s)) //4.
            .count(); 
}
```

1,2 都是无状态操作; 3是有状态操作; 4是无状态操作
3的有状态操作会截断 1, 2的无状态操作;
本来是1, 2的链式调用: s1.a->s1.b->s1.c 现在会变成: s1.a() ->s2.a() -> s1.b() ->s2.b()
output: 无sorted: 可以看到`纯无状态操作是纯链式: peek->filter->peek2`

```shell
peek: 2047843427
filter: 2047843427
peek2:2047843427
peek: -1210662664
filter: -1210662664
peek: 835825054
filter: 835825054
peek2:835825054
peek: 2068471207
filter: 2068471207
peek2:2068471207
peek: -1139851578
filter: -1139851578
peek: -885776051
filter: -885776051
peek: 481902862
filter: 481902862
peek2:481902862
peek: 684461691
filter: 684461691
peek2:684461691
peek: 1417449012
filter: 1417449012
peek2:1417449012
peek: -40633821
filter: -40633821

```

output: 有sorted: `可以看到后面的无状态操作peek2被有状态的sorted打断, 变成: peek->filter... peek->filter 排序all, peek2 all`

```shell
peek: -607778068
filter: -607778068
peek: 50926402
filter: 50926402
peek: -774310924
filter: -774310924
peek: 342023904
filter: 342023904
peek: 26606322
filter: 26606322
peek: 693727663
filter: 693727663
peek: -334751306
filter: -334751306
peek: -960784614
filter: -960784614
peek: 522967780
filter: 522967780
peek: -2144851449
filter: -2144851449
排序:342023904, 50926402
排序:26606322, 342023904
排序:26606322, 342023904
排序:26606322, 50926402
排序:693727663, 50926402
排序:693727663, 342023904
排序:522967780, 342023904
排序:522967780, 693727663
peek2:26606322
peek2:50926402
peek2:342023904
peek2:522967780
peek2:693727663
```

1, 2还是链式调用; 中间的3执行完后, 隔断了4, 所以4不会和1,2承续链式调用, 而是单独执行;

#### (6) 并行操作: parallel()
在4后加5: parallel(): 
`sorted()` 有状态操作不会并行; 1,2,4都会使用 ForkJoinPool线程池并行执行:

```
@Test
public void testStream() {
    Random r = new Random();
    long count = Stream.generate(() -> r.nextInt()).limit(10)
            .peek(s -> System.out.println(Thread.currentThread().getName() + " peek: " + s)) // 1
            .filter(s -> {  // 2
                System.out.println(Thread.currentThread().getName() + " filter: " + s);
                return s > 1000000;
            })
            .sorted((i1, i2) -> { // 3.
                System.out.println(Thread.currentThread().getName() + " 排序:" + i1 + ", " + i2);
                return i1.compareTo(i2);
            })
            .peek(s -> System.out.println(Thread.currentThread().getName() + " peek2:" + s)) //4.
            .parallel()
            .count();
    System.out.println(count);
}
```

```shell
ForkJoinPool.commonPool-worker-1 peek: -1644694686
ForkJoinPool.commonPool-worker-1 filter: -1644694686
ForkJoinPool.commonPool-worker-1 peek: 1524371421
ForkJoinPool.commonPool-worker-1 filter: 1524371421
ForkJoinPool.commonPool-worker-1 peek: -1937453784
ForkJoinPool.commonPool-worker-1 filter: -1937453784
ForkJoinPool.commonPool-worker-1 peek: 991114309
ForkJoinPool.commonPool-worker-1 filter: 991114309
ForkJoinPool.commonPool-worker-1 peek: -109655961
ForkJoinPool.commonPool-worker-1 filter: -109655961
ForkJoinPool.commonPool-worker-1 peek: 878490064
ForkJoinPool.commonPool-worker-1 filter: 878490064
ForkJoinPool.commonPool-worker-1 peek: 2031919515
ForkJoinPool.commonPool-worker-1 filter: 2031919515
ForkJoinPool.commonPool-worker-1 peek: -1855129379
ForkJoinPool.commonPool-worker-1 filter: -1855129379
ForkJoinPool.commonPool-worker-1 peek: 1897985020
ForkJoinPool.commonPool-worker-1 filter: 1897985020
ForkJoinPool.commonPool-worker-1 peek: 352116584
ForkJoinPool.commonPool-worker-1 filter: 352116584
main 排序:991114309, 1524371421
main 排序:878490064, 991114309
main 排序:2031919515, 878490064
main 排序:2031919515, 991114309
main 排序:2031919515, 1524371421
main 排序:1897985020, 1524371421
main 排序:1897985020, 2031919515
main 排序:352116584, 1524371421
main 排序:352116584, 991114309
main 排序:352116584, 878490064
ForkJoinPool.commonPool-worker-6 peek2:991114309
main peek2:1524371421
ForkJoinPool.commonPool-worker-3 peek2:352116584
ForkJoinPool.commonPool-worker-2 peek2:2031919515
ForkJoinPool.commonPool-worker-7 peek2:1897985020
ForkJoinPool.commonPool-worker-4 peek2:878490064
6
```

结论: `有状态的并行操作"不一定"并行` 为啥是"不一定"?

因为官方并未明说, 但是观察下来, 是非并行的!



### 3.7 小结链式调用原理: 

1. stream链式调用原理: ReferencePipeline
2. 每个中间操作产生一个流(新的流)
3. 新流和原流的关系: 新流内有个sourceStage->指向原流的Head节点(ReferencePipeline$Head)
4. 链式调用的维护: ReferencePipeline中的 nextStage -> nextStage -> null来维护链;

### 3.8 小结2

1. 有状态操作 破坏链式调用
2. 并行操作中有状态操作不一定并行, 如排序!
