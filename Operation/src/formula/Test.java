package formula;


import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        Formula a, b, c, d, e, f, g,h;
        ArrayList<Formula> list = new ArrayList<Formula>();

        a = new Formula(Formula.Operators.ADD, 3); // 3
        b = new Formula(Formula.Operators.ADD, 4, 1, 2); // 4'1/2
        c = new Formula(Formula.Operators.SUB, 8, 6); // -8/6
        d = new Formula(Formula.Operators.DIV, 6, 1, 3); // 除 6'1/3
        String s;
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

        f = new Formula();
        g = new Formula(Formula.Operators.SUB);
        h = new Formula();
        f.push(a);
        f.push(b);
        f.push(d);
        s = f._print();
        System.out.println(s);
        e = Formula.calculateFormulaString(s);
        System.out.println(e.output());








//        f.print();

//        f = Formula.calculateFormulaString("1 + 1/2");
//        f = Formula.calculateFormulaString("(1 + 1/2)*4");
//        f = Formula.calculateFormulaString("(4'1/2 ÷ 8'2/3) + (10 ÷ 7'2/7)");
//        f = Formula.calculateFormulaString("10'4/5 ÷ 3");
//        f = Formula.calculateFormulaString("(7'8/9 * 6) + (10'1/7 ÷ 6'1/2)");
//        f = Formula.calculateFormulaString("6'2/3 * 1'1/2 ÷ 3");
//        f = Formula.calculateFormulaString("5'4/7 ÷ 8'5/6");
//        f = Formula.calculateFormulaString("10'2/3 + 4 + 3"+);
//        f = Formula.calculateFormulaString("1 ÷ 1 * 2'1/3 ÷ 7");
//        f = Formula.calculateFormulaString("6 * 5 + 8 + 3");
//        f = Formula.calculateFormulaString("7 * 5'1/2");
//        System.out.println(f.numerator);
//        f.print();
//        System.out.println(f.output());
    }
}
