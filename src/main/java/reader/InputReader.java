package reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {

    public static List<String> getInput() throws IOException {
        try(InputStream is = InputReader.class.getClassLoader().getResourceAsStream("input5.txt")) {
            return new BufferedReader(new InputStreamReader(is,
                    StandardCharsets.UTF_8)).lines().collect(Collectors.toList());
        }
    }
}
