/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mukul.mh.complexnumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author mukul
 */
public class ComplexNumberTest {
    
    public ComplexNumberTest() {
    }

    @Test
    public void testAdd() {
        ComplexNumber a = new ComplexNumber(3, -6);
        ComplexNumber b = new ComplexNumber(-6, 4);
        ComplexNumber c = a.add(b);
        
        Assertions.assertEquals(-3, c.real);
        Assertions.assertEquals(-2, c.imaginary);
    }
    
}
