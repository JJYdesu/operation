package formula;

public class CreateNum {
    public String integer(){    //整数
        int r = 1+(int)(Math.random()*11);
        String a = String.valueOf(r);
        return a;
    }

    public String fraction(){   //分数
        String a = integer()+"/"+integer();
        return a;
    }

}
