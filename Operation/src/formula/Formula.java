package formula;

import java.util.ArrayList;

public class Formula {
    public static enum Operators {
        ADD, // 加
        SUB, // 减
        MUL, // 乘
        DIV  // 除
    }
    public Operators operator = Operators.ADD; // 实际运算符，用于运算。
    public int integer = 0; // 整数部分
    public int numerator = 0; // 分子
    public int denominator = 1; // 分母
    public int symbol = 1; // 代表正负
    public ArrayList<Formula> list; // 多项式

    /**
     * 初始化单项式
     */
    public Formula() {
        list = null;
    }

    /**
     * 生成自然数单项式
     * @param operator 运算符
     * @param integer 整数部分
     */
    public Formula(Operators operator, int integer){
        this.operator = operator;
        if(integer > 0){
            this.integer = integer;
        }else {
            this.integer = -1 * integer;
            this.symbol = -1;
        }
        list = null;
    }

    /**
     * 生成分数单项式
     * @param operator
     * @param numerator
     * @param denominator
     */
    public Formula(Operators operator, int numerator, int denominator){
        this.operator = operator;
        this.integer = numerator / denominator;
        if(numerator * denominator < 0) { // 整数部分
            this.symbol =  -1; // 数值正负
            this.integer = -1 * this.integer; // 正化
        }

        numerator = numerator % denominator; // 求余
        numerator = numerator < 0 ? -1 * numerator: numerator; // 正化
        denominator = denominator < 0 ? -1 * denominator: denominator; // 正化

        if(numerator > 0){ // 约分
            int a, b, c;
            a = denominator;
            b = numerator;
            while ((c = a % b) != 0){
                a = b;
                b = c;
            }
            this.numerator = numerator / b;
            this.denominator = denominator / b;
        }
        list = null;
    }

    /**
     * 生成单项式
     * @param operator 运算符
     * @param integer 整数部分
     * @param numerator 分子
     * @param denominator 分母
     */
    public Formula(Operators operator, int integer, int numerator, int denominator){
        this.operator = operator;
        if(denominator < 0){
            denominator = -1 * denominator;
            numerator = -1 * numerator;
        }
        numerator = integer * denominator + numerator;

        this.integer = numerator / denominator;
        if(numerator * denominator < 0) { // 整数部分
            this.symbol =  -1; // 数值正负
            this.integer = -1 * this.integer; // 正化
        }

        numerator = numerator % denominator; // 求余
        numerator = numerator < 0 ? -1 * numerator: numerator; // 正化
        denominator = denominator < 0 ? -1 * denominator: denominator; // 正化

        if(numerator > 0){ // 约分
            int a, b, c;
            a = denominator;
            b = numerator;
            while ((c = a % b) != 0){
                a = b;
                b = c;
            }
            this.numerator = numerator / b;
            this.denominator = denominator / b;
        }
        list = null;
    }

    /**
     * 生成多项式
     * @param operator
     * @param list
     */
    public Formula(Operators operator, ArrayList<Formula> list){
        this.operator = operator;
        this.list = list;
        this.injectFormulaValue();
    }

    /**
     * 无打印
     * @return 返回式子字符串
     */
    public String _print(){
        String str = "";
        if(list == null || list.size() == 0) { // 单项式
            if(numerator > 0 && denominator > 0) str = numerator + "/" + denominator;
            if(integer > 0) {
                if(str != "") str = integer + "'" + str;
                else str = integer + "";
            }
            if(str == "") str = "0";
            else if(symbol == -1) str += "( - "+ str +")";
        } else { // 多项式
            int length = list.size();
            for(int i = 0; i < length; i++){
                Formula item = list.get(i);
                if(i == 0) { // 首项
                    if(item.operator == Operators.SUB) str += " - ";
                }else {
                    switch (item.operator){
                        case SUB: str += " - ";break;
                        case ADD: str += " + ";break;
                        case MUL: str += " * ";break;
                        case DIV: str += " ÷ ";break;
                    }
                }
                if(item.list != null && item.list.size() > 1) str += '('+ item._print() +')';
                else str += item._print();
            }
        }
        return str;
    }

    /**
     * 控制台输出式子
     * @return 式子字符串
     */
    public String print(){
        String str = this._print() + " = ";
        System.out.println(str);
        return str;
    }

