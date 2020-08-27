package com.gc.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GCExample {

    public static void main(String[] args) {

        System.out.println("Starting GCExample...");

        System.out.println("args:  " + Arrays.toString(args));

        causeOOM();

        while (true) {

            try {
                
                System.out.println("Sleeping...");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    
    private static void causeOOM() {
        
        List<Object> items = new ArrayList<>(1);
        try {
            while (true) {
                items.add(new Object());
            }
        } catch (OutOfMemoryError e) {
            System.err.println(e);
        }

    }

}
