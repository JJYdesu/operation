package formula;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args){
      Formula c,b,e,f;
        ArrayList<Formula> list = new ArrayList<Formula>();
        /*a = new Formula(Formula.Operators.ADD, 3);
        b = new Formula(Formula.Operators.ADD, 4, 1, 2);
        c = new Formula(Formula.Operators.SUB, 6 );
        d = new Formula(Formula.Operators.DIV, 6, 1, 3);

        list.add(a);
        list.add(c);
        e = new Formula(Formula.Operators.ADD, list);
        if(e.isPositiveNumber()==true)
            System.out.println("正数");
        else
            System.out.println("负数");
        e.print();
         */
        Random r = new Random();
        int i=0;
        int n;
        System.out.println("输入");
        Scanner sc = new Scanner(System.in);
        n=sc.nextInt();
        while(i!=100) {
            int a= r.nextInt(n)+1;
            int d= r.nextInt(n)+1;
            b = new Formula(Formula.Operators.ADD, a);
            e = new Formula(Formula.Operators.SUB, d);
            f = new Formula();
            f.push(b);
            f.push(e);
            f.print();
            i++;
        }

    }
}
