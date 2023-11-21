package com.SWEProject.BackEnd.addOn;

import com.SWEProject.BackEnd.constants.Direction;
import com.SWEProject.BackEnd.domain.Map;
import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.model.AStar;
import com.SWEProject.BackEnd.sim.Sim;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddOn {

    private Sim sim;
    private List<Vector> checkSpot = new ArrayList<>(); //이미 확인 완료한 Spot 리스트 (오동작 이후 탐색했던 지점을 다시 탐색하면 안됨)
    private Vector error = Vector.of(0, 0);

    public AddOn(Vector startPoint) {
        this.sim = new Sim(startPoint);
    }

    public Vector getCurrentPosition() {
        return sim.getPosition();
    }

    // 예상 이동 경로 추출
    public List<Vector> pathFind(Map map) {
        Vector start = sim.getPosition();
        List<Vector> result = new ArrayList<>();
        AStar aStar = new AStar(map.getSize(), map.createMapInit());

        //실제로 확인해야할 Spot List
        List<Vector> checkSpotList = map.getSpotList().stream()
                .filter(vector -> !checkSpot.contains(vector)).collect(Collectors.toList());

        for (Vector end : checkSpotList) {
            ArrayList<Vector> search = aStar.search(start, end, map.getHazardList());
            for (Vector vector : search) {
                if (!end.equals(vector)) {
                    result.add(vector);
                }
            }
            start = end;
            if (end == checkSpotList.get(checkSpotList.size() - 1)) {
                result.add(end);
            }
        }
        return result;
    }

    // 로봇의 한 칸 앞이 Hazard이면 True 반환
    public boolean moveWithHazardSense(Map map) {
        if (sim.checkHazard() != null && !map.getHazardList().contains(sim.checkHazard())) {
            map.getHazardList().add(sim.checkHazard());
            return true;
        }
        return false;
    }

    public boolean moveWithColorBlobSense(Map map) {
        if (sim.checkColorblob() != null && map.getColorblobList().contains(sim.checkColorblob())) {
            map.getColorblobList().add(sim.checkColorblob());
            return true;
        }
        return false;
    }

    // 문제가 있으면 True
    public boolean moveWithError(Vector intendPosition){
        if (sim.checkPosition(intendPosition)) {
            return true;
        }
        return false;
    }

    public void move(Vector nextPosition) {
        sim.move(nextPosition);
    }


}
