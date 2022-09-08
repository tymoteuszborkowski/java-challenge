package org.example.input.validation;

import org.example.domain.CoordinateType;

import java.nio.file.Path;
import java.util.List;

public class ApplicationContext {

    private final double x;
    private final double y;
    private final int N;

    private final int M;
    private final CoordinateType type;

    private final List<Path> csvFilePaths;

    public ApplicationContext(double x, double y, int n, int m, CoordinateType type, List<Path> csvFilePaths) {
        this.x = x;
        this.y = y;
        N = n;
        M = m;
        this.type = type;
        this.csvFilePaths = csvFilePaths;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getN() {
        return N;
    }

    public int getM() {
        return M;
    }

    public List<Path> getCsvFilePaths() {
        return csvFilePaths;
    }

    public CoordinateType getType() {
        return type;
    }
}
