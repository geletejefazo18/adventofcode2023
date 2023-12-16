package challenge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day14 {

    public void getResult1(List<String> input){
        char[][] matrix = new char[input.size()][input.get(0).length()];
        for(int i = 0;i<matrix.length;i++) {
            char [] line = input.get(i).toCharArray();
            for (int j= 0; j<matrix[0].length;j++) {
                matrix[i][j] = line[j];
            }
        }
        //move rocks to north!
        for(int column=0;column<matrix[0].length;column++) {
            for(int row = 1;row<matrix.length;row++) {
                if(matrix[row][column] == 'O') {
                    boolean canMoveNorth = true;
                    int positionToMove = row;
                    while (canMoveNorth && positionToMove > 0) {
                        if (matrix[positionToMove - 1][column] == '.') {
                            positionToMove--;
                        } else {
                            canMoveNorth = false;
                        }
                    }
                    if(positionToMove!=row) {
                        matrix[positionToMove][column] = 'O';
                        matrix[row][column] = '.';
                    }
                }
            }
        }
        int total = 0;
        for(int i = 0;i<matrix.length;i++) {
            int countOfRocks = 0;
            for (int j= 0; j<matrix[0].length;j++) {
                if(matrix[i][j] == 'O') {
                    countOfRocks++;
                }
            }
            total = total + countOfRocks*(matrix.length-i);
        }

        System.out.println("result "+ total);
    }

    public void getResult2(List<String> input) {
        char[][] matrix = new char[input.size()][input.get(0).length()];
        for(int i = 0;i<matrix.length;i++) {
            char [] line = input.get(i).toCharArray();
            for (int j= 0; j<matrix[0].length;j++) {
                matrix[i][j] = line[j];

            }
        }
        int cycle = findCycle(matrix);
        System.out.println("Found cycle "+cycle);
        int numberOfCycles = (1000000000 - cycle) % (cycle - 300);
        for(int i = 0; i< numberOfCycles; i++) {
            moveNorth(matrix);
            moveWest(matrix);
            moveSouth(matrix);
            moveEast(matrix);
        }
        int total = 0;
        for(int i = 0;i<matrix.length;i++) {
            int countOfRocks = 0;
            for (int j= 0; j<matrix[0].length;j++) {
                if(matrix[i][j] == 'O') {
                    countOfRocks++;
                }
            }
            total = total + countOfRocks*(matrix.length-i);
        }

        System.out.println("result "+ total);
    }

    private void moveNorth(char[][] matrix) {
        //move rocks to north!
        for(int column=0;column<matrix[0].length;column++) {
            for(int row = 1;row<matrix.length;row++) {
                if(matrix[row][column] == 'O') {
                    boolean canMoveNorth = true;
                    int positionToMove = row;
                    while (canMoveNorth && positionToMove > 0) {
                        if (matrix[positionToMove - 1][column] == '.') {
                            positionToMove--;
                        } else {
                            canMoveNorth = false;
                        }
                    }
                    if(positionToMove!=row) {
                        matrix[positionToMove][column] = 'O';
                        matrix[row][column] = '.';
                    }
                }
            }
        }
    }

    private void moveWest(char[][] matrix) {
        //move rocks to west!
        for(int row=0;row<matrix.length;row++) {
            for(int column = 1;column<matrix[0].length;column++) {
                if(matrix[row][column] == 'O') {
                    boolean canMoveWest = true;
                    int positionToMove = column;
                    while (canMoveWest && positionToMove > 0) {
                        if (matrix[row][positionToMove-1] == '.') {
                            positionToMove--;
                        } else {
                            canMoveWest = false;
                        }
                    }
                    if(positionToMove!=column) {
                        matrix[row][positionToMove] = 'O';
                        matrix[row][column] = '.';
                    }
                }
            }
        }
    }

    private void moveSouth(char[][] matrix) {
        //move rocks to south!
        for(int column=0;column<matrix[0].length;column++) {
            for(int row = matrix.length -2;row>=0;row--) {
                if(matrix[row][column] == 'O') {
                    boolean canMoveSouth = true;
                    int positionToMove = row;
                    while (canMoveSouth && positionToMove < matrix.length -1) {
                        if (matrix[positionToMove + 1][column] == '.') {
                            positionToMove++;
                        } else {
                            canMoveSouth = false;
                        }
                    }
                    if(positionToMove!=row) {
                        matrix[positionToMove][column] = 'O';
                        matrix[row][column] = '.';
                    }
                }
            }
        }
    }

    private void moveEast(char[][] matrix) {
        //move rocks to east!
        for(int row=0;row<matrix.length;row++) {
            for(int column = matrix[0].length -2;column>=0;column--) {
                if(matrix[row][column] == 'O') {
                    boolean canMoveEast = true;
                    int positionToMove = column;
                    while (canMoveEast && positionToMove < matrix[0].length -1) {
                        if (matrix[row][positionToMove+1] == '.') {
                            positionToMove++;
                        } else {
                            canMoveEast = false;
                        }
                    }
                    if(positionToMove!=column) {
                        matrix[row][positionToMove] = 'O';
                        matrix[row][column] = '.';
                    }
                }
            }
        }
    }

    private int findCycle(char[][] matrix) {
        char [][] originalMatrix = null;
        boolean findCycle = false;
        int counter = 0;
        while(!findCycle) {
            if(counter == 300) {
                originalMatrix = new char[matrix.length][matrix[0].length];
                for(int i = 0;i<matrix.length;i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        originalMatrix[i][j] = matrix[i][j];
                    }
                }
            }
            moveNorth(matrix);
            moveWest(matrix);
            moveSouth(matrix);
            moveEast(matrix);
            counter++;
            if(isEqual(matrix, originalMatrix)) {
                break;
            }
            System.out.println("Done cycle "+ counter);
        }
        return counter;
    }

    private boolean isEqual(char[][] matrix1,char[][] matrix2) {
        if(matrix1 == null || matrix2 == null ){
            return false;
        }
        for(int i = 0;i<matrix1.length;i++) {
            for (int j= 0; j<matrix1[0].length;j++) {
                if(matrix1[i][j] != matrix2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
