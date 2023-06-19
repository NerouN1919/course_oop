package com.course.course.sixth;

import java.io.IOException;

public class Supervisor implements Runnable {
    private final AbstractProgram program;

    public Supervisor(AbstractProgram program) {
        this.program = program;
    }

    @Override
    public void run() {
        synchronized (program) {
            while (true) {
                ProgramState state = program.getState();
                System.out.println(("Супервизор ") + (state.name()));
                while (state == ProgramState.RUNNING) {
                    try {
                        System.out.println("Супервизор ждёт с состоянием " + (state.name()));
                        program.wait();
                        state = program.getState();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(("Супервизор работает с состоянием ") + (state.name()));
                if (state == ProgramState.UNKNOWN) {
                    program.setState(ProgramState.RUNNING);
                }
                if (state == ProgramState.FATAL_ERROR) {
                    return;
                }
                if (state == ProgramState.STOPPING) {
                    program.setState(ProgramState.RUNNING);
                }
                program.notifyAll();
            }
        }
    }
}
