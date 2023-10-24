package SWE_Project.backend.addon;

import SWE_Project.backend.common.Vector;

import java.util.*;

public class AStar {
    private PriorityQueue<Vector> openList;
    private ArrayList<Vector> closedList;
    HashMap<Vector, Integer> gMaps;
    HashMap<Vector, Integer> fMaps;
    private int initialCapacity = 100; // 설정 필요
    private int distanceBetweenVectors = 1;
    private Vector size = null;
    private Vector[][] mapInit = null;

    public AStar(Vector size, Vector[][] mapInit) {
        gMaps = new HashMap<Vector, Integer>();
        fMaps = new HashMap<Vector, Integer>();
        openList = new PriorityQueue<Vector>(initialCapacity, new fCompare());
        closedList = new ArrayList<Vector>();
        this.size = size;
        this.mapInit = mapInit;
    }

    // 현재 로봇의 위치와 Target 포인트를 넣어주면 된다.
    public ArrayList<Vector> search(Vector start, Vector end, List<Vector> hazards) {

        openList.clear();
        closedList.clear();

        openList.add(start);
        gMaps.put(start, 0);

        while (!openList.isEmpty()) {
            Vector current = openList.poll();
            closedList.add(current);

            if (current.equals(end)) {
                break;
            }

            current.addNeighbors(hazards, size, mapInit);

            for (Vector neighbor : current.getNeighbors()) {

                if (closedList.contains(neighbor) || openList.contains(neighbor)) {
                    continue;

                } else if (!openList.contains(neighbor)) {
                    fMaps.put(neighbor, hscore(current, neighbor) + gscore(current, neighbor));
                    openList.add(neighbor);

                    neighbor.setParent(current);

                }
            }

            if (openList.isEmpty()) {
                System.out.println("경로 없음");
                break;
            }
        }


        return closedList;
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