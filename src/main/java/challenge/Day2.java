package challenge;

import com.google.common.base.CharMatcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {

    public void printResult(List<String> input) {
        Map<String, String> mapNumbers = new HashMap<>() {{
            put("red", "12");
            put("green", "13");
            put("blue", "14");
        }};
        int totalGameId = 0;
        for(String line: input) {
            String[] gameAndCubes = line.split("[:,;]");
            String gameId = CharMatcher.inRange('0', '9').retainFrom(gameAndCubes[0]);
            boolean addGameId = true;
            for (int i = 1; i < gameAndCubes.length; i++) {
                String cubeNumber = CharMatcher.inRange('0', '9').retainFrom(gameAndCubes[i]);
                String color = gameAndCubes[i].substring(gameAndCubes[i].lastIndexOf(' ') + 1);
                if (Integer.parseInt(cubeNumber) > Integer.parseInt(mapNumbers.get(color)) ){
                    addGameId = false;
                }
            }
            if (addGameId) {
                totalGameId = totalGameId + Integer.parseInt(gameId);
            }
        }
        System.out.println("Total possible gameIds sum " + totalGameId);

    }

    public void printResultSecondPart(List<String> input) {

        int totalSum = 0;
        for(String line: input) {
            int totalLineMult = 1;
            Map<String, Integer> mapNumbers = new HashMap<>() {{
                put("red", 1);
                put("green", 1);
                put("blue", 1);
            }};
            String[] gameAndCubes = line.split("[:,;]");
            for (int i = 1; i < gameAndCubes.length; i++) {
                String cubeNumber = CharMatcher.inRange('0', '9').retainFrom(gameAndCubes[i]);
                String color = gameAndCubes[i].substring(gameAndCubes[i].lastIndexOf(' ') + 1);
                if (Integer.parseInt(cubeNumber) > mapNumbers.get(color)){
                    mapNumbers.put(color, Integer.parseInt(cubeNumber));
                }
            }
            for (Map.Entry<String, Integer> entry : mapNumbers.entrySet()) {
                totalLineMult = totalLineMult * entry.getValue();
            }
            totalSum = totalSum + totalLineMult;

        }
        System.out.println("Total possible sum " + totalSum);

    }
}
