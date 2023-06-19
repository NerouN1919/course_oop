package com.course.course.sixth;

import java.util.Random;

public class Daemon implements Runnable {
    private final AbstractProgram abstractProgram;

    public Daemon(AbstractProgram abstractProgram) {
        this.abstractProgram = abstractProgram;
    }

    @Override
    public void run() {
        Random random = new Random();
        synchronized (abstractProgram) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            while (true) {
                ProgramState state = abstractProgram.getState();
                System.out.printf("Демон %s\n", state.name());
                while (state != ProgramState.RUNNING) {
                    try {
                        System.out.printf("Демон ждёт с состоянием %s\n", state.name());
                        abstractProgram.wait();
                        state = abstractProgram.getState();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.printf("Демон работает с состоянием %s\n", state.name());
                int number = random.nextInt();
                if (number % 10 == 0) {
                    abstractProgram.setState(ProgramState.FATAL_ERROR);
                } else if (number % 1000 < 500) {
                    abstractProgram.setState(ProgramState.STOPPING);
                }
                abstractProgram.notifyAll();
            }
        }
    }
}
