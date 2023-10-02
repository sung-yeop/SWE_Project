package hello.hello.spring.controller;

import hello.hello.spring.Vector2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

public class MapController {

    public static Map map;

    public static class Map {

        private Vector2 size = new Vector2(0,0);
        private List<Vector2> hazardList;
        private Vector2 startPoint;
        private List<Vector2> spotList;


        public Map(Vector2 size, Vector2 startPoint , List<Vector2> spots , List<Vector2> hazards) {
            this.size = size;
            this.startPoint = startPoint;
            spotList = spots;
            hazardList = hazards;
        }


        public Vector2 GetSize(){
            return size;
        }

        public boolean CheckHazard(Vector2 position){
            return hazardList.contains(position);
        }
        public boolean CheckSpot(Vector2 position){
            return spotList.contains(position);
        }

        public void AddHazard(Vector2 position){
            if(!hazardList.contains(position))
                hazardList.add(position);
        }

        public void AddSpot(Vector2 position){
            if(!spotList.contains(position))
                spotList.add(position);
        }

    }
}

