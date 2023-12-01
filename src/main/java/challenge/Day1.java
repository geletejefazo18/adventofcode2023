package challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day1 {

    public void getResult(List<String> input) {
        int totalSum = 0;
        String firstDigit = null;
        String secondDigit = null;
        for(String value: input) {
            char[] arrayChar = value.toCharArray();
            for (char character: arrayChar) {
                if(Character.isDigit(character) && firstDigit == null) {
                    firstDigit = String.valueOf(character);
                }
                else if(Character.isDigit(character)) {
                    secondDigit = String.valueOf(character);
                }
            }
            if(firstDigit == null) {
                firstDigit = "0";
                secondDigit = "0";
            } else if (secondDigit == null) {
                secondDigit = firstDigit;
            }
            totalSum = totalSum + Integer.parseInt(firstDigit + secondDigit);
            firstDigit = null;
            secondDigit = null;
        }
        System.out.print("Total sum: " + totalSum);
    }

    public void getResultPart2WithLetters(List<String> input) {
        Map<String, String> mapNumbers = new HashMap<>() {{
            put("one", "1");
            put("two", "2");
            put("three", "3");
            put("four", "4");
            put("five", "5");
            put("six", "6");
            put("seven", "7");
            put("eight", "8");
            put("nine", "9");
        }};
        List<String> numbers = new ArrayList<>();
        int totalSum = 0;
        String firstDigit;
        String secondDigit;
        for(String value: input) {
            String substring = "";
            char[] arrayChar = value.toCharArray();
            for (char character: arrayChar) {
                if(Character.isDigit(character)) {
                    substring = "";
                    numbers.add(String.valueOf(character));
                } else {
                    substring = substring + character;
                    String number = getNumber(substring, mapNumbers);
                    if (!number.isEmpty()) {
                        numbers.add(number);
                        substring = String.valueOf(character);
                    }
                }
            }
            if(numbers.size() == 0) {
                firstDigit = "0";
                secondDigit = "0";
            } else {
                firstDigit = numbers.get(0);
                secondDigit = numbers.get(numbers.size() - 1);
            }
            numbers.clear();
            totalSum = totalSum + Integer.parseInt(firstDigit + secondDigit);
        }
        System.out.print("Total sum: " + totalSum);
    }

    private String getNumber(String string, Map <String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (string.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "";
    }
}
