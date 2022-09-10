package org.example.domain;

import java.util.Objects;

public class IndexedDistanceCoordinate {

    private final DistancedCoordinate distancedCoordinate;
    private final int fileIdx;

    public IndexedDistanceCoordinate(DistancedCoordinate distancedCoordinate, int fileIdx) {
        this.distancedCoordinate = distancedCoordinate;
        this.fileIdx = fileIdx;
    }

    public DistancedCoordinate getDistancedCoordinate() {
        return distancedCoordinate;
    }

    public int getFileIdx() {
        return fileIdx;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexedDistanceCoordinate that = (IndexedDistanceCoordinate) o;
        return fileIdx == that.fileIdx && Objects.equals(distancedCoordinate, that.distancedCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distancedCoordinate, fileIdx);
    }

    @Override
    public String toString() {
        return "IndexedDistanceCoordinate{" +
                "distancedCoordinate=" + distancedCoordinate +
                ", idx=" + fileIdx +
                '}';
    }
}
