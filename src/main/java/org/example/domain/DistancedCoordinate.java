package org.example.domain;

import java.util.Comparator;
import java.util.Objects;

public class DistancedCoordinate {

    private final double x;
    private final double y;
    private final double distance;

    public DistancedCoordinate(double x, double y, double distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    public double getDistance() {
        return distance;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistancedCoordinate that = (DistancedCoordinate) o;
        return Double.compare(that.x, x) == 0 && Double.compare(that.y, y) == 0 && Double.compare(that.distance, distance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, distance);
    }

}
