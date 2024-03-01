package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

/**
 * La clase GameOverScreen representa la pantalla
 * mostrada cuando se pierde en el juego.
 */
public class GameOverScreen extends ScreenAdapter {

    /**
     * El juego al que pertenece la pantalla de Game Over.
     */
    private BrickBreakerGame game;

    /**
     * Constructor de la clase GameOverScreen.
     * @param game La instancia del juego BrickBreakerGame.
     */
    public GameOverScreen(BrickBreakerGame game) {
        this.game = game;
    }

    /**
     * Renderiza la pantalla de Game Over.
     * @param delta El tiempo transcurrido desde el último renderizado.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibujar tu mensaje de Game Over
        game.batch.begin();
        // Aquí puedes dibujar tu mensaje de Game Over en la pantalla
        game.font.draw(game.batch, "Game Over", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        game.batch.end();

        // Si se toca la pantalla, volvemos al menú principal
        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }
}
