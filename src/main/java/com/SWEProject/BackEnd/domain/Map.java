package com.SWEProject.BackEnd.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@AllArgsConstructor
@Getter
@Setter
public class Map {
    private Vector size;
    private List<Vector> hazardList;
    private Vector startPoint;
    private List<Vector> spotList;
    private List<Vector> colorblobList;

    public Map(Vector size, Vector startPoint, List<Vector> hazardList, List<Vector> spotList, List<Vector> colorblobList) {
        this.size = size;
        this.hazardList = hazardList;
        this.startPoint = startPoint;
        this.spotList = spotList;
        this.colorblobList = colorblobList;
    }

    private Queue<Vector> createMap() {
        Queue<Vector> result = new LinkedList<>();
        for (int i = 0; i < size.getY(); i++) {
            for (int j = 0; j < size.getX(); j++) {
                result.add(new Vector(j, i));
            }
        }
        return result;
    }

    public Vector[][] createMapInit() {
        Vector[][] result = new Vector[size.getX()][size.getY()];
        Queue<Vector> mapqueue = createMap();

        for (int i = 0; i < size.getY(); i++) {
            for (int j = 0; j < size.getX(); j++) {
                result[j][i] = mapqueue.poll();
            }
        }
        return result;
    }
}
