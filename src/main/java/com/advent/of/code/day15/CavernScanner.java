package com.advent.of.code.day15;

import java.util.PriorityQueue;

import static java.util.Comparator.comparingInt;

class CavernScanner {
    int getCheapestPathCost(CavernPoint start, CavernPoint end) {
        final PriorityQueue<CavernPoint> queue = new PriorityQueue<>(comparingInt(CavernPoint::getDistanceFromStart));
        queue.add(start);
        while (!queue.isEmpty()) {
            CavernPoint current = queue.poll();
            for (CavernPoint adjacent : current.getAdjacents()) {
                if (adjacent.getDistanceFromStart() > current.getDistanceFromStart() + adjacent.getRiskLevel()) {
                    adjacent.setDistanceFromStart(current.getDistanceFromStart() + adjacent.getRiskLevel());
                    queue.offer(adjacent);
                }
            }
        }
        return end.getDistanceFromStart();
    }
}