    /**
     * 记录式子数值
     * @param
     */
    public void injectFormulaValue(){
        if(list == null) return;
        int length = list.size(),
            start = 0,
            end = 0;
        if(length < 1) return;

        // 计算式子值
        Formula a, b, c = null;
        a = new Formula();
        for(int i = 0; i < length; i++){
            b = list.get(i);
            if(b.operator == Operators.DIV || b.operator == Operators.MUL){
                a = this.mergeValue(a, b);
            }else {
                if(c == null) c = a;
                else c = mergeValue(c, a);
                a = b;
            }
        }
        c = mergeValue(c, a);

        // 记录值
        this.integer = c.integer;
        this.numerator = c.numerator;
        this.denominator = c.denominator;
        this.symbol = c.symbol;
    }

    /**
     * 合并数值
     * @param x
     * @param y
     * @return 返回最终数值
     */
    public Formula mergeValue(Formula x, Formula y){
        switch (y.operator){
            case MUL: return this._mul(x, y);
            case DIV: return this._div(x, y);
            case SUB: return this._sub(x, y);
            default: return this._add(x, y);
        }
    }

    /**
     * 加法
     * @param x
     * @param y
     * @return
     */
    public Formula _add(Formula x, Formula y){
        int numerator, denominator, numeratorX, numeratorY;

        denominator = x.denominator * y.denominator; // 分母
        numeratorX = (x.integer * denominator + x.numerator * y.denominator) * x.symbol; // x 分子
        numeratorY = (y.integer * denominator + y.numerator * x.denominator) * y.symbol; // y 分子
        numerator = numeratorX + numeratorY; // 分子和

        return new Formula(x.operator, numerator, denominator);
    }

    /**
     * 减法
     * @param x
     * @param y
     * @return
     */
    public Formula _sub(Formula x, Formula y){
        int numerator, denominator, numeratorX, numeratorY;

        denominator = x.denominator * y.denominator; // 分母
        numeratorX = (x.integer * denominator + x.numerator * y.denominator) * x.symbol; // x 分子
        numeratorY = (y.integer * denominator + y.numerator * x.denominator) * y.symbol; // y 分子
        numerator = numeratorX - numeratorY; // 分子和

        return new Formula(x.operator, numerator, denominator);
    }

    /**
     * 除法
     * @param x
     * @param y
     * @return
     */
    public Formula _div(Formula x, Formula y){
        int numerator, denominator, numeratorX, numeratorY;

        numeratorX = (x.integer * x.denominator + x.numerator) * x.symbol;
        numeratorY = (y.integer * y.denominator + y.numerator) * y.symbol;
        numerator = numeratorX * y.denominator;
        denominator = numeratorY * x.denominator;

        return new Formula(x.operator, numerator, denominator);
    }

    /**
     * 乘法
     * @param x
     * @param y
     * @return
     */
    public Formula _mul(Formula x, Formula y){
        int integer, numerator, denominator, operatorX, operatorY, numeratorX, numeratorY, oper;
        Operators operator;

        numeratorX = (x.integer * x.denominator + x.numerator) * x.symbol;
        numeratorY = (y.integer * y.denominator + y.numerator) * y.symbol;
        numerator = numeratorX * numeratorY;
        denominator = x.denominator * y.denominator;

        return new Formula(x.operator, numerator, denominator);
    }

    /**
     * 判断正数
     * @return Boolean
     */
    public Boolean isPositiveNumber(){
        return this.symbol == 1;
    }

    /**
     * 向多项式推入一项
     * @param x
     * @return
     */
    public Boolean push(Formula x){
        if(this.list == null) this.list = new ArrayList<Formula>();
        int integer, denominator, numerator, symbol;
        integer = this.integer;
        denominator = this.denominator;
        numerator = this.numerator;
        symbol = this.symbol;

        this.list.add(x);
        this.injectFormulaValue();
        if(symbol != this.symbol){ // 结果为负数，失败
            this.list.remove(list.size() - 1);
            this.integer = integer;
            this.denominator = denominator;
            this.numerator = numerator;
            this.symbol = symbol;
            return false;
        }
        return true;
    } 

    /**
     * 输出算式运算结果字符串
     * @return
     */
    public String output(){
        String str = "";
        if(this.symbol == -1) str += "-";
        if(this.integer != 0) str += this.integer;
        if(this.denominator != 0 && this.numerator != 0) {
            if(this.integer != 0) str += "'" + this.numerator + "/" + this.denominator;
            else str += this.numerator + "/" + this.denominator;
        }
        return str;
    }

    public void calculateFormulaString( ){ } // 读取字符串

}
