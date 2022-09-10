package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.DistancedCoordinate;
import org.example.input.validation.ApplicationContext;
import org.example.input.validation.ApplicationContextValidator;
import org.example.services.FileResultsAggregationService;
import org.example.services.SingleFileExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        String tmpArgs = "180.2323 232.13523 20 CLOSEST 10 /home/tymek/Desktop/challenge-tests/data0.csv /home/tymek/Desktop/challenge-tests/data1.csv /home/tymek/Desktop/challenge-tests/data2.csv /home/tymek/Desktop/challenge-tests/data3.csv /home/tymek/Desktop/challenge-tests/data4.csv /home/tymek/Desktop/challenge-tests/data5.csv ";

        ApplicationContext appContext = ApplicationContextValidator.validateAndBuildAppContext(tmpArgs.split(" "));
        Comparator<DistancedCoordinate> distanceCoordinateComparator =
                appContext.getResultsType().distancedCoordinateComparator;

        DistancedCoordinate p = new DistancedCoordinate(appContext.getX(), appContext.getY(), 0.0D);
        SingleFileExtractor fileExtractor = new SingleFileExtractor(p, appContext.getN(),
                distanceCoordinateComparator, appContext.getResultsType());
        FileResultsAggregationService finalResultCalculator = new FileResultsAggregationService(appContext);


        long startTime = System.nanoTime();

        DistancedCoordinate[][] eachFileResults = appContext
                .getCsvFilePaths()
                .parallelStream()
                .map(path -> {
                    try {
                        Stream<String> lines = Files.lines(path);
                        return fileExtractor.findCoordinates(lines);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).toArray(DistancedCoordinate[][]::new);

        DistancedCoordinate[] finalResult = finalResultCalculator.aggregateResults(eachFileResults);

        long estimatedTime = System.nanoTime() - startTime;
        long estimatedTimeInMs = MILLISECONDS.convert(estimatedTime, NANOSECONDS);


        printFinalResult(estimatedTimeInMs, appContext, eachFileResults, finalResult);

    }

    private static void printFinalResult(long estimatedTimeInMs,
                                         ApplicationContext appContext,
                                         DistancedCoordinate[][] eachFileResults,
                                         DistancedCoordinate[] finalResult) {
        logger.info("------------------ PART ONE ------------------");
        logger.info("N({}) {} coordinates to P({},{}) for each file",
                appContext.getN(), appContext.getResultsType().name().toLowerCase(), appContext.getX(), appContext.getY());
        Arrays.stream(eachFileResults)
                .forEach(singleFileCoords -> {
                    logger.info("---------------- FILE ---------------- ");
                    for (int i = 0; i < singleFileCoords.length; i++) {
                        DistancedCoordinate coord = singleFileCoords[i];
                        logger.info("{}. {},{}", (i + 1), coord.getX(), coord.getY());
                    }
                    logger.info("------------------------------------------ ");
                });

        logger.info("------------------ PART TWO ------------------");
        logger.info("M({}) {} coordinates to P({},{})",
                appContext.getM(), appContext.getResultsType().name().toLowerCase(), appContext.getX(), appContext.getY());

        for (int i = 0; i < finalResult.length; i++) {
            DistancedCoordinate coord = finalResult[i];
            logger.info("{}. {},{}", (i + 1), coord.getX(), coord.getY());
        }

        logger.info("Elapsed time for processing of final result: {} ms", estimatedTimeInMs);
    }


}