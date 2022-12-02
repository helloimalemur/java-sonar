//https://github.com/chandimab/raspberry-pi-gpio/blob/master/PiJavaUltrasonic/PiJavaUltrasonic.java
package org.example;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalState;

import java.util.concurrent.TimeUnit;

public class Sonar {
//    Context pi4j = Pi4J.newAutoContext();
//    private DigitalInput pin17 = pi4j.din();
//    private DigitalOutput pin22 = pi4j.dout();
    Sonar() {

    }

    public void test() throws InterruptedException {
        var pi4j = Pi4J.newAutoContext();
        var outputPin = pi4j.dout().create(22);
        var inputPin = pi4j.din().create(17);
        outputPin.state(DigitalState.HIGH);
        Thread.sleep(10);
        outputPin.state(DigitalState.LOW);


//        var duration = outputPin.pulse(0, TimeUnit.MICROSECONDS,DigitalState.HIGH);
        var duration = outputPin.pulse(0, TimeUnit.MICROSECONDS,DigitalState.HIGH);
        System.out.println(duration);

    }

//    public float getSonar() throws InterruptedException {
//        long pingTime;
//        float distance = 0.0f;
//        pin22.state(DigitalState.HIGH);
//        Thread.sleep(10);
//        pin22.state(DigitalState.LOW);
//
//        return distance;
//    }

//    public int pulseIn() {
//        int pulseTime;
//
//        return pulseTime;
//    }

}
