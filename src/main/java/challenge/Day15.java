package challenge;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day15 {

    public void getResult1(List<String> input) {
        String[] inputInArray = input.get(0).split(",");
        long totalResult = 0;
        for(String inputString: inputInArray) {
            long currentVal = 0;
            for(char charVal: inputString.toCharArray()){
                currentVal = ((currentVal + (int)charVal)*17) % 256;
            }
            totalResult = totalResult + currentVal;
        }
        System.out.println("Result " + totalResult);
    }

    public void getResult2(List<String> input){
        String[] inputInArray = input.get(0).split(",");
        List<Map<String,Integer>> boxes = new ArrayList<>();
        for(Integer i=0;i<256;i++){
            boxes.add(new LinkedHashMap<>());
        }
        for(String inputString: inputInArray) {
            if(inputString.contains("=")) {
                //add
                String[] labelAndLens = inputString.split("=");
                int boxNumber=0;
                for(char charVal: labelAndLens[0].toCharArray()){
                    boxNumber = ((boxNumber + (int)charVal)*17) % 256;
                }
                boxes.get(boxNumber).put(labelAndLens[0],Integer.parseInt(labelAndLens[1]));
            }else {
                //substract
                String[] labelAndLens = inputString.split("-");
                int boxNumber=0;
                for(char charVal: labelAndLens[0].toCharArray()){
                    boxNumber = ((boxNumber + (int)charVal)*17) % 256;
                }
                boxes.get(boxNumber).remove(labelAndLens[0]);
            }
        }
        long totalSum = 0;
        for(int i=0;i<boxes.size();i++){
            int positionInBox=1;
        for (Map.Entry<String, Integer> entry : boxes.get(i).entrySet()) {
            totalSum = totalSum + (1+i)*positionInBox* entry.getValue();
            positionInBox++;
        }
        }
        System.out.println("Result total "+ totalSum);
    }
}
