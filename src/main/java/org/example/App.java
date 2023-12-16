package org.example;

import challenge.Day1;
import challenge.Day10;
import challenge.Day11;
import challenge.Day12;
import challenge.Day13;
import challenge.Day14;
import challenge.Day15;
import challenge.Day2;
import challenge.Day3;
import challenge.Day4;
import challenge.Day5;
import challenge.Day6;
import challenge.Day7;
import challenge.Day8;
import challenge.Day9;
import reader.InputReader;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) throws Exception {
        List<String> input = InputReader.getInput();
        new Day15().getResult2(input);
    }
}
