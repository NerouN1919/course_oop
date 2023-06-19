package com.course.course.sixth;

public class AbstractProgram {
    private volatile ProgramState state = ProgramState.UNKNOWN;

    public ProgramState getState() {
        return state;
    }
    public void setState(ProgramState state) {
        this.state = state;
    }
}