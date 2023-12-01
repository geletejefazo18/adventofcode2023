package org.example;

import challenge.Day1;
import reader.InputReader;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) throws Exception {
        List<String> input = InputReader.getInput();
        new Day1().getResultPart2WithLetters(input);
    }
}
