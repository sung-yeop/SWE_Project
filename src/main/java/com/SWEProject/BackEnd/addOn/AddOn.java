package com.SWEProject.BackEnd.addOn;

import com.SWEProject.BackEnd.constants.Direction;
import com.SWEProject.BackEnd.domain.Vector;
import com.SWEProject.BackEnd.model.AStar;
import com.SWEProject.BackEnd.sim.Sim;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Getter
@Slf4j
public class AddOn {
    private Sim sim;
    private List<Vector> checkSpot = new ArrayList<>(); //이미 확인 완료한 Spot 리스트 (오동작 이후 탐색했던 지점을 다시 탐색하면 안됨)
    private final static int INPUTPOINT = 0;
    private List<Vector> checkHazards = new ArrayList<>();
    private List<Vector> checkColorblobs = new ArrayList<>();

    public AddOn(Vector startPoint, List<Vector> preHazards, List<Vector> preColors) {
        this.sim = new Sim(startPoint, preHazards, preColors);
    }

    public Direction getDirection() {
        return sim.getDirection();
    }

    public Vector getCurrentPosition() {
        return sim.getPosition();
    }

    public void setCheckSpot(Vector checkSpot) {
        this.checkSpot.add(checkSpot);
    }

    // 예상 이동 경로 추출
//    public List<Vector> pathFind(Map map) {
    public List<Vector> pathFind(Vector size, Vector[][] mapInit, List<Vector> spots) {
        Vector start = sim.getPosition();
        List<Vector> result = new ArrayList<>();
        AStar aStar = new AStar(size, mapInit);

        for (Vector end : spots.stream().sorted(Comparator.comparing(Vector::getX))
                .collect(Collectors.toList())) {
            ArrayList<Vector> search = aStar.search(start, end, checkHazards);
            Vector resultVector = search.get(search.size() - 1);
            List<Vector> temp = new ArrayList<>();

            while (!resultVector.getParent().equals(start)) {
                temp.add(INPUTPOINT, resultVector.getParent());
                resultVector = resultVector.getParent();
            }
            temp.add(INPUTPOINT, resultVector.getParent());
            result.addAll(temp);
            start = end;
        }

        result.add(spots.stream().sorted(Comparator.comparing(Vector::getX))
                .collect(Collectors.toList()).get(spots.size() - 1));

        return result;
    }

    // 로봇의 한 칸 앞이 Hazard이면 True 반환
    public boolean moveWithHazardSense() {
        if (sim.checkHazard() != null && !checkHazards.contains(sim.checkHazard())) {
            checkHazards.add(sim.checkHazard());
            return true;
        }
        return false;
    }

    public boolean moveWithColorBlobSense() {
        if (sim.checkColorblob().size() != 0 && !checkColorblobs.contains(sim.checkColorblob())) {
            for (Vector vector : sim.checkColorblob()) {
                checkColorblobs.add(vector);
            }
            return true;
        }
        return false;
    }

    // 문제가 있으면 True
    public boolean moveWithError(Vector intendPosition) {
        if (sim.checkPosition(intendPosition)) {
            return true;
        }
        return false;
    }

    public void directionSetting(Vector nextPosition) {
        sim.directionSetting(nextPosition);
    }

    public void move() {
        sim.move();
    }

    public void setPosition(Vector beforeMovePosition) {
        sim.setPosition(beforeMovePosition);
    }

    public void addHiddenHazard(Vector vector) {
        sim.addHiddenHazard(vector);
    }

    public void addHiddenColor(Vector vector) {
        sim.addHiddenColor(vector);
    }
}
