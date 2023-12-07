package challenge;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day7 {

    private static final String HIGH_CARD = "HIGHCARD";
    private static final String ONE_PAIR = "ONEPAIR";
    private static final String TWO_PAIR = "TWOPAIR";
    private static final String THREE_OF_A_KIND = "THREEOFAKIND";
    private static final String FULL_HOUSE = "FULLHOUSE";
    private static final String FOUR_OF_A_KIND = "FOUROFAKIND";
    private static final String FIVE_OF_A_KIND = "FIVEOFAKIND";
    private static final String UNKNOWN = "UNKNOWN";


    public void getResult1(List<String> input) {
        Map<String, List<AbstractMap.SimpleEntry<String, Integer>>> mapPlays = new LinkedHashMap<>() {{
            put(HIGH_CARD, new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(ONE_PAIR,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(TWO_PAIR,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(THREE_OF_A_KIND,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(FULL_HOUSE,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(FOUR_OF_A_KIND,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(FIVE_OF_A_KIND,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(UNKNOWN,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
        }};

        Map<Character, Integer> cardValue = new HashMap<>() {{
            put('2', 1);
            put('3', 2);
            put('4', 3);
            put('5', 4);
            put('6', 5);
            put('7', 6);
            put('8', 7);
            put('9', 8);
            put('T', 9);
            put('J', 10);
            put('Q', 11);
            put('K', 12);
            put('A', 13);
        }};

        for(String entry: input) {
            String [] cardAndBid = entry.split("\\s+");
            AbstractMap.SimpleEntry<String, Integer> pair
                    = new AbstractMap.SimpleEntry<>(cardAndBid[0], Integer.parseInt(cardAndBid[1]));
            mapPlays.get(getPlay(cardAndBid[0])).add(pair);
        }
        for (Map.Entry<String, List<AbstractMap.SimpleEntry<String, Integer>>> entry : mapPlays.entrySet()) {
            sortArray(entry.getValue(),cardValue);
        }
        int rank = 1;
        int totalWin = 0;
        for (Map.Entry<String, List<AbstractMap.SimpleEntry<String, Integer>>> entry : mapPlays.entrySet()) {
            for(int i = 0; i<entry.getValue().size(); i++) {
                totalWin = totalWin + rank * entry.getValue().get(i).getValue();
                rank++;
            }
        }
        System.out.println("Max Win :" + totalWin);


    }

    private String getPlay(String hand) {
        char[] characters = hand.toCharArray();
        List<Character> characterList = new ArrayList<>();
        int[] count = new int[hand.length()];
        for(char character: characters) {
            if (!characterList.contains(character)){
                characterList.add(character);
            }
            count[characterList.indexOf(character)] = count[characterList.indexOf(character)]+1;
        }
        String play = UNKNOWN;
        switch(characterList.size()) {
            case 5:
                play = HIGH_CARD;
                break;
            case 4:
                play = ONE_PAIR;
                break;
            case 3:
                for(int counter: count) {
                    if (counter == 2) {
                        play = TWO_PAIR;
                        break;
                    }
                }
                if(play == UNKNOWN ) {
                    play = THREE_OF_A_KIND;
                }
                break;
            case 2:
                for(int counter: count) {
                    if (counter == 4) {
                        play = FOUR_OF_A_KIND;
                        break;
                    }
                }
                if(play == UNKNOWN ) {
                    play = FULL_HOUSE;
                }
                break;

            case 1:
                play = FIVE_OF_A_KIND;
                break;
        }
        return play;
    }

    private String getPlayWithJ(String hand) {
        char[] characters = hand.toCharArray();
        List<Character> characterList = new ArrayList<>();
        int[] count = new int[hand.length()];
        int jokerCount = 0;
        for(char character: characters) {
            if (!characterList.contains(character)){
                characterList.add(character);
            }
            count[characterList.indexOf(character)] = count[characterList.indexOf(character)]+1;
        }
        if(characterList.contains('J')) {
            jokerCount = count[characterList.indexOf('J')];
            int index = -1;
            int max=-1;
            for (int i=0;i<characterList.size();i++) {
                if(count[i]>max && !characterList.get(i).equals('J')) {
                    max= count[i];
                    index = i;
                }
            }
            if(index>=0) {
                count[index] = count[index] + jokerCount;
                count[characterList.indexOf('J')] = 0;
                characterList.remove(new Character('J'));
            }
        }
        //get index with more
        String play = UNKNOWN;
        switch(characterList.size()) {
            case 5:
                play = HIGH_CARD;
                break;
            case 4:
                play = ONE_PAIR;
                break;
            case 3:
                for(int counter: count) {
                    if (counter == 2) {
                        play = TWO_PAIR;
                        break;
                    }
                }
                if(play == UNKNOWN ) {
                    play = THREE_OF_A_KIND;
                }
                break;
            case 2:
                for(int counter: count) {
                    if (counter == 4) {
                        play = FOUR_OF_A_KIND;
                        break;
                    }
                }
                if(play == UNKNOWN ) {
                    play = FULL_HOUSE;
                }
                break;

            case 1:
                play = FIVE_OF_A_KIND;
                break;
        }
        return play;
    }

    private void sortArray(List<AbstractMap.SimpleEntry<String, Integer>> hands, Map<Character,Integer> map) {
        for(int i=0;i<hands.size();i++) {
            for(int j=1;j< (hands.size()-i);j++){
                AbstractMap.SimpleEntry<String, Integer> initPair = hands.get(j-1);
                AbstractMap.SimpleEntry<String, Integer> secondPair = hands.get(j);
                if(isSecondLowerThanFirst(initPair.getKey(), secondPair.getKey(), map)){
                    hands.set(j-1,secondPair);
                    hands.set(j,initPair);
                }
            }
        }
    }

    private boolean isSecondLowerThanFirst(String first, String second, Map<Character,Integer> map) {
        char[] firstChar = first.toCharArray();
        char[] secondChar = second.toCharArray();
        for(int i = 0; i<firstChar.length; i++) {
            int firstCharVal = map.get(firstChar[i]);
            int secondCharVal = map.get(secondChar[i]);
            if(secondCharVal<firstCharVal) {
                return true;
            } else if(secondCharVal>firstCharVal) {
                return false;
            }
        }
        return false;
    }

    public void getResult2(List<String> input) {
        Map<String, List<AbstractMap.SimpleEntry<String, Integer>>> mapPlays = new LinkedHashMap<>() {{
            put(HIGH_CARD, new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(ONE_PAIR,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(TWO_PAIR,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(THREE_OF_A_KIND,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(FULL_HOUSE,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(FOUR_OF_A_KIND,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(FIVE_OF_A_KIND,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
            put(UNKNOWN,  new ArrayList<AbstractMap.SimpleEntry<String, Integer>>());
        }};

        Map<Character, Integer> cardValue = new HashMap<>() {{
            put('J', 0);
            put('2', 1);
            put('3', 2);
            put('4', 3);
            put('5', 4);
            put('6', 5);
            put('7', 6);
            put('8', 7);
            put('9', 8);
            put('T', 9);
            put('Q', 10);
            put('K', 11);
            put('A', 12);
        }};

        for(String entry: input) {
            String [] cardAndBid = entry.split("\\s+");
            AbstractMap.SimpleEntry<String, Integer> pair
                    = new AbstractMap.SimpleEntry<>(cardAndBid[0], Integer.parseInt(cardAndBid[1]));
            if(!cardAndBid[0].contains("J")) {
                mapPlays.get(getPlay(cardAndBid[0])).add(pair);
            } else {
                mapPlays.get(getPlayWithJ(cardAndBid[0])).add(pair);
            }
        }
        for (Map.Entry<String, List<AbstractMap.SimpleEntry<String, Integer>>> entry : mapPlays.entrySet()) {
            sortArray(entry.getValue(),cardValue);
        }
        int rank = 1;
        int totalWin = 0;
        for (Map.Entry<String, List<AbstractMap.SimpleEntry<String, Integer>>> entry : mapPlays.entrySet()) {
            for(int i = 0; i<entry.getValue().size(); i++) {
                totalWin = totalWin + rank * entry.getValue().get(i).getValue();
                rank++;
            }
        }
        System.out.println("Max Win :" + totalWin);


    }
}
