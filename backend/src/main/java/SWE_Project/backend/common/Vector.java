package SWE_Project.backend.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import lombok.extern.slf4j.Slf4j;

// 좌표 저장 타입
@Slf4j
public class Vector {

    public int x;
    public int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector() {

    }

    //초기 생성시 데이터 저장
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // str = ((1 1)(1 3))
    public List<Vector> extractVector(String str) {
        List<Vector> v = new ArrayList<>();
        List<String> replace = replace(str);

        for (String s : replace) {
            String[] temp = s.split(" ");
            v.add(new Vector(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])));
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
}
