package formula;


import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Formula a, b, c, d, e, f, g;
        ArrayList<Formula> list = new ArrayList<Formula>();

        a = new Formula(Formula.Operators.ADD, 3);
        b = new Formula(Formula.Operators.ADD, 4, 1, 2);
        c = new Formula(Formula.Operators.SUB, 8, 6);
        d = new Formula(Formula.Operators.DIV, 6, 1, 3);

        list.add(b);
        list.add(c);
        e = new Formula(Formula.Operators.ADD, list);
//        e.print();
//        System.out.println(e.symbol+ "*("+ e.integer + "'" + e.numerator+"/"+ e.denominator+")");
        ArrayList<Formula> list2 = new ArrayList<Formula>();
        list2.add(a);
        list2.add(e);
        list2.add(d);
        f = new Formula(Formula.Operators.ADD, list2);
//        f.print();
//        System.out.println(f.symbol+ "*("+ f.integer + "'" + f.numerator+"/"+ f.denominator+")");

        ArrayList<Formula> list3 = new ArrayList<Formula>();
        list3.add(c);
        list3.add(c);
        g = new Formula(Formula.Operators.ADD, list3);
        g.print();
        System.out.println(g.isPositiveNumber());
//        f.print();
        System.out.println(g.symbol+ "*("+ g.integer + "'" + g.numerator+"/"+ g.denominator+")");
    }
}
