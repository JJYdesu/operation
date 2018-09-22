package formula;
import java.util.*;
public class CreateFormula {
    public String formula(){
        CreateNum a = new CreateNum();
        CreateOperator b = new CreateOperator();
        int r = (int)(Math.random()*5);
        int d = (int)(Math.random()*2);
        String  formula = null;
        if (d == 0)
            formula = a.integer()+b.operator()+a.integer()+b.operator()+a.integer()+b.operator()+a.integer()+"=";
        else
            switch (r)
            {
                case 0:
                    formula = "("+a.integer()+b.operator()+a.integer()+")"+b.operator()+a.integer()+b.operator()+a.integer()+"=";
                case 1:
                    formula = a.integer()+b.operator()+"("+a.integer()+b.operator()+a.integer()+")"+b.operator()+a.integer()+"=";
                case 2:
                    formula = a.integer()+b.operator()+a.integer()+b.operator()+"("+a.integer()+b.operator()+a.integer()+")"+"=";
                case 3:
                    formula = "("+a.integer()+b.operator()+a.integer()+b.operator()+a.integer()+")"+b.operator()+a.integer()+"=";
                case 4:
                    formula = a.integer()+b.operator()+"("+a.integer()+b.operator()+a.integer()+b.operator()+a.integer()+")"+"=";

            }
        System.out.println(formula);
        return formula;
    }
}
