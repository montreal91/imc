package com.example.imc.rps.domain.game.core.model;

public record Score(
        int playerOneScore,
        int playerTwoScore,
        String playerOneName,
        String playerTwoName,
        boolean isOver
) {}
