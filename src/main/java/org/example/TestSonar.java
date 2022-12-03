package org.example;

import com.pi4j.Pi4J;

public class TestSonar {
    //bcm, GPIO_#
    private int PIN_ECHO = 17, PIN_TRIG = 22;
    private long REJECTION_START=1000,REJECTION_TIME=1000; //ns



    public int getDistance() throws Exception{ //in milimeters
        int distance=0; long start_time, end_time,rejection_start=0,rejection_time=0;
        //Start ranging- trig should be in high state for 10us to generate ultrasonic signal
        //this will generate 8 cycle sonic burst.
        // produced signal would looks like, _|-----|

        var pi4j = Pi4J.newAutoContext();

        var outputPin = pi4j.dout().create(PIN_TRIG);
        var inputPin = pi4j.din().create(PIN_ECHO);

        outputPin.low();
        busyWaitMicros(2);
        outputPin.high();
        busyWaitMicros(10);
        outputPin.low();

        //echo pin high time is propotional to the distance _|----|
        //distance calculation
        while(inputPin.isLow()){ //wait until echo get high
            busyWaitNanos(1); //wait 1ns
            rejection_start++;
            if(rejection_start==REJECTION_START) return -1; //infinity
        }
        start_time=System.nanoTime();

        while(inputPin.isHigh()){ //wait until echo get low
            busyWaitNanos(1); //wait 1ns
            rejection_time++;
            if(rejection_time==REJECTION_TIME) return -1; //infinity
        }
        end_time=System.nanoTime();

        distance=(int)((end_time-start_time)/5882.35294118); //distance in mm
        //distance=(end_time-start_time)/(200*29.1); //distance in mm
        return distance;
    }

    public static void busyWaitMicros(long micros){
        long waitUntil = System.nanoTime() + (micros * 1_000);
        while(waitUntil > System.nanoTime()){
            ;
        }
    }

    public static void busyWaitNanos(long nanos){
        long waitUntil = System.nanoTime() + nanos;
        while(waitUntil > System.nanoTime()){
            ;
        }
    }
}
