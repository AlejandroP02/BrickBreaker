package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;

public class VictoryScreen extends ScreenAdapter {

    private BrickBreakerGame game;

    public VictoryScreen(BrickBreakerGame game) {
        this.game = game;
    }

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
