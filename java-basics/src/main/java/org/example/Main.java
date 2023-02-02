package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println(Lib.getGreeting());

        var httpLib = new HttpLib();
        System.out.println(httpLib.getExampleDotCom());
    }
}