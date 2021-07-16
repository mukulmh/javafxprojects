package calculator;

import java.util.Scanner;


class Calc{
    
    int a,b,c;
    double d;
    public void multiply(){
        c = a*b;
    }
    
    public int add(int a,int b){
        c = a+b;
        return c ;
    }
    
    public double div(double a, double b){
        d = a/b;
        return d;
    }
    
    public void subtr(){
        c = a-b;
    }
    
}

public class Calculator {

    public static void main(String[] args) {
        
        Calc mul = new Calc();
        
        Scanner in = new Scanner(System.in);
        String x = in.nextLine();
        String y = in.nextLine();
        
        mul.a = Integer.parseInt(x);
        mul.b = Integer.parseInt(y);
        
        mul.multiply();
        
        System.out.println("Multiplication result :"+mul.c);
        
        int c = mul.add(mul.a, mul.b);
        
        System.out.println("Addition result :"+c);
        
        double d = mul.div(mul.a, mul.b);
        
        System.out.println("Division result :"+mul.d);
        
        mul.subtr();
        
        System.out.println("Subtraction result :"+mul.c);
    }
    
}