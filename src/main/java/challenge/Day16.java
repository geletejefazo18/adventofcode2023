package challenge;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Day16 {

    public void getResult1(List<String> input) {
        char[][] maze = new char[input.size()][input.get(0).length()];
        char[][] mazeMirrors = new char[input.size()][input.get(0).length()];
        for(int i=0;i<input.size();i++) {
            char[] line = input.get(i).toCharArray();
            for(int j=0;j<input.get(0).length();j++){
                maze[i][j] = line[j];
            }
        }
        Stack<Beam> stackOfBeams = new Stack<>();
        Beam initBeam = new Beam(0,0,'>');
        stackOfBeams.add(initBeam);
        while(!stackOfBeams.isEmpty()) {
            Beam beam = stackOfBeams.pop();
            energyzeBeam(maze,mazeMirrors, beam, stackOfBeams);
            System.out.println("Size:" + stackOfBeams.size());
        }
        int energyzed = 0;
        for(int i=0;i<input.size();i++) {
            for(int j=0;j<input.get(0).length();j++){
                if(maze[i][j] == '<' || maze[i][j] == '>'||maze[i][j] == 'u'||maze[i][j] == '^') {
                    energyzed++;
                }
            }
        }
        for(int i=0;i<input.size();i++) {
            for(int j=0;j<input.get(0).length();j++){
                if(mazeMirrors[i][j] == '#') {
                    energyzed++;
                }
            }
        }
        System.out.println("Total energyzed " + energyzed);
    }

    public void getResult2(List<String> input) {
        char[][] maze = new char[input.size()][input.get(0).length()];
        for(int i=0;i<input.size();i++) {
            char[] line = input.get(i).toCharArray();
            for(int j=0;j<input.get(0).length();j++){
                maze[i][j] = line[j];
            }
        }
        int maxEnergyzed = 0;
        //start first row
        for(int column=0;column<maze[0].length;column++) {
            char[][] mazeMirrors = new char[input.size()][input.get(0).length()];
            char[][] mazeCopy = Arrays.stream(maze).map(char[]::clone).toArray(char[][]::new);
            Stack<Beam> stackOfBeams = new Stack<>();
            Beam initBeam = new Beam(0,column,'u');
            stackOfBeams.add(initBeam);
            while(!stackOfBeams.isEmpty()) {
                Beam beam = stackOfBeams.pop();
                energyzeBeam(mazeCopy,mazeMirrors, beam, stackOfBeams);
            }
            int energyzed = 0;
            for(int i=0;i<input.size();i++) {
                for(int j=0;j<input.get(0).length();j++){
                    if(mazeCopy[i][j] == '<' || mazeCopy[i][j] == '>'||mazeCopy[i][j] == 'u'||mazeCopy[i][j] == '^') {
                        energyzed++;
                    }
                }
            }
            for(int i=0;i<input.size();i++) {
                for(int j=0;j<input.get(0).length();j++){
                    if(mazeMirrors[i][j] == '#') {
                        energyzed++;
                    }
                }
            }
            maxEnergyzed = energyzed>maxEnergyzed ? energyzed : maxEnergyzed;
        }
        //first column
        for(int row=0;row<maze.length;row++) {
            char[][] mazeMirrors = new char[input.size()][input.get(0).length()];
            char[][] mazeCopy = Arrays.stream(maze).map(char[]::clone).toArray(char[][]::new);
            Stack<Beam> stackOfBeams = new Stack<>();
            Beam initBeam = new Beam(row,0,'>');
            stackOfBeams.add(initBeam);
            while(!stackOfBeams.isEmpty()) {
                Beam beam = stackOfBeams.pop();
                energyzeBeam(mazeCopy,mazeMirrors, beam, stackOfBeams);
            }
            int energyzed = 0;
            for(int i=0;i<input.size();i++) {
                for(int j=0;j<input.get(0).length();j++){
                    if(mazeCopy[i][j] == '<' || mazeCopy[i][j] == '>'||mazeCopy[i][j] == 'u'||mazeCopy[i][j] == '^') {
                        energyzed++;
                    }
                }
            }
            for(int i=0;i<input.size();i++) {
                for(int j=0;j<input.get(0).length();j++){
                    if(mazeMirrors[i][j] == '#') {
                        energyzed++;
                    }
                }
            }
            maxEnergyzed = energyzed>maxEnergyzed ? energyzed : maxEnergyzed;
        }
        //last row
        for(int column=0;column<maze[0].length;column++) {
            char[][] mazeMirrors = new char[input.size()][input.get(0).length()];
            char[][] mazeCopy = Arrays.stream(maze).map(char[]::clone).toArray(char[][]::new);
            Stack<Beam> stackOfBeams = new Stack<>();
            Beam initBeam = new Beam(maze.length-1,column,'^');
            stackOfBeams.add(initBeam);
            while(!stackOfBeams.isEmpty()) {
                Beam beam = stackOfBeams.pop();
                energyzeBeam(mazeCopy,mazeMirrors, beam, stackOfBeams);
            }
            int energyzed = 0;
            for(int i=0;i<input.size();i++) {
                for(int j=0;j<input.get(0).length();j++){
                    if(mazeCopy[i][j] == '<' || mazeCopy[i][j] == '>'||mazeCopy[i][j] == 'u'||mazeCopy[i][j] == '^') {
                        energyzed++;
                    }
                }
            }
            for(int i=0;i<input.size();i++) {
                for(int j=0;j<input.get(0).length();j++){
                    if(mazeMirrors[i][j] == '#') {
                        energyzed++;
                    }
                }
            }
            maxEnergyzed = energyzed>maxEnergyzed ? energyzed : maxEnergyzed;
        }
        for(int row=0;row<maze.length;row++) {
            char[][] mazeMirrors = new char[input.size()][input.get(0).length()];
            char[][] mazeCopy = Arrays.stream(maze).map(char[]::clone).toArray(char[][]::new);
            Stack<Beam> stackOfBeams = new Stack<>();
            Beam initBeam = new Beam(row,maze[0].length-1,'<');
            stackOfBeams.add(initBeam);
            while(!stackOfBeams.isEmpty()) {
                Beam beam = stackOfBeams.pop();
                energyzeBeam(mazeCopy,mazeMirrors, beam, stackOfBeams);
            }
            int energyzed = 0;
            for(int i=0;i<input.size();i++) {
                for(int j=0;j<input.get(0).length();j++){
                    if(mazeCopy[i][j] == '<' || mazeCopy[i][j] == '>'||mazeCopy[i][j] == 'u'||mazeCopy[i][j] == '^') {
                        energyzed++;
                    }
                }
            }
            for(int i=0;i<input.size();i++) {
                for(int j=0;j<input.get(0).length();j++){
                    if(mazeMirrors[i][j] == '#') {
                        energyzed++;
                    }
                }
            }
            maxEnergyzed = energyzed>maxEnergyzed ? energyzed : maxEnergyzed;
        }
        System.out.println("Max " +maxEnergyzed);
    }

    private void energyzeBeam(char[][] maze,char[][] mazeMirrors, Beam beam, Stack<Beam> stackOfBeams) {
        boolean endBeam = false;
        while(!endBeam && (beam.getColumn()>=0&& beam.getColumn()< maze[0].length && beam.getRow()>=0 && beam.getRow()< maze.length)) {
            switch (maze[beam.getRow()][beam.getColumn()]) {
                case '\\':
                    if(beam.getDirection() == '^') {
                        beam.setDirection('<');
                    } else if(beam.getDirection() == '>') {
                        beam.setDirection('u');
                    } else if(beam.getDirection() == '<') {
                        beam.setDirection('^');
                    } else if(beam.getDirection() == 'u') {
                        beam.setDirection('>');
                    }
                    mazeMirrors[beam.getRow()][beam.getColumn()] = '#';
                    break;
                case '/':
                    if(beam.getDirection() == '^') {
                        beam.setDirection('>');
                    } else if(beam.getDirection() == '>') {
                        beam.setDirection('^');
                    } else if(beam.getDirection() == '<') {
                        beam.setDirection('u');
                    } else if(beam.getDirection() == 'u') {
                        beam.setDirection('<');
                    }
                    mazeMirrors[beam.getRow()][beam.getColumn()] = '#';
                    break;
                case '-':
                    if(beam.getDirection() == '^' || beam.getDirection() == 'u') {
                        //split
                        Beam newBeam = new Beam(beam.getRow(), beam.getColumn(), '<');
                        stackOfBeams.add(newBeam);
                        beam.setDirection('>');
                    }
                    mazeMirrors[beam.getRow()][beam.getColumn()] = '#';
                    break;
                case '|':
                    if(beam.getDirection() == '>' || beam.getDirection() == '<') {
                        //split
                        Beam newBeam = new Beam(beam.getRow(), beam.getColumn(), '^');
                        stackOfBeams.add(newBeam);
                        beam.setDirection('u');
                    }
                    mazeMirrors[beam.getRow()][beam.getColumn()] = '#';
                    break;
                case '.':
                    maze[beam.getRow()][beam.getColumn()] = beam.getDirection();
                    break;
                default:
                    if(beam.getDirection() == maze[beam.getRow()][beam.getColumn()]) {
                        endBeam = true;
                        break;
                    }
                    break;
            }
            move(beam);
        }
    }

    public void move(Beam beam) {
        switch(beam.getDirection()) {
            case '>':
                beam.setColumn(beam.getColumn()+1);
                break;
            case '<':
                beam.setColumn(beam.getColumn()-1);
                break;
            case '^':
                beam.setRow(beam.getRow()-1);
                break;
            case 'u':
                beam.setRow(beam.getRow()+1);
                break;
        }
    }
    public class Beam {

        private int row;
        private int column;
        private char direction;

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return this.column;
        }

        public char getDirection() {
            return this.direction;
        }

        public void setColumn(int column) {
            this.column = column;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public void setDirection(char direction) {
            this.direction = direction;
        }

        public Beam(int row, int column, char direction) {
            this.row = row;
            this.column = column;
            this.direction = direction;
        }
    }
}
