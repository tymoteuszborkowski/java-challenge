package org.example;

import org.example.domain.DistancedCoordinate;
import org.example.domain.IndexedDistanceCoordinate;
import org.example.input.validation.ApplicationContext;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class FinalResultCalculator {

    private final int numOfFinalCoordinatesToPrint;
    private final ApplicationContext appContext;

    public FinalResultCalculator(int numOfFinalCoordinatesToPrint,
                                 ApplicationContext appContext) {
        this.numOfFinalCoordinatesToPrint = numOfFinalCoordinatesToPrint;
        this.appContext = appContext;
    }

    public DistancedCoordinate[] calculate(DistancedCoordinate[][] eachFileResults) {
        return minHeapMethod(eachFileResults);
    }


    private DistancedCoordinate[] minHeapMethod(DistancedCoordinate[][] distancedCoordinates) {
        PriorityQueue<IndexedDistanceCoordinate> minHeap = new PriorityQueue<>(
                appContext.getM(),
                appContext.getType().indexedDistanceCoordinateComparator.reversed());

        int numOfProcessingFiles = distancedCoordinates.length;
        int[] minIndicesForEachFile = new int[numOfProcessingFiles];
        Arrays.fill(minIndicesForEachFile, 0);

        int numOfFinalRecords = 0;

        for (int idxOfFile = 0; idxOfFile < numOfProcessingFiles; idxOfFile++) {
            DistancedCoordinate singleFileResult = distancedCoordinates[idxOfFile][0];

            IndexedDistanceCoordinate indexedDistanceCoordinate =
                    new IndexedDistanceCoordinate(singleFileResult, idxOfFile);
            minHeap.add(indexedDistanceCoordinate);
        }

        DistancedCoordinate[] finalResults = new DistancedCoordinate[appContext.getM()];
        while (numOfFinalRecords < appContext.getM()) {
            IndexedDistanceCoordinate coord = minHeap.poll();

            int newMinIdx = minIndicesForEachFile[coord.getIdx()] + 1;

            if (newMinIdx < distancedCoordinates[coord.getIdx()].length) {
                minIndicesForEachFile[coord.getIdx()] = newMinIdx;
                minHeap.add(new IndexedDistanceCoordinate(
                        distancedCoordinates[coord.getIdx()][newMinIdx], coord.getIdx()));
            }

            finalResults[numOfFinalRecords] = coord.getDistancedCoordinate();
            numOfFinalRecords++;
        }

        return finalResults;
    }


    private DistancedCoordinate[] oneBigArrayMethod(Stream<DistancedCoordinate[]> eachFileResults) {
        DistancedCoordinate[] fullResults = eachFileResults
                .flatMap(Arrays::stream)
                .toArray(DistancedCoordinate[]::new);
        DistancedCoordinate[] finalArray = new DistancedCoordinate[numOfFinalCoordinatesToPrint];

        Arrays.sort(fullResults, appContext.getType().distancedCoordinateComparator.reversed());
        System.arraycopy(fullResults,
                0,
                finalArray,
                0,
                numOfFinalCoordinatesToPrint);

        return finalArray;
    }


}
