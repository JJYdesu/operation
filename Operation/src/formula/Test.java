package formula;


import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Formula a, b, c;
        ArrayList<Formula> list = new ArrayList<Formula>();
        a = new Formula(Formula.Operators.ADD, 3); // + 3
        b = new Formula(Formula.Operators.SUB, 16, 5); // - 16/5
        list.add(a);
        list.add(b);
        c = new Formula(Formula.Operators.ADD, list); // 3 + 4'1/5
        c.print();
        System.out.println( c.symbol + "*(" +c.integer+"'"+c.numerator+"/"+c.denominator+")");
    }
}
