package SWE_Project.backend.common;


// 좌표 저장 타입
public class Vector {

    public int x;
    public int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //초기 생성시 데이터 저장
    public Vector(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void updateVector(int x, int y){
        this.x = x;
        this.y = y;
    }
}
