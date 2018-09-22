package formula;

import java.util.ArrayList;

public class Formula {
    public static enum Operators {
        ADD, // 加
        SUB, // 减
        MUL, // 乘
        DIV  // 除
    }
    public Operators operator = Operators.ADD;
    public int integer = 0; // 整数部分
    public int numerator = 0; // 分子
    public int denominator = 1; // 分母
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
        this.integer = integer;
        list = null;
    }

    /**
     * 生成真分数单项式
     * @param operator
     * @param numerator
     * @param denominator
     */
    public Formula(Operators operator, int numerator, int denominator){
        this.operator = operator;
        this.integer = numerator / denominator;
        this.numerator = numerator % denominator;
        this.denominator = denominator;
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
        if(denominator <= 0) throw new Error("分母不得小于零");
        this.operator = operator;
        this.integer = integer + numerator / denominator;
        this.numerator = numerator % denominator;
        this.denominator = denominator;
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
        if(list == null) { // 单项式
            if(numerator > 0 && denominator > 0) str = numerator + "/" + denominator;
            if(integer > 0) {
                if(str != "") str = integer + "'" + str;
                else str = integer >= 0 ? integer + "" : "("+ integer +")";
            }
        } else { // 多项式
            int length = list.size();
            for(int i = 0; i < length; i++){
                Formula item = list.get(i);

                if(i != 0){ // 运算符
                    switch (item.operator){
                        case ADD: str += " + "; break;
                        case MUL: str += " * "; break;
                        case DIV: str += " ÷ "; break;
                    }
                }

                if(item.operator == Operators.SUB){
                    str += " - ";
                }

                if(item.list != null && item.list.size() > 1) { // 多项式
                    str += "("+ item._print() +")";
                } else { // 单项式
                    str += item._print();
                }
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
        int length = list.size(),
            start = 0,
            end = 0;
        if(length < 1) return;

        /**
         * 计算式子值
         */
        Formula a, b, c = null;
        a = list.get(0);
        for(int i = 1; i < length; i++){
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

        /**
         * 记录值
         */
        this.integer = c.integer;
        this.numerator = c.numerator;
        this.denominator = c.denominator;

        if(c.operator == Operators.SUB){ // 负值
            if(this.operator == Operators.SUB) this.operator = Operators.ADD;
            else if(this.operator == Operators.ADD) this.operator = Operators.SUB;
            else {
                this.integer = this.integer * -1;
                this.numerator = this.numerator * -1;
            }
        }
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
            default: return this._add(x, y);
        }
    }

    /**
     * 带符号加法
     * @param x
     * @param y
     * @return
     */
    public Formula _add(Formula x, Formula y){
        int integer, numerator, denominator, operatorX, operatorY, numeratorX, numeratorY;
        operatorX = x.operator == Operators.SUB ? -1 : 1;
        operatorY = y.operator == Operators.SUB ? -1 : 1;

        Operators operator;

        denominator = x.denominator * y.denominator;
        numeratorX = operatorX * (x.integer * denominator + x.numerator * y.denominator);
        numeratorY = operatorY * (y.integer * denominator + y.numerator * x.denominator);
        numerator = numeratorX + numeratorY;

        operator = numerator >= 0 ? Operators.ADD : Operators.SUB;
        numerator = numerator >= 0 ? numerator : numerator * -1;
        integer = numerator / denominator;
        numerator = numerator % denominator;

        if(numerator > 0){
            int a, b, c;
            a = denominator;
            b = numerator;
            while ((c = a % b) != 0){
                a = b;
                b = c;
            }
            numerator = numerator / b;
            denominator = denominator / b;
        }

        return new Formula(operator, integer, numerator, denominator);
    }

    /**
     * 除法
     * @param x
     * @param y
     * @return
     */
    public Formula _div(Formula x, Formula y){
        int integer, numerator, denominator, operatorX, operatorY, numeratorX, numeratorY, oper;
        Operators operator;

        operatorX = x.operator == Operators.SUB ? -1 : 1;
        operatorY = y.operator == Operators.SUB ? -1 : 1;

        numeratorX = x.integer * x.denominator + x.numerator;
        numeratorY = y.integer * y.denominator + y.numerator;

        numerator = numeratorX * y.denominator;
        denominator = x.denominator * numeratorY;

        oper = numerator * denominator * operatorX * operatorY; // 符号判别符
        operator = oper >= 0 ? Operators.ADD : Operators.SUB; // 判别符号
        numerator = numerator >= 0 ? numerator : numerator * -1; // 转正数
        denominator = denominator >= 0 ? denominator : denominator * -1; // 转正数

        integer = numerator / denominator;
        numerator = numerator % denominator;

        if(numerator > 0){
            int a, b, c;
            a = denominator;
            b = numerator;
            while ((c = a % b) != 0){
                a = b;
                b = c;
            }
            numerator = numerator / b;
            denominator = denominator / b;
        }

        return new Formula(operator, integer, numerator, denominator);
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

        operatorX = x.operator == Operators.SUB ? -1 : 1;
        operatorY = y.operator == Operators.SUB ? -1 : 1;

        numeratorX = x.integer * x.denominator + x.numerator;
        numeratorY = y.integer * y.denominator + y.numerator;

        numerator = numeratorX * numeratorY;
        denominator = x.denominator * y.denominator;

        oper = numerator * denominator * operatorX * operatorY; // 符号判别符
        operator = oper >= 0 ? Operators.ADD : Operators.SUB; // 判别符号
        numerator = numerator >= 0 ? numerator : numerator * -1; // 转正数
        denominator = denominator >= 0 ? denominator : denominator * -1; // 转正数

        integer = numerator / denominator;
        numerator = numerator % denominator;

        if(numerator > 0){
            int a, b, c;
            a = denominator;
            b = numerator;
            while ((c = a % b) != 0){
                a = b;
                b = c;
            }
            numerator = numerator / b;
            denominator = denominator / b;
        }

        return new Formula(operator, integer, numerator, denominator);
    }

    /**
     * 判断正数
     * @return Boolean
     */
    public Boolean isPositiveNumber(){
        int oper = this.operator == Operators.SUB ? -1 : 1,
        count = this.integer * this.denominator + this.numerator;
        return oper * count >= 0;
    }

    public static void main(String[] args) {
        Formula a, b, c;
        ArrayList<Formula> list = new ArrayList<Formula>();
        a = new Formula(Operators.ADD, 3); // + 3
        b = new Formula(Operators.ADD, 4, 1, 5); // + 4'1/5
        list.add(a);
        list.add(b);
        c = new Formula(Operators.ADD, list); // 3 + 4'1/5
        c.print();
        System.out.println(c.integer+"'"+c.numerator+"/"+c.denominator);
    }

    public void calculateFormulaString( ){ } // 读取字符串

}
