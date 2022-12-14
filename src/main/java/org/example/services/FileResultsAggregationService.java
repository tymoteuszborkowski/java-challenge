package org.example.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.DistancedCoordinate;
import org.example.domain.IndexedDistanceCoordinate;
import org.example.domain.ResultsType;

import java.util.Arrays;
import java.util.PriorityQueue;

public class FileResultsAggregationService {

    private static final Logger logger = LogManager.getLogger(FileResultsAggregationService.class);

    private final int m;
    private final ResultsType resultsType;

    public FileResultsAggregationService(int m, ResultsType resultsType) {
        this.m = m;
        this.resultsType = resultsType;
    }

    public DistancedCoordinate[] aggregateResults(DistancedCoordinate[][] eachFileResults) {
        PriorityQueue<IndexedDistanceCoordinate> heap = prepareInitialHeap(eachFileResults);

        int[] lastTakenIndicesForEachFile = new int[eachFileResults.length];
        logger.debug("Created array to store last taken indices for each file: {}",
                Arrays.toString(lastTakenIndicesForEachFile));

        logger.debug("Creating final results ...");
        DistancedCoordinate[] finalResults = new DistancedCoordinate[m];
        int numOfFinalRecords = 0;
        while (numOfFinalRecords < m) {

            IndexedDistanceCoordinate topHeapElement = heap.poll();
            int fileIdx = topHeapElement.getFileIdx();
            int nextCoordIdxForFile = lastTakenIndicesForEachFile[fileIdx] + 1;

            logger.debug("Polled top element from heap: {}", topHeapElement);
            logger.debug("Assigned next coordinate index for file from top heap element: {}", nextCoordIdxForFile);

            if (nextCoordIdxForFile < eachFileResults[fileIdx].length) {
                lastTakenIndicesForEachFile[fileIdx] = nextCoordIdxForFile;
                IndexedDistanceCoordinate nextCoordForFile =
                        new IndexedDistanceCoordinate(eachFileResults[fileIdx][nextCoordIdxForFile], fileIdx);
                heap.add(nextCoordForFile);

                logger.debug("Assigned next coordinate index is less than number of coordinates for this file ({})." +
                        " Adding new coordinate to heap: {}",eachFileResults[fileIdx].length,  nextCoordForFile);
                logger.debug("Updated last taken indices for each file array: {}",
                        Arrays.toString(lastTakenIndicesForEachFile));
            }

            finalResults[numOfFinalRecords] = topHeapElement.getDistancedCoordinate();
            numOfFinalRecords++;

            logger.debug("Added element for final results array: {}", Arrays.toString(finalResults));
        }

        return finalResults;
    }

    private PriorityQueue<IndexedDistanceCoordinate> prepareInitialHeap(DistancedCoordinate[][] eachFileResults) {
        logger.debug("Creating heap which will include final farthest/closest elements");
        PriorityQueue<IndexedDistanceCoordinate> heap = new PriorityQueue<>(
                m, resultsType.indexedDistanceCoordinateComparator.reversed());

        logger.debug("Adding on heap first element from each file result array...");
        for (int idxOfFile = 0; idxOfFile < eachFileResults.length; idxOfFile++) {
            DistancedCoordinate singleFileResult = eachFileResults[idxOfFile][0];

            IndexedDistanceCoordinate indexedDistanceCoordinate =
                    new IndexedDistanceCoordinate(singleFileResult, idxOfFile);
            heap.add(indexedDistanceCoordinate);

            logger.debug("Added: {}", indexedDistanceCoordinate);
        }

        return heap;

    }
}
