package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BrickBreakerGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this); // Pasamos la instancia de BrickBreakerGame a MainMenuScreen
        setScreen(mainMenuScreen);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
