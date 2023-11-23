package com.SWEProject.BackEnd.validate;

import com.SWEProject.BackEnd.domain.Map;
import com.SWEProject.BackEnd.domain.Vector;

import java.util.List;

public class ValidateMovement {
    //1. 오동작 발생 시 맵 밖으로 나가지 않아야 함
    //2. 오동작 발생 시 Hazard로 이동하지 않아야 함
    //3. 오동작 발생 시 Hidden Hazard로도 이동하지 않아야 함
    //이전 위치로 이동해야할 경우는 true 반환
    public static boolean validateMovement(Map map, Vector afterCurrentPosition){
        if(map.getHazardList().stream().anyMatch(vector -> vector.equals(afterCurrentPosition))){
            return true;
        };
        if (afterCurrentPosition.getX() > map.getSize().getX()
                || afterCurrentPosition.getY() > map.getSize().getY()
        || afterCurrentPosition.getX() < 0 || afterCurrentPosition.getY() < 0) {
            return true;
        }
        return false;
    }
}
