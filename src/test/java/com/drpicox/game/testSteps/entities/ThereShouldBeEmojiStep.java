package com.drpicox.game.testSteps.entities;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;
import org.springframework.stereotype.Component;

@Component
public class ThereShouldBeEmojiStep extends AbstractPostLineStep {


    public ThereShouldBeEmojiStep() {
    }

    @Override
    protected String getRegex() {
        return "There should be \"([^_]+)\" emoji";
    }

    @Override
    protected void run(PostLine line, String[] match) {
    }
}
