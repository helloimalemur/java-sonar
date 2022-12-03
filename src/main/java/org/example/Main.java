package org.example;


import com.pi4j.Pi4J;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var sonar = new Sonar();

        System.out.println("Starting..");

        while(true) {
            int result = sonar.test();
            System.out.println(result);
        }





    }
}