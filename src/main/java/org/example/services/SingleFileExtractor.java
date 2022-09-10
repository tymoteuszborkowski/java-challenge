package org.example.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.DistancedCoordinate;
import org.example.domain.ResultsType;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class SingleFileExtractor {
    private static final Logger logger = LogManager.getLogger(SingleFileExtractor.class);

    private final DistancedCoordinate p;
    private final int n;
    private final Comparator<DistancedCoordinate> distanceCoordinateComparator;
    private final ResultsType type;

    public SingleFileExtractor(DistancedCoordinate p,
                               int n,
                               Comparator<DistancedCoordinate> distanceCoordinateComparator,
                               ResultsType type) {
        this.p = p;
        this.n = n;
        this.distanceCoordinateComparator = distanceCoordinateComparator;
        this.type = type;
    }

    public DistancedCoordinate[] findCoordinates(Stream<String> lines) {
        PriorityQueue<DistancedCoordinate> heap = new PriorityQueue<>(n, distanceCoordinateComparator);
        lines.forEach(line -> performHeapUpdate(line, heap));

        DistancedCoordinate[] resultArray = heap.toArray(new DistancedCoordinate[n]);
        Arrays.sort(resultArray, distanceCoordinateComparator.reversed());

        logger.debug("File output sorted array: {} ", Arrays.toString(resultArray));
        return resultArray;
    }

    private void performHeapUpdate(String line, PriorityQueue<DistancedCoordinate> heap) {
        String[] splitLine = line.split(",");
        double x = Double.parseDouble(splitLine[0]);
        double y = Double.parseDouble(splitLine[1]);
        double distanceToP = p.distanceTo(x, y);

        DistancedCoordinate newDistanceCoord = new DistancedCoordinate(x, y, distanceToP);

        if (heap.size() < n) {
            logger.debug("Heap size less than n={}. Adding element {} on heap.", n, newDistanceCoord);
            heap.add(newDistanceCoord);
        } else {
            if (type.shouldUpdateHeap(heap, newDistanceCoord)) {
                DistancedCoordinate polledElement = heap.poll();
                heap.add(newDistanceCoord);

                logger.debug("Swapped element {} to {}.", polledElement, newDistanceCoord);
            }
        }
    }

}
