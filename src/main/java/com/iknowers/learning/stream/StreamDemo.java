package com.iknowers.learning.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StreamDemo {

    public static void main(String[] args) {
        countLines(args[0]);
    }

    public static void countLines(String path) {
        try (BufferedReader reader =
                     Files.newBufferedReader(
                             Paths.get(path),
                             StandardCharsets.UTF_8)) {
            System.out.println(reader.lines().count());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
