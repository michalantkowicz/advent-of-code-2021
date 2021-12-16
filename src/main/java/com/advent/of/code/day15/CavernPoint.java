package com.advent.of.code.day15;

import java.util.ArrayList;
import java.util.List;

class CavernPoint {
    private Position position;
    private List<CavernPoint> adjacents = new ArrayList<>();
    private int riskLevel;
    private int distanceFromStart;
    private int hDistance;
    private int fDistance;
    CavernPoint prev;

    public CavernPoint getPrev() {
        return prev;
    }

    public void setPrev(CavernPoint prev) {
        this.prev = prev;
    }

    public int getfDistance() {
        return fDistance;
    }

    public void setfDistance(int fDistance) {
        this.fDistance = fDistance;
    }

    public int gethDistance() {
        return hDistance;
    }

    public void sethDistance(int hDistance) {
        this.hDistance = hDistance;
    }

    CavernPoint(Position position, int riskLevel, int distanceFromStart) {
        this.position = position;
        this.riskLevel = riskLevel;
        this.distanceFromStart = distanceFromStart;
        this.fDistance = distanceFromStart;
    }

    public Position getPosition() {
        return position;
    }

    public int getRiskLevel() {
        return riskLevel;
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public void setDistanceFromStart(int distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    public List<CavernPoint> getAdjacents() {
        return adjacents;
    }

    public void addAdjacent(CavernPoint adjacent) {
        this.adjacents.add(adjacent);
    }
}