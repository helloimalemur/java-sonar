package org.example;


import com.pi4j.Pi4J;

public class Main {
    public static void main(String[] args) throws Exception {
//        var sonar = new Sonar();
        var sonar = new TestSonar();
        System.out.println("Starting..");

        while(true) {
//            int result = sonar.test();
            int result = sonar.getDistance();
            System.out.println(result);
        }





    }
}