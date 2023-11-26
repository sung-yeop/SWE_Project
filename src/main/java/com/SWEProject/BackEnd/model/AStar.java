package com.SWEProject.BackEnd.model;

import com.SWEProject.BackEnd.domain.Vector;

import java.util.*;

public class AStar {
    private PriorityQueue<Vector> openList;
    private ArrayList<Vector> closedList;
    HashMap<Vector, Integer> gMaps;
    HashMap<Vector, Integer> fMaps;
    private int initialCapacity = 100; // 설정 필요
    private Vector size = null;
    private Vector[][] mapInit = null;

    public AStar(Vector size, Vector[][] mapInit) {
        gMaps = new HashMap<Vector, Integer>();
        fMaps = new HashMap<Vector, Integer>();
        openList = new PriorityQueue<Vector>(initialCapacity, new fCompare());
        closedList = new ArrayList<Vector>();
        this.size = size;
        this.mapInit = mapInit;
        addNeighbors(size, mapInit);
    }

    private void addNeighbors(Vector size, Vector[][] mapInit) {
        for (int i = 0; i < size.x; i++) {
            for (int j = 0; j < size.y; j++) {
                mapInit[i][j].addNeighbors(size, mapInit);
            }
        }
    }

    // 현재 로봇의 위치와 Target 포인트를 넣어주면 된다.
    public ArrayList<Vector> search(Vector start, Vector end, List<Vector> hazards) {
        openList.clear();
        closedList.clear();

        start = mapInit[start.getX()][start.getY()];
        end = mapInit[end.getX()][end.getY()];

        openList.add(start);
        gMaps.put(start, 0);

        while (!openList.isEmpty()) {
            Vector current = openList.poll();

            closedList.add(current);

            if (!fMaps.containsKey(current)) {
                fMaps.put(current, hscore(current, end) + gscore(start, current));
            }

            if (current.equals(end)) {
                break;
            }

            b:
            for (Vector neighbor : current.getNeighbors()) {
                if (contain(neighbor, hazards)) {
                    continue b;
                } else if (!openList.stream().anyMatch(vector -> vector.equals(neighbor))) {
                    fMaps.put(neighbor, hscore(neighbor, end) + gscore(current, neighbor));
                    openList.add(neighbor);

                    neighbor.setParent(current);

                    if (fMaps.get(current) > fMaps.get(neighbor)) {
                        current = neighbor;
                    }
                }
            }

            if (openList.isEmpty()) {
                System.out.println("경로 없음");
                break;
            }
        }
        return closedList;
    }

    private boolean contain(Vector neighbor, List<Vector> hazards) {
        int flag_openList = 0;
        int flag_closedList = 0;
        int flag_hazards = 0;

        for (Vector vector : openList) {
            if (vector.equals(neighbor)) {
                flag_openList = 1;
                break;
            }
        }

        for (Vector vector : closedList) {
            if (vector.equals(neighbor)) {
                flag_closedList = 1;
                break;
            }
        }

        for (Vector vector : hazards) {
            if (vector.getX() == neighbor.getX() && vector.getY() == neighbor.getY()) {
                flag_hazards = 1;
                break;
            }
        }

        if (flag_openList == 1 || flag_closedList == 1 || flag_hazards == 1) {
            return true;
        } else {
            return false;
        }
    }

    private int gscore(Vector start, Vector now) {
        int x = Math.abs(start.getX() - now.getX());
        int y = Math.abs(start.getY() - now.getY());

        int gscore = x + y;

        return gscore;
    }

    private int hscore(Vector now, Vector goal) {
        int x = Math.abs(now.getX() - goal.getX());
        int y = Math.abs(now.getY() - goal.getY());

        int hscore = x + y;
        return hscore;
    }

    class fCompare implements Comparator<Vector> {
        public int compare(Vector o1, Vector o2) {
            return fMaps.get(o1) > fMaps.get(o2) ? 1 : -1;
        }
    }
}