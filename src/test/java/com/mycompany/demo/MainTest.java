/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demo;

import com.mycompany.demo.Main;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author chaunguyen
 */
public class MainTest {
    private static Main m;

    public MainTest() {
    }

    @BeforeClass
    public static void initMain() {
        m = new Main();
    }

    @Test
    public void testSum() {
        int result = m.sum(1, 2);
        assertEquals(3, result);
    }
    
    @Test
    public void testSubtraction() {
        int result = m.subtraction(9, 6);
        assertEquals(3, result);
    }
    
    @Test
    public void testMultiplication() {
        int result = m.multiplication(3, 1);
        assertEquals(3, result);
    }
    
    @Test
    public void testDivison() {
        try {
            int result = m.divison(3, 1);
            assertEquals(3, result);
        } catch (Exception ex) {
            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
