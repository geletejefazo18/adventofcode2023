package challenge;

import com.google.common.base.CharMatcher;

import java.util.Arrays;
import java.util.List;

public class Day4 {

    public void printResult1(List<String> input) {
        double totalPoints = 0;
        for(String line: input) {
            String[] card = line.split(":");
            String[] card2 = card[1].trim().split("\\|");
            String[] myNumbersString = card2[1].trim().split("\\s+");
            int[] myNumbers = new int[myNumbersString.length];
            for(int i = 0; i< myNumbersString.length; i++) {
                myNumbers[i] = Integer.parseInt(myNumbersString[i]);
            }
            Arrays.sort(myNumbers);
            String[] winningNumbersString = card2[0].trim().split("\\s+");
            int[] winningNumbers = new int[winningNumbersString.length];
            for(int i = 0; i< winningNumbersString.length; i++) {
                winningNumbers[i] = Integer.parseInt(winningNumbersString[i]);
            }
            double winningMatchCount = 0;
            for(int j = 0; j< winningNumbers.length; j++) {
                if(Arrays.binarySearch(myNumbers, winningNumbers[j]) >= 0) {
                    winningMatchCount++;
                }
            }
            totalPoints = winningMatchCount > 0 ? totalPoints + Math.pow(2, winningMatchCount - 1) : totalPoints;
        }
        System.out.println("Points are: " + totalPoints);
    }

    public void printResult2(List<String> input) {
        int totalNumberOfCards= 0;
        int[] numberOfcards = new int[209];
        Arrays.fill(numberOfcards, 1);
        for(String line: input) {
            String[] card = line.split(":");
            int cardNumberIndex = Integer.parseInt(CharMatcher.inRange('0', '9').retainFrom(card[0]))-1;
            String[] card2 = card[1].trim().split("\\|");
            String[] myNumbersString = card2[1].trim().split("\\s+");
            int[] myNumbers = new int[myNumbersString.length];
            for(int i = 0; i< myNumbersString.length; i++) {
                myNumbers[i] = Integer.parseInt(myNumbersString[i]);
            }
            Arrays.sort(myNumbers);
            String[] winningNumbersString = card2[0].trim().split("\\s+");
            int[] winningNumbers = new int[winningNumbersString.length];
            for(int i = 0; i< winningNumbersString.length; i++) {
                winningNumbers[i] = Integer.parseInt(winningNumbersString[i]);
            }
            int winningMatchCount = 0;
            for(int j = 0; j< winningNumbers.length; j++) {
                if(Arrays.binarySearch(myNumbers, winningNumbers[j]) >= 0) {
                    winningMatchCount++;
                }
            }
            int times = numberOfcards[cardNumberIndex];
            while(times>0) {
                for (int index = cardNumberIndex + 1; index < cardNumberIndex + 1 + winningMatchCount; index++) {
                    numberOfcards[index] = numberOfcards[index] + 1;
                }
                times--;
            }
        }
        for (int finalIndex = 0; finalIndex<numberOfcards.length;finalIndex++) {
            totalNumberOfCards = totalNumberOfCards + numberOfcards[finalIndex];
        }
        System.out.println("Total Number of cards are: " + totalNumberOfCards);
    }
}
