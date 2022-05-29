package com.example.imc.rps.infrastructure;

import com.example.imc.rps.application.GameRunner;
import com.example.imc.rps.domain.game.application.SimpleController;
import com.example.imc.rps.domain.game.core.GameService;
import com.example.imc.rps.domain.game.core.model.Action;
import com.example.imc.rps.domain.game.infrastructure.DummyBot;
import com.example.imc.rps.domain.game.infrastructure.InMemoryGameDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

/**
 * In-house dependency injection.
 */
public class GameConfiguration {
    public SimpleController getGameController() {
        GameService service = new GameService(
                new InMemoryGameDatabase(),
                new DummyBot(List.of(
                        Action.ROCK,
                        Action.SPOCK,
                        Action.PAPER,
                        Action.SCISSORS,
                        Action.LIZARD,
                        Action.LIZARD,
                        Action.PAPER,
                        Action.SCISSORS,
                        Action.ROCK,
                        Action.SPOCK
                ))
        );

        return new SimpleController(service, service, service, service, service);
    }

    public GameRunner getGameRunner() {
        SimpleController controller = getGameController();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return new GameRunner(controller, reader, new PrintWriter(System.out));
    }
}
