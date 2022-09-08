package org.example.domain;

import java.util.Comparator;
import java.util.PriorityQueue;

public enum CoordinateType {

    CLOSEST(Comparator.comparing(DistancedCoordinate::getDistance, Comparator.reverseOrder()),
            Comparator.comparing(d -> d.getDistancedCoordinate().getDistance(), Comparator.reverseOrder())) {
        @Override
        public void heapCompareStrategy(PriorityQueue<DistancedCoordinate> heap,
                                        DistancedCoordinate distancedCoordinate) {
            if (distancedCoordinate.getDistance() < heap.peek().getDistance()) {
                heap.poll();
                heap.add(distancedCoordinate);
            }
        }

    },
    FARTHEST(Comparator.comparing(DistancedCoordinate::getDistance),
            Comparator.comparing(d -> d.getDistancedCoordinate().getDistance())) {
        @Override
        public void heapCompareStrategy(PriorityQueue<DistancedCoordinate> heap,
                                        DistancedCoordinate distancedCoordinate) {
            if (distancedCoordinate.getDistance() > heap.peek().getDistance()) {
                heap.poll();
                heap.add(distancedCoordinate);
            }
        }
    };

    abstract public void heapCompareStrategy(
            PriorityQueue<DistancedCoordinate> heap,
            DistancedCoordinate distancedCoordinate);

    public final Comparator<DistancedCoordinate> distancedCoordinateComparator;
    public final Comparator<IndexedDistanceCoordinate> indexedDistanceCoordinateComparator;

    CoordinateType(Comparator<DistancedCoordinate> distancedCoordinateComparator,
                   Comparator<IndexedDistanceCoordinate> indexedDistanceCoordinateComparator
    ) {
        this.distancedCoordinateComparator = distancedCoordinateComparator;
        this.indexedDistanceCoordinateComparator = indexedDistanceCoordinateComparator;
    }

}
