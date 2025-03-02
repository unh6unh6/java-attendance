package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader {

    private static final String FILE_PATH = "src/main/resources/attendances.csv";
    private static final int START_LINE_INDEX = 1;

    public static List<String> readFile() {
        try {
            List<String> lines = Files.readAllLines(Path.of(FILE_PATH));
            return lines.subList(START_LINE_INDEX, lines.size());

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
