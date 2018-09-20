package formula;

import java.lang.reflect.Array;

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
    public Formula[] list;

    public Formula() {
        list = null;
    }

    /**
     * 返回字符串
     * @return
     */
    public String _print(){
        String str = "";
        if(list == null) { // 单项式
            if(numerator > 0 && denominator > 0) str = numerator + "/" + denominator;
            if(integer > 0) {
                if(str != "") str = integer + "'" + str;
                else str = integer + "";
            }
        } else { // 多项式
            int length = list.length;
            for(int i = 0; i < length; i++){
                Formula item = list[i];

                if(i != 0){ // 运算符
                    switch (item.operator){
                        case ADD: str += " + "; break;
                        case SUB: str += " - "; break;
                        case MUL: str += " * "; break;
                        case DIV: str += " ÷ "; break;
                    }
                }

                if(item.list != null && item.list.length > 1) { // 多项式
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
     */
    public void print(){
        System.out.println(_print());
    }

    public static void main(String[] args) { }

    public void calculateFormulaString( ){ } // 读取字符串

}
