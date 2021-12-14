package com.drpicox.game.testSteps.entities;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class ThereShouldBeEmojiInTheShipStep extends AbstractPostLineStep {


    public ThereShouldBeEmojiInTheShipStep() {
    }

    @Override
    protected String getRegex() {
        return "There should be \"([^\"]+)\" emoji in the \"([^\"]+)\" ship \"([^\"]+)\"";
    }

    @Override
    protected void run(PostLine line, String[] match) {
    }
}
