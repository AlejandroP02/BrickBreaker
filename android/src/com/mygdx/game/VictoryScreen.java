package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

/**
 * La clase VictoryScreen representa la pantalla
 * mostrada cuando el jugador gana el juego.
 */
public class VictoryScreen extends ScreenAdapter {

    /**
     * El juego al que pertenece la pantalla de victoria.
     */
    private BrickBreakerGame game;

    /**
     * Constructor de la clase VictoryScreen.
     * @param game La instancia del juego BrickBreakerGame.
     */
    public VictoryScreen(BrickBreakerGame game) {
        this.game = game;
    }

    /**
     * Renderiza la pantalla de victoria.
     * @param delta El tiempo transcurrido desde el último renderizado.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Dibujar tu mensaje de victoria
        game.batch.begin();
        // Aquí puedes dibujar tu mensaje de victoria en la pantalla
        game.font.draw(game.batch, "¡Has ganado!", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        game.batch.end();

        // Si se toca la pantalla, volvemos al menú principal
        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }

}
