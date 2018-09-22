package formula;
import java.util.*;
public class Main {
    public static void main(String[] args){
        int num;
        int i;
        CreateFormula a = new CreateFormula();
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要生成的题目个数:");
        num = sc.nextInt();
        for(i=0;i<num;i++){
            a.formula();
        }
    }
}
