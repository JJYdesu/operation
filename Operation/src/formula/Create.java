package formula;
import java.util.*;
import java.io.*;
public class Create {

        Random r = new Random();
        ArrayList<Formula> list = new ArrayList<Formula>();
        Formula e;

        //生成式子中的第一个操作数（符号只能是+）
        public Formula CreateFirstOperand(int n){
            int a = r.nextInt(n) + 1; //随机产生1-n中的一个操作数
            int h = r.nextInt(n) + 1;
            int g = r.nextInt(h) + 1;
            int b = r.nextInt(2);
            if (b==0){
                e = new Formula(Formula.Operators.ADD, a);
            }
            else {
                e = new Formula(Formula.Operators.ADD, a, g, h);
            }
            return e;
        }

        //生成式子中其他的操作数，可以为+,-,*,/
        public Formula CreateOtherOperand(int n){
            int a = r.nextInt(n) + 1; //随机产生1-n中的一个操作数
            int h = r.nextInt(n) + 1;
            int g = r.nextInt(h) + 1;

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
                Formula first,first2,second, third, fourth, i,j,k,l;
                i = new Formula();
                j = new Formula();
                l = new Formula();
                first = n.CreateFirstOperand(p);
                first2 = n.CreateFirstOperand(p);
                second = n.CreateOtherOperand(p);
                third = n.CreateOtherOperand(p);
                fourth = n.CreateOtherOperand(p);
                int OperandNumber = r.nextInt(3);

                switch (OperandNumber) {

                    //两个操作数
                    case 0:
                        i.push(first);
                        i.push(second);
                        i.print();
                        s = i.output();
                        break;

                        //三个操作数
                        case 1:
                            i.push(first);
                            i.push(second);
                            i.push(third);
                            i.print();
                            s = i.output();
                        break;

                        //四个操作数
                        case 2:
                            int a = r.nextInt(4);
                            switch (a) {
                                case 0:            //a+b+c+d
                                     i.push(first);
                                     i.push(second);
                                     i.push(third);
                                     i.push(fourth);
                                     i.print();
                                     s = i.output();
                                 break;
                                 case 1:
                                     int b = r.nextInt(2);
                                     switch (b) {
                                         case 0:              //(a+b+c)+d
                                             i.push(first);
                                             i.push(second);
                                             i.push(third);
                                             j.push(i);
                                             j.push(fourth);
                                             j.print();
                                             s = j.output();
                                         break;
                                         case 1:                //a+(b+c+d)
                                              int c = r.nextInt(3);
                                              switch (c) {
                                                  case 0: k = new Formula(Formula.Operators.ADD);j = k;break;
                                                  case 1: k = new Formula(Formula.Operators.SUB);j = k;break;
                                                  case 2: k = new Formula(Formula.Operators.MUL);j = k;break;
                                              }
                                              i.push(first);
                                              j.push(first2);
                                              j.push(second);
                                              j.push(third);
                                              i.push(j);
                                              i.print();
                                              s = i.output();
                                              break;
                                 }
                                 break;

                                     case 2:
                                         int c = r.nextInt(3);
                                         int d = r.nextInt(3);
                                         switch (d) {
                                             case 0: k = new Formula(Formula.Operators.ADD);j = k;break;
                                             case 1: k = new Formula(Formula.Operators.SUB);j = k;break;
                                             case 2: k = new Formula(Formula.Operators.MUL);j = k;break;
                                         }
                                         switch (c) {
                                             case 0:              //(a+b)+c+d
                                              i.push(first);
                                              i.push(second);
                                              j.push(i);
                                              j.push(third);
                                              j.push(fourth);
                                              j.print();
                                              s = j.output();
                                              break;
                                              case 1:              //a+(b+c)+d
                                                  i.push(first);
                                                  j.push(first2);
                                                  j.push(second);
                                                  i.push(j);
                                                  i.push(third);
                                                  i.print();
                                                  s = i.output();
                                                  break;
                                                   case 2:               //a+b+(c+d)
                                                       i.push(first);
                                                       i.push(second);
                                                       j.push(first2);
                                                       j.push(third);
                                                       i.push(j);
                                                       i.print();
                                                       s = i.output();
                                                       break;
                                 }
                                 break;
                                         case 3:       //(a+b)+(c+d)
                                             int e = r.nextInt(3);
                                             switch (e) {
                                                 case 0: k = new Formula(Formula.Operators.ADD);j = k;break;
                                                 case 1: k = new Formula(Formula.Operators.SUB);j = k;break;
                                                 case 2: k = new Formula(Formula.Operators.MUL);j = k;break;
                                             }
                                             i.push(first);
                                             i.push(second);
                                             j.push(first2);
                                             j.push(third);
                                             l.push(i);
                                             l.push(j);
                                             l.print();
                                             s = l.output();
                                             break;

                            }

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



