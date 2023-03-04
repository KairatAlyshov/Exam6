package kz.attractor.java;

import kz.attractor.java.Calendar.CalendarServer;


import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        try {
            new CalendarServer("localhost", 9879).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
