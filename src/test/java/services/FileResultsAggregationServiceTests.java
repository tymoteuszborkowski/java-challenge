package services;

import org.example.domain.DistancedCoordinate;
import org.example.services.FileResultsAggregationService;
import org.junit.jupiter.api.Test;

import static org.example.domain.ResultsType.CLOSEST;
import static org.example.domain.ResultsType.FARTHEST;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileResultsAggregationServiceTests {

    private static final int M = 8;
    private static final DistancedCoordinate[][] SORTED_SAMPLE_COORDINATES_ASCENDING = {
            {
                    coord(1.0D, 1.0D, 0.0D),
                    coord(1.1D, 1.2D, 0.223D),
                    coord(1.1D, 1.2D, 0.223D),
                    coord(-2.1D, 2.2D, 3.3241D)

            },
            {
                    coord(1.0D, 1.0D, 0.0D),
                    coord(0.6D, 0.6D, 0.566D),
                    coord(0.4D, 0.5D, 0.781D),
                    coord(1.3D, 0.2D, 0.854D),
                    coord(2.1D, 3.0D, 2.282D),
                    coord(10.0D, 20.0D, 21.024D)

            }
    };

    private static final DistancedCoordinate[][] SORTED_SAMPLE_COORDINATES_DESCENDING = {
            {

                    coord(-2.1D, 2.2D, 3.3241D),
                    coord(1.1D, 1.2D, 0.223D),
                    coord(1.1D, 1.2D, 0.223D),
                    coord(1.0D, 1.0D, 0.0D)
            },
            {
                    coord(10.0D, 20.0D, 21.024D),
                    coord(2.1D, 3.0D, 2.282D),
                    coord(1.3D, 0.2D, 0.854D),
                    coord(0.4D, 0.5D, 0.781D),
                    coord(0.6D, 0.6D, 0.566D),
                    coord(1.0D, 1.0D, 0.0D),
            }
    };

    @Test
    void shouldProduceSortedClosestCoordinates() {
        //given
        FileResultsAggregationService aggregationService = new FileResultsAggregationService(M, CLOSEST);

        //when
        DistancedCoordinate[] result = aggregationService.aggregateResults(SORTED_SAMPLE_COORDINATES_ASCENDING);

        //then
        assertEquals(result.length, M);
        assertAll(
                () -> assertEquals(result[0], coord(1.0D, 1.0D, 0.0D)),
                () -> assertEquals(result[1], coord(1.0D, 1.0D, 0.0D)),
                () -> assertEquals(result[2], coord(1.1D, 1.2D, 0.223D)),
                () -> assertEquals(result[3], coord(1.1D, 1.2D, 0.223D)),
                () -> assertEquals(result[4], coord(0.6D, 0.6D, 0.566D)),
                () -> assertEquals(result[5], coord(0.4D, 0.5D, 0.781D)),
                () -> assertEquals(result[6], coord(1.3D, 0.2D, 0.854D)),
                () -> assertEquals(result[7], coord(2.1D, 3.0D, 2.282D))
        );
    }

    @Test
    void shouldProduceSortedFarthestCoordinates() {
        //given
        FileResultsAggregationService aggregationService = new FileResultsAggregationService(M, FARTHEST);
        
        //when
        DistancedCoordinate[] result = aggregationService.aggregateResults(SORTED_SAMPLE_COORDINATES_DESCENDING);

        //then
        assertEquals(result.length, M);
        assertAll(
                () -> assertEquals(result[0], coord(10.0D, 20.0D, 21.024D)),
                () -> assertEquals(result[1], coord(-2.1D, 2.2D, 3.3241D)),
                () -> assertEquals(result[2], coord(2.1D, 3.0D, 2.282D)),
                () -> assertEquals(result[3], coord(1.3D, 0.2D, 0.854D)),
                () -> assertEquals(result[4], coord(0.4D, 0.5D, 0.781D)),
                () -> assertEquals(result[5], coord(0.6D, 0.6D, 0.566D)),
                () -> assertEquals(result[6], coord(1.1D, 1.2D, 0.223D)),
                () -> assertEquals(result[7], coord(1.1D, 1.2D, 0.223D))
        );
    }

    private static DistancedCoordinate coord(double x, double y, double dist) {
        return new DistancedCoordinate(x, y, dist);
    }

}
