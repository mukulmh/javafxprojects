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
public class CreditCardTest {

    public CreditCardTest() {
    }

    @Test
    public void testWithdraw() {
        double EPS = .00001;
        CreditCard creditCard = new CreditCard("Mukul", 1234, 5000);
        creditCard.withdraw(1000);
        Assertions.assertEquals(-1000, creditCard.getBalance(),EPS);
        Assertions.assertEquals(5000, creditCard.getLimit(),EPS);
    }

    @Test
    public void testWithdrawNegative() {
        double EPS = .00001;
        CreditCard creditCard = new CreditCard("Mukul", 1234, 5000);
        creditCard.withdraw(-1000);
        Assertions.assertEquals(0.0, creditCard.getBalance(),EPS);
        Assertions.assertEquals(5000, creditCard.getLimit(),EPS);
    }

    @Test
    public void testWithdrawMoreThanLimit() {
        double EPS = .00001;
        CreditCard creditCard = new CreditCard("Mukul", 1234, 5000);
        creditCard.withdraw(2000);
        creditCard.withdraw(5000);
        Assertions.assertEquals(-2000, creditCard.getBalance(),EPS);
        Assertions.assertEquals(5000, creditCard.getLimit(),EPS);
    }

    @Test
    public void testDeposit() {
        double EPS = .00001;
        CreditCard creditCard = new CreditCard("Mukul", 1234, 5000);
        creditCard.deposit(1000);
        Assertions.assertEquals(1000, creditCard.getBalance(),EPS);
        Assertions.assertEquals(5000, creditCard.getLimit(),EPS);
    }

    @Test
    public void testDepositNegativeAmount() {
        double EPS = .00001;
        CreditCard creditCard = new CreditCard("Mukul", 1234, 5000);
        creditCard.deposit(-1000);
        Assertions.assertEquals(0, creditCard.getBalance(),EPS);
        Assertions.assertEquals(5000, creditCard.getLimit(),EPS);
    }

    @Test
    public void testGetBalance() {
        double EPS = .00001;
        CreditCard creditCard = new CreditCard("Mukul", 1234, 5000);
        Assertions.assertEquals(0, creditCard.getBalance(),EPS);
    }

    @Test
    public void testGetLimit() {
        double EPS = .00001;
        CreditCard creditCard = new CreditCard("Mukul", 1234, 5000);
        Assertions.assertEquals(5000, creditCard.getLimit(),EPS);
    }

}
