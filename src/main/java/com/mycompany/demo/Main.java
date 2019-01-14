/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.demo;

/**
 *
 * @author chaunguyen
 */
public class Main {

    public int sum(int a, int b) {
        return a + b;
    }

    public int subtraction(int a, int b) {
        return a - b;
    }

    public int multiplication(int a, int b) {
        return a * b;
    }

    public int divison(int a, int b) throws Exception {
        if (b == 0) {
            throw new Exception("Divider can't be zero");
        }
        return a / b;
    }

    public static void main(String[] args) {
        Main m = new Main();

        System.out.println("Hello World!!!");
        int i = 0;
        while (true) {
            try {
                System.out.println("i = " + i);
                i++;
                Thread.sleep(m.sum(1000, i));
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
}
