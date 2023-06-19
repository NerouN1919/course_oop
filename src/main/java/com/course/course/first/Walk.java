package com.course.course.first;

import java.util.Random;

public class Walk implements Activity {
    @Override
    public int move(int firstCoordinate) {
        Random random = new Random();
        int secondCoordinate = firstCoordinate + random.nextInt(-100, 100);
        if (firstCoordinate < secondCoordinate) {
            System.out.println("Герой прошел " + (secondCoordinate - firstCoordinate) + " метров вперёд!");
        } else if (firstCoordinate > secondCoordinate) {
            System.out.println("Герой прошел " + (firstCoordinate - secondCoordinate) + " метров назад!");
        } else {
            System.out.println("Герой стоит на месте!");
        }
        return secondCoordinate;
    }
}
