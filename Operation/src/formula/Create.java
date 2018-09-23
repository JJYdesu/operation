package formula;
import java.util.*;
import java.io.*;
public class Create {

        Random r = new Random();
        ArrayList<Formula> list = new ArrayList<Formula>();
        Formula e;

        //生成式子中的第一个操作数（符号只能是+或-）
        public Formula CreateFirstOperand(int n){
            int a = r.nextInt(n)+1; //随机产生1-m中的一个操作数
            int g = r.nextInt(n) + 1;
            int h = r.nextInt(n) + 1;
            int b = r.nextInt(2);
            int d = r.nextInt(2);
            if (b==0){
                switch (d){
                    case 0:e = new Formula(Formula.Operators.ADD, a);break;
                    case 1:e = new Formula(Formula.Operators.SUB, a);break;
                }
            }
            else {
                switch (d){
                    case 0:e = new Formula(Formula.Operators.ADD, a, g, h);break;
                    case 1:e = new Formula(Formula.Operators.SUB, a, g, h);break;
                }
            }
            return e;
        }

        //生成式子中其他的操作数，可以为+,-,*,/
        public Formula CreateOtherOperand(int n){
            int a = r.nextInt(n)+1; //随机产生1-n中的一个操作数
            int g = r.nextInt(n)+1;
            int h = r.nextInt(n)+1;
            int b = r.nextInt(2);
            int c = r.nextInt(4);
            if(b==0){
                switch (c){
                    case 0:e = new Formula(Formula.Operators.ADD, a);break;
                    case 1:e = new Formula(Formula.Operators.SUB, a);break;
                    case 2:e = new Formula(Formula.Operators.MUL, a);break;
                    case 3:e = new Formula(Formula.Operators.DIV, a);break;
                }
            }
            else{
                switch (c){
                    case 0:e = new Formula(Formula.Operators.ADD, a, g, h);break;
                    case 1:e = new Formula(Formula.Operators.SUB, a, g, h);break;
                    case 2:e = new Formula(Formula.Operators.MUL, a, g, h);break;
                    case 3:e = new Formula(Formula.Operators.DIV, a, g, h);break;
                }
            }
            return e;
        }

        //随机生成多项式
        public  String CreateFormula(int p){

                String s = new String();
                Create n = new Create();
                Formula first, second, third, fourth, i;
                first = n.CreateFirstOperand(p);
                second = n.CreateOtherOperand(p);
                third = n.CreateOtherOperand(p);
                fourth = n.CreateOtherOperand(p);
                int OperandNumber = r.nextInt(3);

                switch (OperandNumber) {
                    case 0:
                        n.list.add(first);
                        n.list.add(second);
                        i = new Formula(Formula.Operators.ADD, n.list);

                             i.print();
                             s = i.symbol + "*(" + i.integer + "'" + i.numerator + "/" + i.denominator + ")";
                             break;
                    case 1:
                        n.list.add(first);
                        n.list.add(second);
                        n.list.add(third);
                        i = new Formula(Formula.Operators.ADD, n.list);

                            i.print();
                            s = i.symbol + "*(" + i.integer + "'" + i.numerator + "/" + i.denominator + ")";
                            break;
                    case 2:
                        n.list.add(first);
                        n.list.add(second);
                        n.list.add(third);
                        n.list.add(fourth);
                        i = new Formula(Formula.Operators.ADD, n.list);

                            i.print();
                            s = i.symbol + "*(" + i.integer + "'" + i.numerator + "/" + i.denominator + ")";
                            break;
                }
            return s;
           }



    public static void main(String[] args) {
        Create n = new Create();
        int i, a, r,m;
        String[] t = new String[10001];
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入数字的范围:");
        a = sc.nextInt();

        System.out.println("请输入要生成的题目数量:");
        i = sc.nextInt();
        for (m = 1; m <= i; m++) {
            System.out.print(m + "、");
            t[m] = n.CreateFormula(a);
        }

        System.out.println("显示答案请输入1");
        r = sc.nextInt();
        if (r == 1) {
            for (m = 1; m <= i; m++) {
                System.out.print(m + "、");
                System.out.println(t[m]);
            }

        }
    }


}



