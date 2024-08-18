package com.craftinginterpretrs.lox;

import java.io.BufferedReader; // reading text from an input stream
import java.io.IOException; // signals an I/O error has occurred
import java.io.InputStreamReader; // bridges byte streams to character streams
import java.nio.charset.Charset; // handles character encoding
import java.nio.file.Files; // working with files
import java.nio.file.Paths; // working with file system paths
import java.util.List; // interface that represents a list of objects

public class Lox {
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.out.println("Usage: jlox [script]");
            System.exit(64);
        } else if (args.length == 1) {
            runFile(args[0]);
        } else {
            runPrompt();
        }
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break; // checks for ctrl-D
            run(line);
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();
    }
}
