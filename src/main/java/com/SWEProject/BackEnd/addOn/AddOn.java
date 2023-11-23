package com.SWEProject.BackEnd.addOn;

import com.SWEProject.BackEnd.constants.Direction;
import com.SWEProject.BackEnd.domain.Map;
import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.model.AStar;
import com.SWEProject.BackEnd.sim.Sim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
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

    public void setCheckSpot(Vector checkSpot) {
        this.checkSpot.add(checkSpot);
    }

    // 예상 이동 경로 추출
    public List<Vector> pathFind(Map map) {
        Vector start = sim.getPosition();
        List<Vector> result = new ArrayList<>();
        AStar aStar = new AStar(map.getSize(), map.createMapInit());


        for (Vector end : map.getSpotList().stream().sorted(Comparator.comparing(Vector::getX))
                .collect(Collectors.toList())) {
            ArrayList<Vector> search = aStar.search(start, end, map.getHazardList());
            for (Vector vector : search) {
                if (!end.equals(vector)) {
                    result.add(vector);
                }
            }
            start = end;
            if (end == map.getSpotList().get(map.getSpotList().size() - 1)) {
                result.add(end);
            }
        }
        List<Vector> output = result.stream().filter(v -> v.getNeighbors().stream().count() > 4).collect(Collectors.toList());
        log.info("4개 이상 neightbor : " + output.stream().map(String::valueOf).collect(Collectors.joining(", ")) + "\n");

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

    public void directionSetting(Vector nextPosition) {
        sim.directionSetting(nextPosition);
    }

    public void move(){
        sim.move();
    }


}
