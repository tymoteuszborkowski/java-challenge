package org.example.input.validation;

import org.example.domain.ResultsType;

import java.nio.file.Path;
import java.util.List;

public class ApplicationContext {

    private final double x;
    private final double y;
    private final int N;

    private final int M;
    private final ResultsType resultsType;

    private final List<Path> csvFilePaths;

    public ApplicationContext(double x, double y, int n, int m, ResultsType resultsType, List<Path> csvFilePaths) {
        this.x = x;
        this.y = y;
        N = n;
        M = m;
        this.resultsType = resultsType;
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

    public ResultsType getResultsType() {
        return resultsType;
    }
}
