package hello.hello.spring;

import hello.hello.spring.controller.MapController;
import  java.util.List;

public class Sim {
    private Vector2 position;


    public boolean hazardSensor(){
        // 보는 방향에 따라 다르게 구현
        return MapController.map.CheckHazard(position);
    }

    public List<Vector2> colorBlobSensor(){
        // Vector2 List를 만들어서 넘겨줘야한다
        if(MapController.map.CheckSpot(position)){

        }
        return null;
    }

    public Vector2 positioningSensor(){
        return position;
    }

}
