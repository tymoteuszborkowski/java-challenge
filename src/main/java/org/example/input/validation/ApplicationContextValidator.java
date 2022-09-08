package org.example.input.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.domain.CoordinateType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ApplicationContextValidator {

    private static final Logger logger = LogManager.getLogger(ApplicationContextValidator.class);

    public static ApplicationContext validateAndBuildAppContext(String[] args) {
        logger.debug("Application input arguments: {}", Arrays.toString(args));

        validateNumberOfAppArguments(args);
        double x = validateX(args);
        double y = validateY(args);
        int n = validateN(args);
        List<Path> csvFilePaths = validateCsvFilePaths(args);
        int m = validateM(args, n, csvFilePaths);
        CoordinateType type = validateType(args);

        return new ApplicationContext(x, y, n, m, type, csvFilePaths);
    }

    private static void validateNumberOfAppArguments(String[] args) {
        logger.debug("Validating number of app arguments.");
        if (args.length < 6) {
            throw new IncorrectArgumentsException("Incorrect number of passed arguments.");
        }
    }

    private static double validateX(String[] args) {
        logger.debug("Validating x.");
        try {
            return Double.parseDouble(args[0]);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentsException("Unable to parse x value as double.");
        }

    }

    private static double validateY(String[] args) {
        logger.debug("Validating y.");
        try {
            return Double.parseDouble(args[1]);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentsException("Unable to parse y value as double.");
        }

    }

    private static int validateN(String[] args) {
        logger.debug("Validating n.");
        try {
            return Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentsException("Unable to parse n value as int.");
        }
    }

    private static int validateM(String[] args, int n, List<Path> csvFilePaths) {
        logger.debug("Validating m - checking if double.");
        int m;
        try {
            m = Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            throw new IncorrectArgumentsException("Unable to parse m value as int.");
        }

        logger.debug("Validating m - checking if not exceed N * numOfFiles.");
        int maxM = csvFilePaths.size() * n;
        if (m > maxM) {
            throw new IncorrectArgumentsException("M must be equal or less than N * number of files (" + maxM + ")");
        }
        return m;
    }

    private static CoordinateType validateType(String[] args) {
        logger.debug("Validating type.");
        try {
            return CoordinateType.valueOf(args[3].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IncorrectArgumentsException(
                    "Provide correct value of \"type\" parameter. Correct values are: closest/farthest.");
        }
    }

    private static List<Path> validateCsvFilePaths(String[] args) {
        logger.debug("Validating csv file paths.");
        try {
            String[] filePaths = Arrays.copyOfRange(args, 5, args.length);
            return Arrays
                    .stream(filePaths)
                    .map(Paths::get)
                    .collect(Collectors.toList());


        } catch (NumberFormatException e) {
            throw new IncorrectArgumentsException("Unable to parse csvFilePaths value as string array.");
        }
    }


}
