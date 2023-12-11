package challenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Day11 {

    public void getResult1(List<String> input) {
        Map<Integer,Coordenates> galaxyWithCoordinates= new HashMap<>();
        boolean[] withGalaxyColumn = new boolean[input.get(0).length()];
        boolean[] withGalaxyRow = new boolean[input.size()];
        for(int i=0;i<input.size();i++) {
            char[] row = input.get(i).toCharArray();
            for(int j=0;j<row.length; j++) {
                if(row[j] == '#') {
                    //galaxy
                    withGalaxyColumn[j] = true;
                    withGalaxyRow[i] = true;
                }
            }
        }
        int extraRows =0;
        int extraColumns = 0;
        for(boolean boleano: withGalaxyColumn) {
            if(!boleano){
                extraColumns++;
            }
        }
        for(boolean boleano: withGalaxyRow) {
            if(!boleano){
                extraRows++;
            }
        }
        char[][] expandedMatrix = new char[withGalaxyRow.length + extraRows][withGalaxyColumn.length+extraColumns];
        int galaxyCounter = 1;
        int rowNewMatrix = 0;
        int columnNewMatrix = 0 ;
        for(int i=0;i<input.size();i++) {
            char[] row = input.get(i).toCharArray();
            columnNewMatrix = 0;
            for(int j=0;j<row.length; j++) {
                if (row[j] == '#') {
                    galaxyWithCoordinates.put(galaxyCounter,new Coordenates(rowNewMatrix, columnNewMatrix));
                    expandedMatrix[rowNewMatrix][columnNewMatrix] = '#';
                    galaxyCounter++;
                } else {
                    expandedMatrix[rowNewMatrix][columnNewMatrix] = '.';
                }
            if (!withGalaxyColumn[j]) {
                columnNewMatrix++;
                expandedMatrix[rowNewMatrix][columnNewMatrix] = '.';
            }
            columnNewMatrix++;
            }
            if(!withGalaxyRow[i]){
                rowNewMatrix++;
                Arrays.fill(expandedMatrix[rowNewMatrix],'.');
            }
            rowNewMatrix++;
        }
        int totalSum = 0;
        for(int i=1;i<galaxyCounter-1;i++) {
            int initY = galaxyWithCoordinates.get(i).getRow();
            int initX = galaxyWithCoordinates.get(i).getColumn();
            for(int j=i+1;j<=galaxyCounter-1;j++) {
                int secondY = galaxyWithCoordinates.get(j).getRow();
                int secondX = galaxyWithCoordinates.get(j).getColumn();
                totalSum = totalSum + Math.abs(initY-secondY) + Math.abs(initX-secondX);
            }
        }
        System.out.println("Total sum " + totalSum);
    }

    public void getResult2(List<String> input) {
        Map<Integer,Coordenates> galaxyWithCoordinates= new HashMap<>();
        int[] withGalaxyColumn = new int[input.get(0).length()];
        int[] withGalaxyRow = new int[input.size()];
        Arrays.fill(withGalaxyRow,1000000);
        Arrays.fill(withGalaxyColumn,1000000);
        int galaxyCounter = 1;
        for(int i=0;i<input.size();i++) {
            char[] row = input.get(i).toCharArray();
            for(int j=0;j<row.length; j++) {
                if(row[j] == '#') {
                    //galaxy
                    galaxyWithCoordinates.put(galaxyCounter,new Coordenates(i, j));
                    galaxyCounter++;
                    withGalaxyColumn[j] = 1;
                    withGalaxyRow[i] = 1;
                }
            }
        }
        int sum = 0;
        for(int i = 0;i<withGalaxyRow.length;i++) {
            sum = sum +  withGalaxyRow[i];
            withGalaxyRow[i] = sum;
        }
        sum = 0;
        for(int i = 0;i<withGalaxyColumn.length;i++) {
            sum = sum +  withGalaxyColumn[i];
            withGalaxyColumn[i] = sum;
        }
        long totalSum = 0;
        for(int i=1;i<galaxyCounter-1;i++) {
            int initY = galaxyWithCoordinates.get(i).getRow();
            int initX = galaxyWithCoordinates.get(i).getColumn();
            for(int j=i+1;j<=galaxyCounter-1;j++) {
                int secondY = galaxyWithCoordinates.get(j).getRow();
                int secondX = galaxyWithCoordinates.get(j).getColumn();
                totalSum = totalSum + Math.abs(withGalaxyRow[initY]-withGalaxyRow[secondY]) + Math.abs(withGalaxyColumn[initX]-withGalaxyColumn[secondX]);
            }
        }
        System.out.println("Total sum Result " + totalSum);
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

        public int getColumn() {
            return column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Day11.Coordenates that = (Day11.Coordenates) o;
            return row == that.row && column == that.column;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, column);
        }
    }
}
