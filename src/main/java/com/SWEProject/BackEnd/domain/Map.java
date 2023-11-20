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


    public Map(Vector size, Vector startPoint, List<Vector> spots, List<Vector> hazards, List<Vector> colorblobList) {
        this.size = size;
        this.startPoint = startPoint;
        this.colorblobList = colorblobList;
        this.spotList = spots;
        this.hazardList = hazards;
    }

    public Queue<Vector> createMap() {
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


    public Vector getSize() {
        return size;
    }

    public boolean CheckHazard(Vector position) {
        return hazardList.contains(position);
    }

    public boolean CheckSpot(Vector position) {
        return spotList.contains(position);
    }

    public void AddHazard(Vector position) {
        if (!hazardList.contains(position))
            hazardList.add(position);
    }

    public void AddSpot(Vector position) {
        if (!spotList.contains(position))
            spotList.add(position);
    }

    public void AddColorBlob(Vector position) {
        if (!colorblobList.contains(position)) {
            colorblobList.add(position);
        }
    }
}
