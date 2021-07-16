/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mukul.mh.bankaccount;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mukul
 */
public class BankAccountTest {

    public BankAccountTest() {
    }

    @Test
    public void testWithdraw() {

        BankAccount account = new BankAccount("Mukul", 1234, 1000);
        account.withdraw(100);
        Assertions.assertEquals(900, account.getBalance());
    }

    @Test
    public void testWithdrawNegative() {

        BankAccount account = new BankAccount("Mukul", 1234, 1000);
        account.withdraw(-100);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void testWithdrawMoreThanBalance() {

        BankAccount account = new BankAccount("Mukul", 1234, 1000);
        account.withdraw(1010);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void testDeposit() {

        BankAccount account = new BankAccount("Mukul", 1234, 1000);
        account.deposit(1000);
        Assertions.assertEquals(2000, account.getBalance());
    }

    @Test
    public void testDepositNegativeAmmount() {

        BankAccount account = new BankAccount("Mukul", 1234, 1000);
        account.deposit(-1000);
        Assertions.assertEquals(1000, account.getBalance());
    }

    @Test
    public void testGetBalance() {
    }

}
