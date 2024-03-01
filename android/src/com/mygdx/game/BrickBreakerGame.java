package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * La clase BrickBreakerGame es la clase principal del juego.
 * */
public class BrickBreakerGame extends Game {
    /**
     * Lote para dibujar sprites en 2D.
     */
    public SpriteBatch batch;
    /**
     * Fuente para mostrar texto.
     */
    public BitmapFont font;

    /**
     * Se llama cuando el juego se está creando.
     */
    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        MainMenuScreen mainMenuScreen = new MainMenuScreen(this); // Pasamos la instancia de BrickBreakerGame a MainMenuScreen
        setScreen(mainMenuScreen);
    }

    /**
     * Se llama cuando el juego se está cerrando.
     */
    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
