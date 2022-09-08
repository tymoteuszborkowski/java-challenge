package org.example;

import org.example.domain.Coordinate;
import org.example.domain.CoordinateType;
import org.example.domain.DistancedCoordinate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class SingleFileExtractor {


    private final Stream<String> lines;
    private final Coordinate p;
    private final int n;

    private final Comparator<DistancedCoordinate> distanceCoordinateComparator;

    private final CoordinateType type;



    public SingleFileExtractor(Stream<String> lines,
                               Coordinate p,
                               int n,
                               Comparator<DistancedCoordinate> distanceCoordinateComparator,
                               CoordinateType type) {
        this.lines = lines;
        this.p = p;
        this.n = n;
        this.distanceCoordinateComparator = distanceCoordinateComparator;
        this.type = type;
    }

    public DistancedCoordinate[] findExtremeCoordinates() {
        PriorityQueue<DistancedCoordinate> heap = new PriorityQueue<>(n, distanceCoordinateComparator);
        lines
                .forEach(line -> {
                    String[] splitLine = line.split(",");
                    double x = Double.parseDouble(splitLine[0]);
                    double y = Double.parseDouble(splitLine[1]);
                    double distanceToP = p.distanceTo(x, y);

                    DistancedCoordinate distancedCoordinate = new DistancedCoordinate(x, y, distanceToP);


                    if (heap.size() < n) {
                        heap.add(distancedCoordinate);
                    } else {
                        type.heapCompareStrategy(heap, distancedCoordinate);
                    }
                });

        DistancedCoordinate[] resultArray = heap.toArray(new DistancedCoordinate[n]);
        Arrays.sort(resultArray, distanceCoordinateComparator.reversed());
        return resultArray;
    }

}
