package challenge;

import java.util.List;

public class Day6 {

    public void printResult1(List<String> input) {

        String[] timeString = input.get(0).split(":")[1].trim().split("\\s+");
        int[]time = new int[timeString.length];
        for(int i = 0; i< timeString.length; i++) {
            time[i] = Integer.parseInt(timeString[i]);
        }
        String[] distanceString = input.get(1).split(":")[1].trim().split("\\s+");
        int[]distance = new int[distanceString.length];
        for(int i = 0; i< distanceString.length; i++) {
            distance[i] = Integer.parseInt(distanceString[i]);
        }
        int [] counters = new int[time.length];
        for(int j=0 ; j<time.length; j++) {
            int timeNow = time[j];
            int distanceNow = distance[j];
            int count = 0;
            int timeRemaining = timeNow;
            while(timeRemaining>0) {
                int calculatedDistance = count * timeRemaining;
                if(calculatedDistance>distanceNow) {
                    counters[j] = counters[j] + 1;
                }
                count++;
                timeRemaining = timeNow - count;
            }
        }
        int mult = counters [0];
        for(int i=1;i<counters.length;i++) {
            mult =mult * counters[i];
        }
        System.out.println("Result is " + mult);
    }

    public void printResult2(List<String> input) {

        String[] timeString = input.get(0).split(":")[1].trim().split("\\s+");
        String singleTimeString = "";
        for(int i = 0; i< timeString.length; i++) {
            singleTimeString = singleTimeString + timeString[i];
        }
        long time = Long.parseLong(singleTimeString);
        String[] distanceString = input.get(1).split(":")[1].trim().split("\\s+");
        String singleDistanceString = "";
        for(int i = 0; i< distanceString.length; i++) {
            singleDistanceString = singleDistanceString + distanceString[i];
        }
        long distance = Long.parseLong(singleDistanceString);
        long param = (long)(Math.sqrt(Math.pow(-time,2)-4*distance));
        long minVal = (long)Math.ceil((time - param)/2);
        long maxVal = (long)Math.floor((time + param)/2);
        long possibilities = maxVal - minVal + 1;
        System.out.println("Possible combination " + possibilities);
    }
}
