package com.fundamentalconstant.core.ui.utils;

public class EventTimer {

    long lastQuerried = System.currentTimeMillis();

    public boolean hasTimeElapsed(long milliseconds) {
        long current = System.currentTimeMillis();
        if ((current - lastQuerried) > milliseconds) {
            lastQuerried = current;
            return true;
        }
        return false;
    }

    public boolean hasTimeElapsed() {
        return hasTimeElapsed(1000 / 30);
    }
}
