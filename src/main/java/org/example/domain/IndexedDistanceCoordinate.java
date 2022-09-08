package org.example.domain;

import java.util.Objects;

public class IndexedDistanceCoordinate {

    private final DistancedCoordinate distancedCoordinate;
    private final int idx;

    public IndexedDistanceCoordinate(DistancedCoordinate distancedCoordinate, int idx) {
        this.distancedCoordinate = distancedCoordinate;
        this.idx = idx;
    }

    public DistancedCoordinate getDistancedCoordinate() {
        return distancedCoordinate;
    }

    public int getIdx() {
        return idx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexedDistanceCoordinate that = (IndexedDistanceCoordinate) o;
        return idx == that.idx && Objects.equals(distancedCoordinate, that.distancedCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distancedCoordinate, idx);
    }
}
