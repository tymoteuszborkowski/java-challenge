package services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SampleFilesUtils {

    public static final Stream<String> SAMPLE_TEST_FILE_1;

    static {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            SAMPLE_TEST_FILE_1 = Files.lines(Paths.get(classLoader.getResource("sampleFile1.csv").getPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
