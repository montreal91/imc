package com.example.imc.rps.domain.game.core.model;

public class ActionComparator {
    int compare(Action one, Action two) {
        return switch (one) {
            case ROCK -> switch (two) {
                case ROCK -> 0;
                case SCISSORS, LIZARD -> 1;
                case PAPER, SPOCK -> -1;
            };
            case PAPER -> switch (two) {
                case PAPER -> 0;
                case ROCK, SPOCK -> 1;
                case SCISSORS, LIZARD -> -1;
            };
            case SCISSORS -> switch (two) {
                case SCISSORS -> 0;
                case PAPER, LIZARD -> 1;
                case ROCK, SPOCK -> -1;
            };
            case LIZARD -> switch (two) {
                case LIZARD -> 0;
                case PAPER, SPOCK -> 1;
                case ROCK, SCISSORS -> -1;
            };
            case SPOCK -> switch (two) {
                case SPOCK -> 0;
                case ROCK, SCISSORS -> 1;
                case PAPER, LIZARD -> -1;
            };
        };
    }
}
