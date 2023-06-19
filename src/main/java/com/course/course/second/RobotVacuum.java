package com.course.course.second;

public class RobotVacuum {
    private final StringBuilder result;

    private boolean isOn;
    private int batteryLevel;
    public RobotVacuum(StringBuilder result) {
        isOn = false;
        batteryLevel = 100;
        this.result = result;
    }
    public StringBuilder getResult(){
        return result;
    }

    protected void turnOn() {
        if (isOn) {
            result.append("Я уже включен\n");
            return;
        }
        isOn = true;
        result.append("Я включился\n");
    }

    protected void turnOff() {
        if (!isOn) {
            result.append("Я и так выключен\n");
            return;
        }
        isOn = false;
        result.append("Пока\n");
    }

    protected void chargeBattery() {
        batteryLevel = 100;
    }

    @Counter(3)
    public void clean() {
        if(!isOn){
            result.append("Робот выключен\n");
            return;
        }
        batteryLevel -= 10;
        if (batteryLevel < 0) {
            result.append("Зарядка закончилась, не могу продолжать!\n");
            batteryLevel = 0;
            return;
        }
        result.append("Проводится чистка...\n");
    }

    @Counter(3)
    public void move(int distance) {
        if(!isOn){
            result.append("Робот выключен\n");
            return;
        }
        batteryLevel -= 5 * distance;
        if (batteryLevel <= 0) {
            result.append("Зарядка закончилась, не могу продолжать!\n");
            batteryLevel = 0;
            return;
        }
        result.append("Продвинулся ").append(distance).append(" метров...\n");
    }

    public void checkBatteryLevel() {
        result.append("Уровень зарядки: ").append(batteryLevel).append("\n");
    }

    public void getStatus() {
        result.append("Режим: ").append(isOn ? "Включен\n" : "Выключен\n");
    }

    private void detectObstacles() {
        if(!isOn){
            result.append("Робот выключен\n");
            return;
        }
        result.append("Поиск мусора...\n");
    }

    private void navigate() {
        if(!isOn){
            result.append("Робот выключен\n");
            return;
        }
        result.append("Навигация...\n");
    }

    @Counter(3)
    private void rotateBrush() {
        if(!isOn){
            result.append("Робот выключен\n");
            return;
        }
        batteryLevel -= 5;
        if (batteryLevel < 0) {
            result.append("Не двигать счетками, потому что низкий уровень заряда батареи!\n");
            return;
        }
        result.append("Убираю мусор...\n");
    }
}
