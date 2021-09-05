package com.drpicox.game.cities;

import com.drpicox.game.games.Game;
import com.drpicox.game.players.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface CityRepository extends JpaRepository<City, String> {
    List<City> findAllByGame(Game game);
    List<City> findAllByGameAndOwner(Game game, Player owner);
}
