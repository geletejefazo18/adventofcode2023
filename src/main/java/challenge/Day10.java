package challenge;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

public class Day10 {

    public void getResult1(List<String> input) {
        //build matrix - while building find S indexes
        char[][] matrix = new char[140][140];
        int initialIndexRow = 0;
        int initialIndexColumn = 0;
        for(int i=0;i< matrix.length;i++) {
            char[] rowChar = input.get(i).toCharArray();
            for(int j = 0; j< matrix[0].length ; j++){
                matrix[i][j] = rowChar[j];
                if(matrix[i][j] == 'S') {
                    initialIndexRow = i;
                    initialIndexColumn = j;
                }
            }

        }
        // see where to go first step
        char right = initialIndexColumn < matrix[0].length - 1 ? matrix[initialIndexRow][initialIndexColumn+1] : 0;
        char left = initialIndexColumn > 0 ? matrix[initialIndexRow][initialIndexColumn-1] : 0;
        char top = initialIndexRow > 0 ? matrix[initialIndexRow - 1][initialIndexColumn] : 0;
        char below = initialIndexRow < matrix.length - 1 ? matrix[initialIndexRow +1][initialIndexColumn] : 0;
        char move = '.';
        int newRow = -1;
        int newColumn = -1;
        int previousRow = initialIndexRow;
        int previousColumn = initialIndexColumn;
        if (right == '-' || right == 'J' || right == '7') {
            move = right;
            newRow = previousRow;
            newColumn = previousColumn + 1;
        } else if(left == '-' || left == 'L' || left == 'F') {
            move = left;
            newRow = previousRow;
            newColumn = previousColumn + -1;
        } else if(top == '|' || top == 'F' || top == '7') {
            move = top;
            newRow = previousRow -1 ;
            newColumn = previousColumn;
        } else if(below == '|' || below == 'J' || below == 'L') {
            move = below;
            newRow = previousRow +1 ;
            newColumn = previousColumn;
        }
        int counter = 1;
        while(!(newRow==initialIndexRow && newColumn==initialIndexColumn)) {
            int[] moveTo = whereToGo(previousRow, previousColumn, newRow, newColumn,move);
            previousColumn = newColumn;
            previousRow = newRow;
            newRow = moveTo[0];
            newColumn = moveTo[1];
            move = matrix[newRow][newColumn];
            counter++;
        }
        System.out.println("Far " + counter/2);

    }

    public void getResult2(List<String> input) {
        //build matrix - while building find S indexes
        char[][] matrix = new char[140][140];
        int initialIndexRow = 0;
        int initialIndexColumn = 0;
        for(int i=0;i< matrix.length;i++) {
            char[] rowChar = input.get(i).toCharArray();
            for(int j = 0; j< matrix[0].length ; j++){
                matrix[i][j] = rowChar[j];
                if(matrix[i][j] == 'S') {
                    initialIndexRow = i;
                    initialIndexColumn = j;
                }
            }

        }
        // see where to go first step
        char right = initialIndexColumn < matrix[0].length - 1 ? matrix[initialIndexRow][initialIndexColumn+1] : 0;
        char left = initialIndexColumn > 0 ? matrix[initialIndexRow][initialIndexColumn-1] : 0;
        char top = initialIndexRow > 0 ? matrix[initialIndexRow - 1][initialIndexColumn] : 0;
        char below = initialIndexRow < matrix.length - 1 ? matrix[initialIndexRow +1][initialIndexColumn] : 0;
        char move = '.';
        int newRow = -1;
        int newColumn = -1;
        int previousRow = initialIndexRow;
        int previousColumn = initialIndexColumn;
        if (right == '-' || right == 'J' || right == '7') {
            move = right;
            newRow = previousRow;
            newColumn = previousColumn + 1;
        } else if(left == '-' || left == 'L' || left == 'F') {
            move = left;
            newRow = previousRow;
            newColumn = previousColumn + -1;
        } else if(top == '|' || top == 'F' || top == '7') {
            move = top;
            newRow = previousRow -1 ;
            newColumn = previousColumn;
        } else if(below == '|' || below == 'J' || below == 'L') {
            move = below;
            newRow = previousRow +1 ;
            newColumn = previousColumn;
        }
        Map <Coordenates, Integer> mazeWithStep = new HashMap<>();
        mazeWithStep.put(new Coordenates(newRow, newColumn), 1);
        int counter = 1;
        while(!(newRow==initialIndexRow && newColumn==initialIndexColumn)) {
            int[] moveTo = whereToGo(previousRow, previousColumn, newRow, newColumn,move);
            previousColumn = newColumn;
            previousRow = newRow;
            newRow = moveTo[0];
            newColumn = moveTo[1];
            move = matrix[newRow][newColumn];
            counter++;
            mazeWithStep.put(new Coordenates(newRow, newColumn), counter);
        }
        int countInsideLoop = 0;
        int upsAndDown = 0;
        for(int i=1;i< matrix.length-1;i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Coordenates currentRow = new Coordenates(i,j);
                Coordenates belowRow = new Coordenates(i+1,j);
                if(mazeWithStep.containsKey(currentRow) && mazeWithStep.containsKey(belowRow)) {
                    int difference = mazeWithStep.get(currentRow) - mazeWithStep.get(belowRow);
                    if (Math.abs(difference) == 1) {
                        upsAndDown = difference ==1 ? upsAndDown +1 : upsAndDown -1;
                    }

                } else if (!mazeWithStep.containsKey(currentRow) & upsAndDown !=0) {
                    countInsideLoop++;
                }
            }
        }
        System.out.println("Count insideloop "+countInsideLoop);

    }

    private int[] whereToGo(int previousRow, int previousColumn, int newRow, int newColumn, char instruction) {
        switch (instruction) {
            case('|'):
                if(previousRow< newRow) {
                    newRow++;
                } else {
                    newRow--;
                }
                break;
            case('-'):
                if(previousColumn< newColumn) {
                    newColumn++;
                } else {
                    newColumn--;
                }
                break;
            case('L'):
                if(previousRow<newRow) {
                    newColumn++;
                } else {
                    newRow--;
                }
                break;
            case('J'):
                if(previousRow<newRow) {
                    newColumn--;
                } else {
                    newRow--;
                }
                break;
            case('7'):
                if(previousColumn<newColumn) {
                    newRow++;
                } else {
                    newColumn--;
                }
                break;
            case('F'):
                if(previousColumn>newColumn) {
                    newRow++;
                } else {
                    newColumn++;
                }
                break;
        }
        return new int[]{newRow,newColumn};
    }

    public class Coordenates {
        int row;
        int column;

        public Coordenates(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordenates that = (Coordenates) o;
            return row == that.row && column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}
