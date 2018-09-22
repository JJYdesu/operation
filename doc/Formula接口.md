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
