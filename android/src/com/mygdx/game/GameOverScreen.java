package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

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
     * Fuente del juego.
     */
    private BitmapFont bigFont;

    /**
     * Constructor de la clase GameOverScreen.
     * @param game La instancia del juego BrickBreakerGame.
     */
    public GameOverScreen(BrickBreakerGame game) {
        this.game = game;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("8bitOperatorPlus-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.borderColor = Color.BLACK;
        params.color = Color.WHITE;
        params.size = 50;
        params.borderWidth = 5;
        bigFont = generator.generateFont(params);
        generator.dispose();
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
        bigFont.draw(game.batch, "Game Over", Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        game.batch.end();

        // Si se toca la pantalla, volvemos al menú principal
        if (Gdx.input.isTouched()) {
            game.setScreen(new MainMenuScreen(game));
            dispose();
        }
    }
}
