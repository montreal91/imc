package com.example.imc.rps.application;

import com.example.imc.rps.domain.game.application.SimpleController;
import com.example.imc.rps.domain.game.core.model.Action;
import com.example.imc.rps.domain.game.core.model.ActionMatch;
import com.example.imc.rps.domain.game.core.model.GameId;
import com.example.imc.rps.domain.game.core.model.GameMatchQuery;
import com.example.imc.rps.domain.game.core.model.GetGameScoreQuery;
import com.example.imc.rps.domain.game.core.model.MakeActionCommand;
import com.example.imc.rps.domain.game.core.model.Score;
import com.example.imc.rps.domain.game.core.model.StartNewGameCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Optional;

/**
 * A 'client' for the game.
 * <p>
 * Provides very basic user interface for the game.
 */
public class GameRunner {
    private final SimpleController gameController;
    private final BufferedReader reader;
    private final Writer writer;
    private GameId currentGameId;
    private boolean isRunning = true;
    private final FileUtil fileUtil = new FileUtil();

    public GameRunner(
            SimpleController gameController,
            BufferedReader reader,
            Writer writer
    ) {
        this.gameController = gameController;
        this.reader = reader;
        this.writer = writer;
    }

    public void run() throws IOException {
        safePrint("Rock Paper Scissors Lizard Spock", "\n\n");
        safePrint("If confused enter 'help'.");
        safePrint("Please enjoy this little game.");

        while (isRunning) {
            printPrompt();
            processUserInput(reader.readLine().toUpperCase());
        }
    }

    private void processUserInput(String input) {
        switch (dispatch(input)) {
            case HELP -> processHelp();
            case EXIT -> processExit();
            case START -> processStart(input);
            case ACTION -> processAction(input);
            case STAT -> processStat();
            case UNRECOGNIZED -> processUnrecognized();
        }
    }

    private UserCommand dispatch(String input) {
        if (input.equals("HELP")) {
            return UserCommand.HELP;
        }

        if (input.equals("EXIT")) {
            return UserCommand.EXIT;
        }

        if (input.equals("STAT")) {
            return UserCommand.STAT;
        }

        if (input.startsWith("START")) {
            return UserCommand.START;
        }

        if (input.startsWith("ACTION")) {
            return UserCommand.ACTION;
        }

        return UserCommand.UNRECOGNIZED;
    }

    private void processHelp() {
        safePrint(fileUtil.readHelpFromFile());
    }

    private void processExit() {
        isRunning = false;
    }

    private void processStat() {
        List<Score> scores = gameController.getAllScores();
        scores.forEach(this::printScore);
    }

    private void processStart(String input) {
        try {
            String[] split = input.split("/");
            if (split.length != 2) {
                safePrint(
                        "*** Please enter a valid start command 'start/<rounds>'"
                );
                return;
            }
            int roundsToWin = Integer.parseInt(split[1]);
            // TODO: get rid of dependencies on core commands in this class
            currentGameId = gameController.startNewGame(
                    new StartNewGameCommand(roundsToWin)
            );
            printCurrentScore();
        } catch (NumberFormatException nfe) {
            safePrint("*** Please enter a valid integer as a number of rounds");
        }
    }

    private void processAction(String input) {
        if (currentGameId == null) {
            safePrint(
                    "*** Before taking any actions please start the game " +
                            "with 'start/<rounds>' command."
            );
            return;
        }
        String[] split = input.split("/");
        if (split.length != 2) {
            safePrint(
                    "*** Please enter a valid action command 'action/" +
                            "<rock|paper|scissors|lizard|spock>'"
            );
            return;
        }
        try {
            Action action = Action.valueOf(split[1]);
            Optional<Score> currentScore = gameController.makeAction(
                    new MakeActionCommand(currentGameId, action)
            );
            List<ActionMatch> matches = gameController.getGameMatches(
                    new GameMatchQuery(currentGameId)
            );
            safePrint("<<< You show: " + matches.get(matches.size() - 1).one());
            safePrint("<<< Ai shows: " + matches.get(matches.size() - 1).two());
            currentScore.ifPresent(score -> {
                printScore(score);
                if (score.isOver()) {
                    safePrint("<<< The winner is " + getWinner(score));
                    safePrint("<<< You can start new game or exit.");
                    currentGameId = null;
                }
            });

        } catch (IllegalArgumentException iae) {
            safePrint(
                    "*** Please enter a valid action command 'action/" +
                            "<rock|paper|scissors|lizard|spock>'"
            );
        }
    }

    private void processUnrecognized() {
        safePrint("*** Unrecognized command.");
    }

    private void printCurrentScore() {
        if (currentGameId == null) {
            return;
        }

        Optional<Score> score = gameController.getGameScore(
                new GetGameScoreQuery(currentGameId)
        );
        score.ifPresent(this::printScore);
    }

    private void printScore(Score score) {
        safePrint(String.format(
                "<<< %s [%s - %s] %s",
                score.playerOneName(),
                score.playerOneScore(),
                score.playerTwoScore(),
                score.playerTwoName()
        ));
    }

    private void safePrint(String s) {
        safePrint(s, "\n");
    }

    private void safePrint(String s, String end) {
        try {
            writer.write(s + end);
            writer.flush();
        } catch (IOException e) {
            System.err.println(
                    "!!! Unexpected output error. Please restart the game"
            );
            System.exit(1);
        }
    }

    private void printPrompt() {
        safePrint(">>> ", "");
    }

    private String getWinner(Score score) {
        if (score.playerOneScore() > score.playerTwoScore()) {
            return score.playerOneName();
        }
        return score.playerTwoName();
    }
}
