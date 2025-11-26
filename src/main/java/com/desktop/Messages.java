package com.desktop;

public interface Messages {
    public final String K_TYPE = "type";
    public final String K_VALUE = "value";
    
    // Config
    public final String T_GET_NAME = "getName";
    public final String C_CHECK_NAME = "checkMyName";
    public final String T_CHECK_NAME_STATUS = "checkNameStatus";
    public final String V_NAME_AVAILABLE = "NameAvailable";
    public final String V_NAME_USED = "NameUsed";

    // Waiting
    public final String C_AWAITING_COUNTDOWN = "waitingCountdown";
    public final String T_START_COUNTDOWN = "goCountdownActivity";

    // Countdown
    public final String T_COUNTDOWN = "countdown";
    public final String V_P1NAME = "player1";
    public final String V_P2NAME = "player2";
    public final String V_SECONDS = "msgCountDown";
    public final String C_READY_STARTGAME = "readyStartGame";

    // Game
    public final String T_SERVER_START_GAME = "startGame";
    public final String T_CLIENT_POSSITION = "clientPoss";
    public final String T_SERVER_DATA = "serverData";
    public final String K_CLIENTS_LIST = "serverClients";
    public final String K_SERVER_GAME_DATA = "serverGameData";
    public final String C_MOVE = "clientMove";
    public final String C_INPUT = "input";
    public final String C_NAME = "clientName";

    public final String T_INIT_ROUND_COUNTDOWN = "roundCountDown";
    public final String T_WINNER = "winner";

    // Results
    public final String C_PLAY_AGAIN = "playAgain";
    public final String C_EXIT = "exit";
}
