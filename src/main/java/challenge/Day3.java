package challenge;

import java.util.ArrayList;
import java.util.List;

public class Day3 {

    public void printResult1(List<String> input) {
        char[][] matrix = new char[140][140];
        for (int i = 0; i < input.size(); i++) {
            char[] chars = input.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                matrix[i][j] = chars[j];
            }
        }
        int totalSum = 0;
        for (int row = 0; row < matrix.length; row++) {
            String number = "";
            int firstDigitIndex = -1;
            for (int col = 0; col < matrix[row].length; col++) {
                if(Character.isDigit(matrix[row][col])) {
                    number = number + matrix[row][col];
                    if(firstDigitIndex == -1) {
                        firstDigitIndex = col;
                    }
                    if(col == matrix[row].length - 1 && !number.isEmpty()) {
                        //end of line, check if number needs to be used
                        if(shouldCountAsSymbolIsAdjacent(row, firstDigitIndex, col, matrix)){
                            totalSum = totalSum + Integer.parseInt(number);
                        }
                    }
                } else if(!number.isEmpty()) {
                    //Check if number need to be used
                    if(shouldCountAsSymbolIsAdjacent(row, firstDigitIndex, col-1, matrix)) {
                        totalSum = totalSum + Integer.parseInt(number);
                    }
                    firstDigitIndex = -1;
                    number = "";
                }
            }
        }
        System.out.println("Total sum of adjacent " + totalSum);
    }

    private boolean shouldCountAsSymbolIsAdjacent(int row, int columnFirstIndex, int columnLastIndex, char[][] matrix) {
        //calculate limits above, left, right, and down
        int upperLimit = row == 0 ? row : row - 1;
        int leftLimit = columnFirstIndex == 0 ? columnFirstIndex : columnFirstIndex - 1;
        int rightLimit = columnLastIndex + 1 == matrix[0].length ? columnLastIndex : columnLastIndex + 1;
        int lowerLimit = row + 1 == matrix.length ? row : row + 1;
        if (leftLimit < columnFirstIndex && isSymbol(matrix[row][leftLimit])) {
            return true;
        }
        else if(rightLimit > columnLastIndex && isSymbol(matrix[row][rightLimit])) {
            return true;
        }
        for (int initRow = upperLimit; initRow <= lowerLimit; initRow++) {
            for (int initCol = leftLimit; initCol <= rightLimit; initCol++) {
                if (initRow == row) {
                    break;
                }
               else if(isSymbol(matrix[initRow][initCol])) {
                   return true;
                }
            }
        }
        return false;
    }

    public void printResult2(List<String> input) {
        char[][] matrix = new char[140][140];
        for (int i = 0; i < input.size(); i++) {
            char[] chars = input.get(i).toCharArray();
            for (int j = 0; j < chars.length; j++) {
                matrix[i][j] = chars[j];
            }
        }
        int totalSum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                //finding *
                if (matrix[row][col] == '*') {
                    //found one
                    if(row ==80) {
                        System.out.println("Found");
                    }
                    List<Integer> numbers = getPairOfNumbersToMultiply(row,col,matrix);
                    if(numbers.size() == 2) {
                        totalSum = totalSum + numbers.get(0) * numbers.get(1);
                    } else if(numbers.size()>2) {
                        System.out.println("ERROR");
                    }
                }
            }
        }
        System.out.println("Gear ratio number is " +totalSum);
    }
    private List<Integer> getPairOfNumbersToMultiply(int astRow, int astCol, char[][] matrix) {
        List<Integer> integerList = new ArrayList<>();
        //calculate limits above, left, right, and down
        int upperLimit = astRow == 0 ? astRow : astRow - 1;
        int leftLimit = astCol == 0 ? astCol : astCol - 1;
        int rightLimit = astCol + 1 == matrix[0].length ? astCol : astCol + 1;
        int lowerLimit = astRow + 1 == matrix.length ? astRow : astRow + 1;
        if (leftLimit < astCol && Character.isDigit(matrix[astRow][leftLimit])) {
            //There is a number
            integerList.add(getNumberToMultiply(astRow, leftLimit, matrix));
        }
        if(rightLimit > astCol && Character.isDigit(matrix[astRow][rightLimit])) {
            //There is a number
            integerList.add(getNumberToMultiply(astRow, rightLimit, matrix));
        }
        for (int initRow = upperLimit; initRow <= lowerLimit; initRow++) {
            for (int initCol = leftLimit; initCol <= rightLimit; initCol++) {
                if (initRow == astRow) {
                    break;
                }
                else if(Character.isDigit(matrix[initRow][initCol])) {
                    integerList.add(getNumberToMultiply(initRow, initCol, matrix));
                    //check if there are two more chars( we are on left corner) if so there must be a non digit in between otherwise it is same number
                    boolean leftCorner = initCol + 2 == rightLimit ? true : false;
                    boolean middle = initCol + 1 == rightLimit ? true: false;
                    if(leftCorner) {
                        boolean leftAndNonDigitInBetween =  Character.isDigit(matrix[initRow][initCol+1])         ;
                        if(leftAndNonDigitInBetween) {
                            break;
                        }
                    } else if (middle) {
                        break;
                    }
                }
            }
        }
    return integerList;
    }
    private boolean isSymbol(char character) {
        return !Character.isDigit(character) && character != '.';
    }

    private int getNumberToMultiply(int row, int initColumnDigitFound, char[][] matrix) {
       boolean foundInitDigit = initColumnDigitFound == 0 ? true : false;
       while (!foundInitDigit && initColumnDigitFound >0 ) {
           initColumnDigitFound--;
           if(initColumnDigitFound < 0) {
               foundInitDigit = true;
               initColumnDigitFound++;
           }
           else if(!Character.isDigit(matrix[row][initColumnDigitFound])) {
               initColumnDigitFound++;
               foundInitDigit = true;
           }
       }
       String number = "";
       for(int i = initColumnDigitFound; i< matrix[row].length; i++) {
           if(Character.isDigit(matrix[row][i])) {
               number = number + matrix[row][i];
           } else {
               break;
           }
       }
       return Integer.parseInt(number);
    }
}
