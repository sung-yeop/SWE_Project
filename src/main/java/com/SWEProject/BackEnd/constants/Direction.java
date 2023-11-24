package com.SWEProject.BackEnd.constants;

import java.util.Arrays;

public enum Direction {
    Up("Up", "Right"),
    Down("Down", "Left"),
    Left("Left", "Up"),
    Right("Right", "Down");

    private String now;
    private String after;

    Direction(String now, String after) {
        this.now = now;
        this.after = after;
    }

    public static Direction getAfterDirectionWithNow(Direction now) {
        return Arrays.stream(Direction.values()).filter(dir -> dir.now.equals(now.after))
                .findFirst().get();
    }
}
