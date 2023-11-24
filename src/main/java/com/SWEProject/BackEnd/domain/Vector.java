package com.SWEProject.BackEnd.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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


    private List<String> replace(String str) {
        List<String> replace = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(str, ")(");
        while (st.hasMoreTokens()) {
            String s = st.nextToken();
            if (s != " ") {
                replace.add(s);
            }
        }

        return replace;
    }

    // 현재 벡터에 알아서 네이버 값을 추가하는 메서드
    public void addNeighbors(Vector size, Vector[][] mapInit) {
//        if (this.neighbors.size() == 4){
//            return;
//        }
//
//        if((((x > 0 && x < size.x-1 || y > 0 && y <size.y-1))
//                && this.neighbors.size() == 2 || this.neighbors.size() == 3)){
//            return;
//        }
//
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

//        if (x - 1 > 0 && !neighbors.stream().anyMatch(vector -> vector.equals(mapInit[x - 1][y]))) {
//            neighbors.add(mapInit[x - 1][y]);
//        }
//        if (x + 1 < size.getX() - 1 && !neighbors.stream().anyMatch(vector -> vector.equals(mapInit[x + 1][y]))) {
//            neighbors.add(mapInit[x + 1][y]);
//        }
//        if (y + 1 < size.getY() - 1 && !neighbors.stream().anyMatch(vector -> vector.equals(mapInit[x][y + 1]))) {
//            neighbors.add(mapInit[x][y + 1]);
//        }
//        if (y - 1 > 0 && !neighbors.stream().anyMatch(vector -> vector.equals(mapInit[x][y - 1]))) {
//            neighbors.add(mapInit[x][y - 1]);
//        }
    }
}
