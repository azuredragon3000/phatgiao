package com.example.quizphatgiao;

public class PassFailed {
    int level;
    boolean pass;

    public PassFailed(int level, boolean pass) {
        this.level = level;
        this.pass = pass;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean getPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
