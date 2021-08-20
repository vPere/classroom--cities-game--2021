package com.drpicox.game.testSteps.login;

import com.drpicox.game.testPost.reader.PostLine;
import com.drpicox.game.testSteps.AbstractPostLineStep;

import org.springframework.stereotype.Component;

@Component
public class GoToTheLoginStep extends AbstractPostLineStep {

    private LoginTestView loginTestView;

    public GoToTheLoginStep(LoginTestView loginTestView) {
        this.loginTestView = loginTestView;
    }

    @Override
    protected String getRegex() {
        return "Go to the login";
    }

    @Override
    protected void run(PostLine line, String[] match) {
        loginTestView.clear();
    }
}