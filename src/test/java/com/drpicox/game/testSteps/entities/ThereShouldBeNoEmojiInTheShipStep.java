package com.drpicox.game.testSteps.entities;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

import static com.google.common.truth.Truth.assertThat;

@Component
public class ThereShouldBeNoEmojiInTheShipStep extends AbstractPostLineStep {


    public ThereShouldBeNoEmojiInTheShipStep() {
    }

    @Override
    protected String getRegex() {
        return "There should be no \"([^\"]+)\" emoji in the \"([^\"]+)\" ship \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
    }
}
