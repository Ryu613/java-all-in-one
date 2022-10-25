# Java 8 实战 读后感
## Java8 里主要设计目标：
1. 编程更灵活便捷
   - 引入了函数式编程（思想：把行为参数化）
     - 方法引用: 双冒号"::"
     - lambda匿名函数: "() ->"
2. 充分利用多核CPU性能资源，降低并发程序编写难度
   - 引入了流(Stream)
## 具体技术点的个人感想
### 1. lambda
- 一般来说确实比匿名类和行为参数化的方式要简单易理解，最主要是这种lambda方式在编程中实际上确实只需要提供最关键的信息（还需要写一些语法糖标记代码），而不需要重复一大堆模板化的代码。
    但是，如果lambda里面内部有相对复杂一些的处理逻辑，或者需要访问一些外部变量的情况，就不是那么有突出优点的方式了
- lambda就是一种精简的，可以表示一个执行函数的语法糖，并可以像一个变量一样传递到其他函数中。
### 2. stream
- 用stream避免多层嵌套，嵌套多了易读性下降
- 尽量保持stream简单化，可并行化，避免外部排序
### 3. Collectors
- 里面提供的collector可以嵌套，但是嵌套太多层可读性很差，不要滥用
- 