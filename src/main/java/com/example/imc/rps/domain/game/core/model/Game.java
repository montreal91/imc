package com.example.imc.rps.domain.game.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Rock-Paper-Scissors game
 *
 * It takes a single action from each of two players and compares them.
 * If player one beats player two, player one score increases by one and vice versa.
 * The whole history of comparisons for the game is stored.
 */
public class Game {
    private GameId id;
    private final int roundsToWin;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;
    private Action playerOneAction = null;
    private Action playerTwoAction = null;
    private final ActionComparator comparator;
    private final List<ActionMatch> matches = new ArrayList<>();

    public Game(int roundsToWin, ActionComparator comparator) {
        this.roundsToWin = roundsToWin;
        this.comparator = comparator;
    }

    public int getPlayerTwoScore() {
        return playerTwoScore;
    }

    public int getPlayerOneScore() {
        return playerOneScore;
    }

    public GameId getId() {
        return id;
    }

    public void setId(GameId id) {
        this.id = id;
    }

    public void setPlayerOneAction(Action playerOneAction) {
        this.playerOneAction = playerOneAction;
    }

    public void setPlayerTwoAction(Action playerTwoAction) {
        this.playerTwoAction = playerTwoAction;
    }

    public void match() {
        if (playerOneAction == null || playerTwoAction == null) {
            return;
        }
        if (comparator.compare(playerOneAction, playerTwoAction) > 0) {
            playerOneScore++;
        } else if (comparator.compare(playerOneAction, playerTwoAction) < 0) {
            playerTwoScore++;
        }

        matches.add(new ActionMatch(playerOneAction, playerTwoAction));
        playerOneAction = null;
        playerTwoAction = null;
    }

    public boolean isOver() {
        return playerOneScore == roundsToWin || playerTwoScore == roundsToWin;
    }

    public List<ActionMatch> getMatches() {
        return List.copyOf(matches);
    }
}
