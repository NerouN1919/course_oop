package com.course.course.first;

import java.util.Random;

public class Ride implements Activity {
    @Override
    public int move(int firstCoordinate) {
        Random random = new Random();
        int secondCoordinate = firstCoordinate + random.nextInt(-100, 100);
        if(firstCoordinate < secondCoordinate){
            System.out.println("Герой проехал на лошади " + (secondCoordinate-firstCoordinate) + " метров вперёд!");
        } else if(firstCoordinate > secondCoordinate) {
            System.out.println("Герой проехал на лошади " + (firstCoordinate-secondCoordinate) + " метров назад!");
        } else {
            System.out.println("Герой лошадь с героем стоит на месте!");
        }
        return secondCoordinate;
    }
}
