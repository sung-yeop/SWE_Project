package SWE_Project.backend.common;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 좌표 저장 타입
@Slf4j
@Getter
@Setter
public class Vector {

    private Vector parent;
    private ArrayList<Vector> neighbors;
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

    public Vector(Vector parent) {
        this();
        this.parent = parent;
    }

    public Vector(Vector parent, int x, int y) {
        this();
        this.parent = parent;
        this.x = x;
        this.y = y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Vector n) {
        return this.x == n.x && this.y == n.y;
    }


    ////////////////////////////
    // str = ((1 1)(1 3))
    public List<Vector> extractVector(String str) {
        List<Vector> v = new ArrayList<>();
        List<String> replace = replace(str);

        for (String s : replace) {
            String[] position = s.split(" ");
            v.add(new Vector(Integer.parseInt(position[0]), Integer.parseInt(position[1])));
        }

        return v;
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
