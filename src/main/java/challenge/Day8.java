package challenge;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 {

    public void getResult1(List<String> input) {
        char[] instructionsArray = input.get(0).toCharArray();
        Map<String, AbstractMap.SimpleEntry<String, String>> map = new HashMap<>();
        for(int i=2;i<input.size();i++){
            String[] array = input.get(i).split("=");
            String[] keyVal = array[1].trim().substring(1,array[1].trim().length()-1).split(",");
            map.put(array[0].trim(),new AbstractMap.SimpleEntry<>(keyVal[0].trim(),keyVal[1].trim()));
        }
        String key = "AAA";
        int indexPointer = 0;
        int stepCounter =0;
        while(!key.equals("ZZZ")) {
            char instruction = instructionsArray[indexPointer];
            AbstractMap.SimpleEntry<String, String> pair = map.get(key);
            if(instruction == 'L') {
                key = pair.getKey();
            }
            else {
                key = pair.getValue();
            }
            stepCounter++;
            if(indexPointer + 1 < instructionsArray.length) {
                indexPointer++;
            } else {
                indexPointer = 0;
            }
        }
        System.out.println("Number of steps " + stepCounter);
    }

    public void getResult2(List<String> input) {
        char[] instructionsArray = input.get(0).toCharArray();
        Map<String, AbstractMap.SimpleEntry<String, String>> map = new HashMap<>();
        List<String> startingKeys = new ArrayList<>();
        for(int i=2;i<input.size();i++){
            String[] array = input.get(i).split("=");
            String[] keyVal = array[1].trim().substring(1,array[1].trim().length()-1).split(",");
            String key = array[0].trim();
            if(key.endsWith("A")) {
                startingKeys.add(key);
            }
            map.put(key,new AbstractMap.SimpleEntry<>(keyVal[0].trim(),keyVal[1].trim()));
        }
        long[] numberOfTimes = new long[startingKeys.size()];
        for(int i=0; i<startingKeys.size();i++) {
            String key = startingKeys.get(i);
            long stepCounter =0;
            int indexPointer = 0;
            while (!key.contains("Z")) {
                char instruction = instructionsArray[indexPointer];
                AbstractMap.SimpleEntry<String, String> pair = map.get(key);
                String val = "";
                if (instruction == 'L') {
                    key = pair.getKey();
                } else {
                    key = pair.getValue();
                }
                stepCounter++;
                if (indexPointer + 1 < instructionsArray.length) {
                    indexPointer++;
                } else {
                    indexPointer = 0;
                }
            }
            System.out.println("Found 1");
            numberOfTimes[i] = stepCounter;
        }
        System.out.println("number of times "+ lcm(numberOfTimes));
    }
    private static long lcm(long a, long b)
    {
        return a * (b / gcd(a, b));
    }
    private static long gcd(long a, long b)
    {
        while (b > 0)
        {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long lcm(long[] input)
    {
        long result = input[0];
        for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }
}
