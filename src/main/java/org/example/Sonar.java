//https://github.com/chandimab/raspberry-pi-gpio/blob/master/PiJavaUltrasonic/PiJavaUltrasonic.java
package org.example;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalInput;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.digital.DigitalState;

import javax.swing.plaf.ComponentInputMapUIResource;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Sonar {
    Sonar() {

    }

    public int test() throws InterruptedException {
        long REJ = 0, startTime = 0, endTime = 0;
        int distance = 0;
        //setup pins
        var pi4j = Pi4J.newAutoContext();

        var outputPin = pi4j.dout().create(22);
        outputPin.config().shutdownState(DigitalState.LOW);

        var properties = new Properties();
        properties.put("id", "digital_input");
        properties.put("address", 17);
        properties.put("name", "input-pin");

        var config = DigitalInput.newConfigBuilder(pi4j).load(properties).build();
        var inputPin = pi4j.din().create(config);



        //
        outputPin.state(DigitalState.LOW);

        waitMicros(2);
        outputPin.state(DigitalState.HIGH);
        waitMicros(10);
        outputPin.state(DigitalState.LOW);
        //
        long REJECTION = 1000;
        while(inputPin.isLow()) {
            waitNanos(1);
            REJ++;
            if(REJ== REJECTION) {
                return -1;
            }
        }
        startTime = System.nanoTime();
        while(inputPin.isHigh()) {
            waitNanos(1);
            REJ++;
            if(REJ== REJECTION) {
                return -1;
            }
        }
        endTime = System.nanoTime();

        distance = (int) ((endTime-startTime)/5882.35294118);

        return distance;
    }



    public void waitMicros(long micros) {
        long waitUntil = System.nanoTime() + (micros * 1_000);
        while(waitUntil>System.nanoTime()) {
            ;
        }
    }
    public void waitNanos(long nanos) {
        long waitUntil = System.nanoTime() + nanos;
        while(waitUntil>System.nanoTime()) {
            ;
        }
    }

}
