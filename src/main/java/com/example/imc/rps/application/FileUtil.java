package com.example.imc.rps.application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringJoiner;

class FileUtil {
    String readHelpFromFile() {
        InputStream is = getClass().getClassLoader()
                                   .getResourceAsStream("help.txt");
        return readFromInputStream(is);
    }

    private static String readFromInputStream(InputStream is) {
        StringJoiner sj = new StringJoiner("\n");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            br.lines()
              .forEach(sj::add);
            return sj.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
