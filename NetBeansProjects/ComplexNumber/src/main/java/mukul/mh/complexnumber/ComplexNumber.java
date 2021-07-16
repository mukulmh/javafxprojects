/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mukul.mh.complexnumber;

/**
 *
 * @author mukul
 */
public class ComplexNumber {
    protected int real;
    protected int imaginary;
    
    public ComplexNumber(int r, int i){
        real = r;
        imaginary = i;
    }
    
    public ComplexNumber add(ComplexNumber b){
        ComplexNumber a = this;
        int real = a.real + b.real;
        int imaginary = a.imaginary + b.imaginary; 
        ComplexNumber c = new ComplexNumber(real,imaginary);
        return c;
    }
    
    public static void main(String args[]){
        ComplexNumber a = new ComplexNumber(-3 , -6);
        ComplexNumber b = new ComplexNumber(2, -3);
        ComplexNumber c = a.add(b);
        
        System.out.printf("%d + %di",c.real,c.imaginary);
    }
}
