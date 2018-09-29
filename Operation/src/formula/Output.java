package formula;
import java.io.*;
import java.util.Scanner;

public class Output {
    public static void main(String[] args)throws IOException{
        Create n = new Create();
        Formula b;
        int i, a, r,m;
        String[] t = new String[20000];
        Scanner sc = new Scanner(System.in);
        PrintStream questionout = new PrintStream("exercise.txt");
        PrintStream answerout = new PrintStream("answer.txt");

        System.out.println("请输入数值范围:");
        a = sc.nextInt();
        questionout.print("数字范围:" + a + "\t");
        System.out.println("请输入要生成的题目数量:");
        i = sc.nextInt();
        questionout.println("题目数量:" + i);


        for(m=1; m<=i; m++){
            t[m] = n.CreateFormula(a);
            questionout.println(m + "、" + t[m] + "=");
            b = Formula.calculateFormulaString(t[m]);
            answerout.println(m + "、" + b.output());
        }
        System.out.println("成功生成题目与答案");

    }
}
