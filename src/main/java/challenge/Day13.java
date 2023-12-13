package challenge;

import java.util.ArrayList;
import java.util.List;

public class Day13 {

    public void getResult1(List<String> input) {
        List<String> recordsForPattern = new ArrayList<>();
        int totalResult = 0;
        for(int indexLine = 0; indexLine< input.size();indexLine++){
            String line = input.get(indexLine);
            if(line.isEmpty()) {
                //end of line! work on finding the mirror and clear elements afterwards
                totalResult = getTotalResult(recordsForPattern, totalResult);
            } else if(indexLine == input.size() -1) {
                // last line
                recordsForPattern.add(line);
                totalResult = getTotalResult(recordsForPattern, totalResult);
            } else {
                recordsForPattern.add(line);
            }
        }
        System.out.println("Result is " + totalResult);
    }

    private int getTotalResult(List<String> recordsForPattern, int totalResult) {
        char[][] matrix;
        matrix = new char[recordsForPattern.size()][recordsForPattern.get(0).length()];
        for(int i=0;i<matrix.length;i++) {
            char[] characters = recordsForPattern.get(i).toCharArray();
            for(int j=0;j<matrix[0].length;j++) {
                matrix[i][j] = characters[j];
            }
        }
        int[] mirrorLine = findMirrorLine(matrix);
        if(mirrorLine[0]<0) {
            //vertical line (column)
            totalResult = totalResult + mirrorLine[1]+1;
        } else {
            // horizontal line (row)
            totalResult = totalResult + 100 * (mirrorLine[0]+1);
        }
        recordsForPattern.clear();
        return totalResult;
    }

    private int[] findMirrorLine(char[][] matrix) {
        int mirrorLine = 0;
        //see first if it is vertical line
        for(int i = mirrorLine;i<matrix[0].length - 1;i++) {
            int left = i;
            int right = i+1;
            boolean isMirrorLine = true;
            while(left >= 0 && right < matrix[0].length) {
                // iterate elements per row
                for(int row = 0; row < matrix.length; row++) {
                    if(matrix[row][left] != matrix[row][right]) {
                        //this is not the mirror line, break;
                        isMirrorLine = false;
                        break;
                    }
                }
                if(!isMirrorLine) {
                    break;
                } else {
                    left--;
                    right++;
                }
            }
            if(isMirrorLine) {
                return new int[]{-1,i};
            }
        }
        //check for horizontal line
        mirrorLine = 0;
        for(int i = mirrorLine;i<matrix.length - 1;i++) {
            int above = i;
            int below = i+1;
            boolean isMirrorLine = true;
            while(above >= 0 && below < matrix.length) {
                // iterate elements per column
                for(int column = 0; column <  matrix[0].length; column ++) {
                    if(matrix[above][column] != matrix[below][column]) {
                        //this is not the mirror line, break;
                        isMirrorLine = false;
                        break;
                    }
                }
                if(!isMirrorLine) {
                    break;
                } else {
                    above--;
                    below++;
                }
            }
            if(isMirrorLine) {
                return new int[]{i,-1};
            }
        }
        return null;
    }

    public void getResult2(List<String> input) {
        List<String> recordsForPattern = new ArrayList<>();
        int totalResult = 0;
        for(int indexLine = 0; indexLine< input.size();indexLine++){
            String line = input.get(indexLine);
            if(line.isEmpty()) {
                //end of line! work on finding the mirror and clear elements afterwards
                totalResult = getTotalResultWithSmudge(recordsForPattern, totalResult);
            } else if(indexLine == input.size() -1) {
                // last line
                recordsForPattern.add(line);
                totalResult = getTotalResultWithSmudge(recordsForPattern, totalResult);
            } else {
                recordsForPattern.add(line);
            }
        }
        System.out.println("Result is " + totalResult);
    }

    private int getTotalResultWithSmudge(List<String> recordsForPattern, int totalResult) {
        char[][] matrix;
        matrix = new char[recordsForPattern.size()][recordsForPattern.get(0).length()];
        for(int i=0;i<matrix.length;i++) {
            char[] characters = recordsForPattern.get(i).toCharArray();
            for(int j=0;j<matrix[0].length;j++) {
                matrix[i][j] = characters[j];
            }
        }
        int[] mirrorLine = findMirrorLineWithSmudge(matrix);
        if(mirrorLine[0]<0) {
            //vertical line (column)
            totalResult = totalResult + mirrorLine[1]+1;
        } else {
            // horizontal line (row)
            totalResult = totalResult + 100 * (mirrorLine[0]+1);
        }
        recordsForPattern.clear();
        return totalResult;
    }

    private int[] findMirrorLineWithSmudge(char[][] matrix) {
        int mirrorLine = 0;
        //see first if it is vertical line
        for(int i = mirrorLine;i<matrix[0].length - 1;i++) {
            int countOfErrors = 0;
            int left = i;
            int right = i+1;
            boolean isMirrorLine = true;
            while(left >= 0 && right < matrix[0].length) {
                // iterate elements per row
                for(int row = 0; row < matrix.length; row++) {
                    if(matrix[row][left] != matrix[row][right]) {
                        //this is not the mirror line, break;
                        countOfErrors++;
                        if(countOfErrors>1) {
                            isMirrorLine = false;
                            break;
                        }
                    }
                }
                if(!isMirrorLine) {
                    break;
                } else {
                    left--;
                    right++;
                }
            }
            if(isMirrorLine && countOfErrors == 1) {
                return new int[]{-1,i};
            }
        }
        //check for horizontal line
        mirrorLine = 0;
        for(int i = mirrorLine;i<matrix.length - 1;i++) {
            int countOfErrors = 0;
            int above = i;
            int below = i+1;
            boolean isMirrorLine = true;
            while(above >= 0 && below < matrix.length) {
                // iterate elements per column
                for(int column = 0; column <  matrix[0].length; column ++) {
                    if(matrix[above][column] != matrix[below][column]) {
                        countOfErrors++;
                        if(countOfErrors>1) {
                            isMirrorLine = false;
                            break;
                        }
                    }
                }
                if(!isMirrorLine) {
                    break;
                } else {
                    above--;
                    below++;
                }
            }
            if(isMirrorLine  && countOfErrors == 1) {
                return new int[]{i,-1};
            }
        }
        return null;
    }
}
