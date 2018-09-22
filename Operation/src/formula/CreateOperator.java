package formula;

public class CreateOperator {
    public String operator(){
        int r = (int)(Math.random()*4);
        String [] a = new String[4];
        a[0]="+";
        a[1]="-";
        a[2]="*";
        a[3]="/";
        return a[r];
    }
}
