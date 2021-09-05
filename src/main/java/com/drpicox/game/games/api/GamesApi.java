package com.drpicox.game.games.api;

import com.drpicox.game.common.api.GlobalRestException;
import com.drpicox.game.common.api.SuccessResponse;
import com.drpicox.game.games.GamesController;
import com.drpicox.game.players.PlayersController;
import com.drpicox.game.players.api.LoginForm;
import com.drpicox.game.players.api.PlayersApi;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/games")
public class GamesApi {
    private final GamesController gamesController;
    private final PlayersController playersController;
    private final PlayersApi playersApi;
    private final List<GameResponder> gameResponders;

    public GamesApi(GamesController gamesController, PlayersController playersController, PlayersApi playersApi, List<GameResponder> gameResponders) {
        this.gamesController = gamesController;
        this.playersController = playersController;
        this.playersApi = playersApi;
        this.gameResponders = gameResponders;
        // TODO: Temporal, removeme
        gameResponders.sort((a, b) -> a.getClass().getSimpleName().compareTo(b.getClass().getSimpleName()));
    }

    @PostMapping
    public SuccessResponse createGame(@RequestBody CreateGameForm form) {
        form.verify();

        var gameName = form.getGameName();
        var token = form.getToken();

        var player = playersController.findPlayerByToken(token).orElseThrow();
        var createdGame = gamesController.createGame(gameName, player);
        if (createdGame.isEmpty())
            throw new GlobalRestException("The " + gameName + " game already exists");


        return new SuccessResponse("The " + gameName + " game has been created successfully");
    }

    @PostMapping("/join")
    public SuccessResponse join(@RequestBody JoinGameForm form) {
        var gameName = form.getGameName();
        var creatorName = form.getCreatorName();
        var token = form.getToken();

        var joiningPlayer = playersController.findPlayerByToken(token).orElseThrow();
        var creatorPlayer = playersController.findPlayer(creatorName).orElseThrow();

        var game = gamesController.join(gameName, creatorPlayer, joiningPlayer).orElseThrow(
                () -> new GlobalRestException("You should join games in which are you not joined")
        );

        return new SuccessResponse("You have joined " + game.getGameName() + " game from " + game.getCreator().getPlayerName() + " successfully");
    }

    @PostMapping("/joinNext")
    public GameResponse joinNext(@RequestBody JoinNextForm form) {
        var playerName = form.getPlayerName();
        var password = form.getPassword();
        var loginResponse = playersApi.login(new LoginForm(playerName, password));
        var token = loginResponse.getToken();

        var gameName = form.getGameName();
        var creatorName = form.getCreatorName();
        join(new JoinGameForm(gameName, creatorName, token));

        return get(gameName, creatorName, token);
    }

    @PostMapping("/byPlayer")
    public ListGamesResponse listGamesByCreator(@RequestBody ListGamesByCreatorForm form) {
        form.verify();

        var playerName = form.getPlayerName();
        var player = playersController.findPlayer(playerName).orElseThrow(() -> new GlobalRestException("The friend name should be an existing player"));

        var result = new ListGamesResponse();
        var games = gamesController.findByCreator(player);
        games.forEach(result::addGame);

        return result;
    }

    @PostMapping("/byJoined")
    public ListGamesResponse listGamesByJoined(@RequestBody ListGamesByJoinedForm form) {
        var token = form.getToken();
        var player = playersController.findPlayerByToken(token).orElseThrow();

        var result = new ListGamesResponse();
        var games = gamesController.findByJoined(player);
        games.forEach(result::addGame);

        return result;
    }

    @GetMapping("/{gameName}/by/{creatorName}")
    public GameResponse get(@PathVariable String gameName, @PathVariable String creatorName, @RequestParam String token) {
        var playingPlayer = playersController.findPlayerByToken(token).orElseThrow();
        var creatorPlayer = playersController.findPlayer(creatorName).orElseThrow();

        var game = gamesController.findGame(gameName, creatorPlayer).orElseThrow();
        if (!game.isPlayerJoined(playingPlayer))
            throw new GlobalRestException("You should play only games in which you are joined");

        var response = new GameResponse(game, playingPlayer.getPlayerName(), token);
        gameResponders.stream().forEach(r -> r.respond(response, game, playingPlayer));
        return response;
    }

    @PostMapping("/{gameName}/by/{creatorName}/endRound")
    public GameResponse endRound(@PathVariable String gameName, @PathVariable String creatorName, @RequestParam String token) {
        var playingPlayer = playersController.findPlayerByToken(token).orElseThrow();
        var creatorPlayer = playersController.findPlayer(creatorName).orElseThrow();
        gamesController.endRound(gameName, creatorPlayer);

        return get(gameName, creatorName, token);
    }
}
