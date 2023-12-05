package challenge;

import java.util.ArrayList;
import java.util.List;

public class Day5 {

    public void printResult1(List<String> input) {
        // get list of seeds
        String[] seedsString = input.get(0).split(":")[1].trim().split("\\s+");
        long[] seeds = new long[seedsString.length];
        for (int i = 0; i < seedsString.length; i++) {
            seeds[i] = Long.parseLong(seedsString[i]);
        }
        List<long[][]> listOfMaps = new ArrayList<>();
        long[][] matrix = new long[3][3];
        boolean countingMatrix = false;
        int rowMap = 0;
        for (int j = 1; j < input.size(); j++) {
            if (input.get(j).matches(".*[a-z].*")) {
                //on next one create a map!
                boolean endOfMatrix = false;
                int rowSize = 0;
                while (!endOfMatrix && j + rowSize + 1 < input.size()) {
                    if (!input.get(j + rowSize + 1).isEmpty()) {
                        rowSize++;
                    } else {
                        endOfMatrix = true;
                    }
                }
                matrix = new long[rowSize][3];
                countingMatrix = true;

            } else if (input.get(j).isEmpty() && countingMatrix) {
                //end of matrix
                listOfMaps.add(matrix);
                matrix = null;
                rowMap = 0;
            } else if(countingMatrix){
                String[] mapString = input.get(j).trim().split("\\s+");
                for (int s = 0; s < mapString.length; s++) {
                    matrix[rowMap][s] = Long.parseLong(mapString[s]);
                }
                rowMap++;
            }
        }
        if(matrix!=null){
            listOfMaps.add(matrix);
        }
        //Iterate over seeds and return the min
        long min = Long.MAX_VALUE;
        for(long seed: seeds){
            long location = getLocationLatestMap(seed, listOfMaps);
            if(location<min) {
                min = location;
            }
        }
        System.out.println("Min Location " + min);
    }

    private long getLocationLatestMap(long seed, List<long[][]>listOfMaps) {
        long key = seed;
        for(long[][] map: listOfMaps) {
            long result =  -1;
            for(int row = 0;row<map.length;row++){
                if(key>=map[row][1] && map[row][1]+map[row][2]> key){
                    result= key - map[row][1] + map[row][0];
                    break;
                }
            }
            if(result >= 0) {
                key = result;
            }
        }
        return key;
    }

    public void printResult2(List<String> input) {
        // get list of seeds
        String[] seedsString = input.get(0).split(":")[1].trim().split("\\s+");
        long[] seeds = new long[seedsString.length];
        for (int i = 0; i < seedsString.length; i++) {
            seeds[i] = Long.parseLong(seedsString[i]);
        }
        List<long[][]> listOfMaps = new ArrayList<>();
        long[][] matrix = new long[3][3];
        boolean countingMatrix = false;
        int rowMap = 0;
        for (int j = 1; j < input.size(); j++) {
            if (input.get(j).matches(".*[a-z].*")) {
                //on next one create a map!
                boolean endOfMatrix = false;
                int rowSize = 0;
                while (!endOfMatrix && j + rowSize + 1 < input.size()) {
                    if (!input.get(j + rowSize + 1).isEmpty()) {
                        rowSize++;
                    } else {
                        endOfMatrix = true;
                    }
                }
                matrix = new long[rowSize][3];
                countingMatrix = true;

            } else if (input.get(j).isEmpty() && countingMatrix) {
                //end of matrix
                listOfMaps.add(matrix);
                matrix = null;
                rowMap = 0;
            } else if(countingMatrix){
                String[] mapString = input.get(j).trim().split("\\s+");
                for (int s = 0; s < mapString.length; s++) {
                    matrix[rowMap][s] = Long.parseLong(mapString[s]);
                }
                rowMap++;
            }
        }
        if(matrix!=null){
            listOfMaps.add(matrix);
        }
        //Iterate over seeds and return the min
        long min = Long.MAX_VALUE;
        for(int indexSeed=0;indexSeed<seeds.length;indexSeed=indexSeed+2) {
            for (long seed = seeds[indexSeed]; seed < seeds[indexSeed] + seeds[indexSeed + 1]; seed++) {
                long location = getLocationLatestMap(seed, listOfMaps);
                if (location < min) {
                    min = location;
                }
            }
        }
        System.out.println("Min Location with range " + min);
    }

}
