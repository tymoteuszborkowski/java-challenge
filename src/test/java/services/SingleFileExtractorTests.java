package services;

import org.example.domain.DistancedCoordinate;
import org.example.services.SingleFileCoordinatesExtractor;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.example.domain.ResultsType.CLOSEST;
import static org.example.domain.ResultsType.FARTHEST;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SingleFileExtractorTests {

    private static final DistancedCoordinate P = new DistancedCoordinate(1.0D, 1.0D, 0.0D);
    private static final int N = 5;

    @Test
    void shouldSuccessfullyProduceSortedClosestCoordinatesForSampleFile() {
        SingleFileCoordinatesExtractor singleFileExtractor = new SingleFileCoordinatesExtractor(P, N, CLOSEST);
        Stream<String> sampleFileLines = SampleFilesUtils.SAMPLE_TEST_FILE_1;

        DistancedCoordinate[] result = singleFileExtractor.findCoordinates(sampleFileLines);

        assertEquals(result.length, N);
        assertAll(
                () -> assertEquals(result[0], new DistancedCoordinate(1.0D, 1.0D, 0.0D)),
                () -> assertEquals(result[1], new DistancedCoordinate(1.0D, 1.0D, 0.0D)),
                () -> assertEquals(result[2], new DistancedCoordinate(1.1D, 1.2D, 0.22360679774997896D)),
                () -> assertEquals(result[3], new DistancedCoordinate(0.6D, 0.7D, 0.5D)),
                () -> assertEquals(result[4], new DistancedCoordinate(-2.1D, 2.2D, 3.3241540277189325D))
        );
    }

    @Test
    void shouldSuccessfullyProduceSortedFarthestCoordinatesForSampleFile() {
        SingleFileCoordinatesExtractor singleFileExtractor = new SingleFileCoordinatesExtractor(P, N, FARTHEST);
        Stream<String> sampleFileLines = SampleFilesUtils.SAMPLE_TEST_FILE_1;

        DistancedCoordinate[] result = singleFileExtractor.findCoordinates(sampleFileLines);

        assertEquals(result.length, N);
        assertAll("numbers",
                () -> assertEquals(result[0], new DistancedCoordinate(10.0D, 11.0D, 13.45362404707371D)),
                () -> assertEquals(result[1], new DistancedCoordinate(-2.1D, 2.2D, 3.3241540277189325D)),
                () -> assertEquals(result[2], new DistancedCoordinate(0.6D, 0.7D, 0.5D)),
                () -> assertEquals(result[3], new DistancedCoordinate(1.1D, 1.2D, 0.22360679774997896D)),
                () -> assertEquals(result[4], new DistancedCoordinate(1.0D, 1.0D, 0.0D))
        );
    }

}
