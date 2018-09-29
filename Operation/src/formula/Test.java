package formula;


import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Formula a, b, c, d, e, f, g;
        ArrayList<Formula> list = new ArrayList<Formula>();

        a = new Formula(Formula.Operators.ADD, 3); // 3
        b = new Formula(Formula.Operators.ADD, 4, 1, 2); // 4'1/2
        c = new Formula(Formula.Operators.SUB, 8, 6); // -8/6
        d = new Formula(Formula.Operators.DIV, 6, 1, 3); // 除 6'1/3

       /* list.add(b);
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
//        System.out.println(f.symbol+ "*("+ f.integer + "'" + f.numerator+"/"+ f.denominator+")");*/

//        ArrayList<Formula> list3 = new ArrayList<Formula>();
//        list3.add(a);
//        list3.add(b);
//        g = new Formula(Formula.Operators.ADD, list3);
//        ArrayList<Formula> list4 = new ArrayList<Formula>();
//        list4.add(c);
//        list4.add(g);
//        list4.add(d);
//        f = new Formula(Formula.Operators.ADD, list4);
//        f.print();
//        System.out.println(f.output());
//        f.print();*/
//        String s ;
//        s = f.symbol+ "*("+ f.integer + "'" + f.numerator+"/"+ f.denominator+")";
//        System.out.println(s);

//        f = new Formula();
//        f.push(a);
//        f.push(b);
//        e = new Formula(Formula.Operators.DIV);
//        e.push(a);
//        e.push(b);
//        g = new Formula();
//        g.push(f);
//        g.push(e); // g = f ÷ e
//
//        g.print();
//        String s = g.output();
//        System.out.println(s);

//        f = Formula.calculateFormulaString("1 + 1/2");
//        f = Formula.calculateFormulaString("(1 + 1/2)*4");
        f = Formula.calculateFormulaString("(4 - 2'1/5) * (1 + 1/2)");
//        System.out.println(f.numerator);
//        f.print();
        System.out.println(f.output());
    }
}
