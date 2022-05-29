package com.example.imc.rps;

import com.example.imc.rps.application.GameRunner;
import com.example.imc.rps.infrastructure.GameConfiguration;

import java.io.IOException;

public class RockPaperScissors {
    public static void main(String[] args) throws IOException {
        GameRunner runner = new GameConfiguration().getGameRunner();
        runner.run();
    }
}
