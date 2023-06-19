package com.course.course.first;

public class Hero {
    private int coordinate = 0;
    private Activity activity = new Walk();

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
    public void move() {
        this.coordinate = activity.move(this.coordinate);
    }
    public int getCoordinate() {
        return coordinate;
    }
}
