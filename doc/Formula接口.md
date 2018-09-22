# Formula

## 1. 初始化 Formula 实例

### 生成单项式
- 初始化 0 值
```java
public Formula()
```
- 初始化`自然数`快捷接口。参数：（运算符，自然数）
```java
public Formula(Operators operator, int integer)
```
- 初始化`分数`快捷接口。参数：（运算符，分子，分母）
```java
public Formula(Operators operator, int numerator, int denominator)
```
- 初始化的`完整`接口。参数：（运算符，自然数，分子，分母）
```java
public Formula(Operators operator, int integer, int numerator, int denominator)
```

***

### 生成多项式
- 初始化的`完整`接口。参数：（运算符，Formula集）
```java
public Formula(Operators operator, ArrayList<Formula> list)
```

***

## 2. 判断正负
- 实例方法：正数返回 true，否则 false
```java
public Boolean isPositiveNumber()
```
该方法包含运算符的计算，如：
```java
Formula a = new Formula(Formula.Operators.SUB, -3) // 参数：（减，-3）
a.isPositiveNumber() // true
```

***

## 3. 输出式子字符串
- 无打印输出，返回式子的字符串
```java
public String _print()
```
- 控制台打印，同时返回式子的字符串
```java
public String print()
```

***

## 4. 向多项式增加一项
- 接口函数：
- 成功返回 true
- 失败返回 false，（计算结果为负数，则失败，不会进行添加）
```java
public Boolean push(Formula x)
```
例如：
```java
Formula a, b, c;
a = new Formula();
b = new Formula();
c = new Formula();
c.push(a)
c.push(b)
```
等同于：
```java
Formula a, b, c;
a = new Formula();
b = new Formula();

ArrayList<Formula> list = new ArrayList<Formula>();
list.add(a)
list.add(b)
c = new Formula(Formula.Operators.ADD, list)
```

***

## 5.获取单项式或多项式的值
- 创建完成，或 push 成功都可以直接获取`当前`式子的值：
```java
formula.integer // 整数部分
formula.numerator // 分子
formula.denominator // 分母
formula.symbol // 正负号
formula.operator // 运算符
```


