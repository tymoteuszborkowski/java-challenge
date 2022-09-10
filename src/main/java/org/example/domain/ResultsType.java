package org.example.domain;

import java.util.Comparator;
import java.util.PriorityQueue;

public enum ResultsType {

    CLOSEST(Comparator.comparing(DistancedCoordinate::getDistance, Comparator.reverseOrder()),
            Comparator.comparing(d -> d.getDistancedCoordinate().getDistance(), Comparator.reverseOrder())) {
        @Override
        public boolean shouldUpdateHeap(PriorityQueue<DistancedCoordinate> heap,
                                        DistancedCoordinate distancedCoordinate) {
            return distancedCoordinate.getDistance() < heap.peek().getDistance();
        }

    },
    FARTHEST(Comparator.comparing(DistancedCoordinate::getDistance),
            Comparator.comparing(d -> d.getDistancedCoordinate().getDistance())) {
        @Override
        public boolean shouldUpdateHeap(PriorityQueue<DistancedCoordinate> heap,
                                        DistancedCoordinate distancedCoordinate) {
            return distancedCoordinate.getDistance() > heap.peek().getDistance();
        }
    };

    abstract public boolean shouldUpdateHeap(
            PriorityQueue<DistancedCoordinate> heap,
            DistancedCoordinate distancedCoordinate);

    public final Comparator<DistancedCoordinate> distancedCoordinateComparator;
    public final Comparator<IndexedDistanceCoordinate> indexedDistanceCoordinateComparator;

    ResultsType(Comparator<DistancedCoordinate> distancedCoordinateComparator,
                Comparator<IndexedDistanceCoordinate> indexedDistanceCoordinateComparator
    ) {
        this.distancedCoordinateComparator = distancedCoordinateComparator;
        this.indexedDistanceCoordinateComparator = indexedDistanceCoordinateComparator;
    }

}
