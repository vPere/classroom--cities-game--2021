package com.drpicox.game.testSteps.game;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import com.drpicox.game.testSteps.helpers.CitiesHelper;
import org.springframework.stereotype.Component;

import static com.drpicox.game.nameds.api.NamedResponse.toName;
import static com.google.common.truth.Truth8.assertThat;

@Component
public class ShouldHaveTheCityStep extends AbstractPostLineStep {


    private final GameTestView gameTestView;

    public ShouldHaveTheCityStep(GameTestView gameTestView) {
        this.gameTestView = gameTestView;
    }

    @Override
    protected String getRegex() {
        return "\"([^\"]+)\" should have the \"([^\"]+)\" city";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        var playerName = match[1];
        var cityName = match[2];

        var game = gameTestView.getGame();
        var cities = CitiesHelper.findAllByOwner(game, playerName);
        var cityNames = cities.stream().map(toName());
        assertThat(cityNames).contains(cityName);
    }
}
