/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mukul.mh.bankaccount;

/**
 *
 * @author mukul
 */
public class CreditCardOld {

    private String name;
    private long number;
    private double balance;
    private double limit;

    public CreditCardOld(String name, long number, double limit) {
        this.name = name;
        this.number = number;
        this.balance = 0;
        this.limit = limit;
    }

    public void withdraw(double amount) {
        if (amount >= 0 && -(balance - amount) <= limit) {
            balance = balance - amount;
        }

    }

    public void deposit(double amount) {
        if (amount >= 0) {
            balance = balance + amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public double getLimit() {
        return limit;
    }

}
