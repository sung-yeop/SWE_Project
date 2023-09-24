package hello.hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public class MapController { @GetMapping("map")
@ResponseBody
public Map createMap(@RequestParam("Map" ) String map ,
                     @RequestParam("Start" ) String start ,
                     @RequestParam("Spot" ) String spot ,
                     @RequestParam("Hazard" ) String hazard){
    return new Map(map, start, spot, hazard);
}


    static class Map{
        public String InitMap;
        public String InitStart;
        public String InitSpot;
        public String InitHazard;

        public Map(String map , String start, String spot, String hazard){
            InitMap = map;
            InitStart = start;
            InitSpot = spot;
            InitHazard = hazard;
        }

        public void Move(){

        }
    }
}
