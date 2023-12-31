package com.SWEProject.BackEnd.addOn;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// 좌표 저장 타입
@Slf4j
@Getter
@Setter
@Component
public class Vector {
    public Vector parent;
    public List<Vector> neighbors;
    @JsonValue
    public int x;
    public int y;

    public Vector() {
        neighbors = new ArrayList<Vector>();
    }

    public Vector(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public boolean equals(Vector n) {
        if (n == null) {
            return false;
        }
        return this.x == n.getX() && this.y == n.getY();
    }

    public static Vector of(int x, int y) {
        return new Vector(x, y);
    }

    public static Vector deepClone(Vector vector) {
        Vector copy = new Vector();
        copy.x = vector.x;
        copy.y = vector.y;
        copy.neighbors = vector.neighbors;
        copy.parent = vector.parent;
        return copy;
    }

    // 현재 벡터에 알아서 네이버 값을 추가하는 메서드
    public void addNeighbors(Vector size, Vector[][] mapInit) {
        int x = getX() - 1;
        int y = getY() - 1;

        for (int i = y; i < y + 3; i++) {
            for (int j = x; j < x + 3; j++) {
                if (!(j < 0 || i < 0 || j >= size.getX() || i >= size.getY() || (j == getX() && i == getY()))
                        && !(j == getX() - 1 && i == getY() - 1) &&
                        !(j == getX() - 1 && i == getY() + 1) && !(j == getX() + 1 && i == getY() + 1) &&
                        !(j == getX() + 1 && i == getY() - 1)) {
                    neighbors.add(mapInit[j][i]);
                }
            }
        }
    }
}
