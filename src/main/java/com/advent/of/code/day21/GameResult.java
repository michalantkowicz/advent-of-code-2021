package com.advent.of.code.day21;

class GameResult {
    private long playerOneWins = 0;
    private long playerOneScore = 0;
    private int playerOnePosition;
    private long playerTwoWins = 0;
    private long playerTwoScore = 0;
    private int playerTwoPosition;

    GameResult(int playerOnePosition, int playerTwoPosition) {
        this.playerOnePosition = playerOnePosition;
        this.playerTwoPosition = playerTwoPosition;
    }
    
    GameResult merge(GameResult gameResult) {
        final GameResult result = new GameResult(gameResult.playerOnePosition, gameResult.playerTwoPosition);
        result.playerOneScore = this.playerOneScore + gameResult.playerOneScore;
        result.playerOneWins = this.playerOneWins + gameResult.playerOneWins;
        result.playerTwoScore = this.playerTwoScore + gameResult.playerTwoScore;
        result.playerTwoWins = this.playerTwoWins + gameResult.playerTwoWins;
        return result;
        
    }

    long getPlayerOneWins() {
        return playerOneWins;
    }

    void setPlayerOneWins(long playerOneWins) {
        this.playerOneWins = playerOneWins;
    }

    long getPlayerOneScore() {
        return playerOneScore;
    }

    void setPlayerOneScore(long playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    int getPlayerOnePosition() {
        return playerOnePosition;
    }

    void setPlayerOnePosition(int playerOnePosition) {
        this.playerOnePosition = playerOnePosition;
    }

    long getPlayerTwoWins() {
        return playerTwoWins;
    }

    void setPlayerTwoWins(long playerTwoWins) {
        this.playerTwoWins = playerTwoWins;
    }

    long getPlayerTwoScore() {
        return playerTwoScore;
    }

    void setPlayerTwoScore(long playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    int getPlayerTwoPosition() {
        return playerTwoPosition;
    }

    void setPlayerTwoPosition(int playerTwoPosition) {
        this.playerTwoPosition = playerTwoPosition;
    }
}
