package org.example;

import challenge.Day1;
import challenge.Day2;
import challenge.Day3;
import challenge.Day4;
import challenge.Day5;
import challenge.Day6;
import challenge.Day7;
import challenge.Day8;
import reader.InputReader;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) throws Exception {
        List<String> input = InputReader.getInput();
        new Day8().getResult2(input);
    }
}
