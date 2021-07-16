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
public class BankAccount {

    private String name;
    private long number;
    protected double balance;

    public BankAccount(String name, long number, double balance) {
        this.name = name;
        this.number = number;
        this.balance = balance;
    }

    public void withdraw(double ammount) {
        if (ammount >= 0 && ammount <= balance) {
            balance = balance - ammount;
        }

    }

    public void deposit(double ammount) {

        if (ammount >= 0) {
            balance = balance + ammount;
        }
    }

    public double getBalance() {
        return balance;
    }
}
