package com.niewj.basic.multi.bruceEckel.constructorSafe;// TestStaticIDField.java

public class TestStaticIDField {
    public static void main(String[] args) {
        IDChecker.test(StaticIDField::new);
    }
}
/* Output:
35866
*/