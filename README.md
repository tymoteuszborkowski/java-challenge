## Usage

1. Build using `mvn clean install` command or download prebuilt version from https://drive.google.com/file/d/1CSQ7-_joj9i7RCm6PtBSzPQTe79OkG4Q/view?usp=sharing
2. Run a program as follows:
 `java -jar java-challenge-1.0-jar-with-dependencies.jar x y N type M csvFilePath1 csvFilePath2 csvFilePath3 `

## Important!
1. Order of passed jar arguments must be preserved.
2. `M` must be equal or less than `N * number of files` 
3. `type` must have value `CLOSEST` or `FARTHEST`
4. Csv file paths must be separated by space


### Example usage with output
`java -jar java-challenge-1.0-jar-with-dependencies.jar 110.0 90.0 5 CLOSEST 15 /home/tymek/Desktop/challenge-tests/data0.csv /home/tymek/Desktop/challenge-tests/data1.csv /home/tymek/Desktop/challenge-tests/data2.csv /home/tymek/Desktop/challenge-tests/data3.csv`
 
> <br /> ------------------ PART ONE ------------------
> <br /> N(5) closest coordinates to P(110.0,90.0) for each file
> <br /> ---------------- FILE ----------------
> <br /> 1. 111.13139621296496,88.86094125649063
> <br /> 2. 109.32807067241558,91.72952595361794
> <br /> 3. 108.67138277412398,88.5212647012626
> <br /> 4. 111.74299052676069,89.02466219155637
> <br /> 5. 111.64104547571253,91.27276356730539
> <br /> ------------------------------------------
> <br /> ---------------- FILE ----------------
> <br /> 1. 110.94592112552603,89.81158243866479
> <br /> 2. 110.99472720880638,89.8161817078609
> <br /> 3. 110.45673972372131,90.98801993197247
> <br /> 4. 111.1187601552332,90.36265643260916
> <br /> 5. 109.48318099443304,88.9422710594855
> <br /> ------------------------------------------
> <br /> ---------------- FILE ----------------
> <br /> 1. 110.3883322178124,90.96216232436687
> <br /> 2. 109.08325739739973,89.50837889684487
> <br /> 3. 110.4830704401061,91.19252781616716
> <br /> 4. 111.35576123943059,90.22576117132978
> <br /> 5. 111.37186063824302,90.63214860898582
> <br /> ------------------------------------------
> <br /> ---------------- FILE ----------------
> <br /> 1. 111.02468139595904,90.11060842831459
> <br /> 2. 111.1514618447923,90.03433294775972
> <br /> 3. 110.5163641129745,88.46785603691937
> <br /> 4. 111.74910248210232,89.42750943543622
> <br /> 5. 110.23752973765637,91.83432142809868
> <br /> ------------------------------------------
> <br /> ------------------ PART TWO ------------------
> <br /> M(15) closest coordinates to P(110.0,90.0)
> <br /> 1. 110.94592112552603,89.81158243866479
> <br /> 2. 110.99472720880638,89.8161817078609
> <br /> 3. 111.02468139595904,90.11060842831459
> <br /> 4. 110.3883322178124,90.96216232436687
> <br /> 5. 109.08325739739973,89.50837889684487
> <br /> 6. 110.45673972372131,90.98801993197247
> <br /> 7. 111.1514618447923,90.03433294775972
> <br /> 8. 111.1187601552332,90.36265643260916
> <br /> 9. 109.48318099443304,88.9422710594855
> <br /> 10. 110.4830704401061,91.19252781616716
> <br /> 11. 111.35576123943059,90.22576117132978
> <br /> 12. 111.37186063824302,90.63214860898582
> <br /> 13. 111.13139621296496,88.86094125649063
> <br /> 14. 110.5163641129745,88.46785603691937
> <br /> 15. 111.74910248210232,89.42750943543622
> <br /> Elapsed time for processing of final result: 263 ms
