package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

/**
 * La clase MainMenuScreen representa la pantalla principal del juego.
 * Esta pantalla muestra un botón para iniciar el juego.
 */
public class MainMenuScreen extends ScreenAdapter {

    /**
     * Referencia al juego principal.
     */
    private BrickBreakerGame game;
    /**
     * Escenario para mostrar los elementos de la pantalla.
     */
    private Stage stage;
    /**
     * Cámara para la vista ortográfica.
     */
    private OrthographicCamera camera;
    /**
     * Renderizador para dibujar formas geométricas en la pantalla.
     */
    private ShapeRenderer shapeRenderer;

    /**
     * Constructor para crear una instancia de MainMenuScreen.
     * @param game El juego al que pertenece esta pantalla.
     */
    public MainMenuScreen(BrickBreakerGame game) {
        this.game = game;
    }

    /**
     * Se llama cuando esta pantalla se muestra.
     * Configura la cámara, el escenario y el renderizador de formas.
     * Crea y agrega un botón de reproducción al escenario.
     */
    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
        Gdx.input.setInputProcessor(stage);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = game.font;
        buttonStyle.fontColor = Color.WHITE;

        TextButton playButton = new TextButton("PLAY", buttonStyle);
        playButton.setSize(1000, 500);
        playButton.setPosition((Gdx.graphics.getWidth() - playButton.getWidth()) / 2, Gdx.graphics.getHeight() * 0.5f);
        playButton.getLabel().setAlignment(Align.center);
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
                dispose();
            }
        });

        stage.addActor(playButton);
    }

    /**
     * Se llama para renderizar la pantalla.
     * Limpia el color de la pantalla y dibuja
     * el botón de reproducción y el fondo.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(50, 200, Gdx.graphics.getWidth() - 100, 20);
        shapeRenderer.end();

        stage.act(delta);
        stage.draw();
    }

    /**
     * Se llama cuando la ventana cambia de tamaño.
     * Actualiza el viewport del escenario para
     * adaptarse al nuevo tamaño de la ventana.
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    /**
     * Se llama cuando esta pantalla deja de mostrarse.
     * Libera los recursos utilizados por el escenario
     * y el renderizador de formas.
     */
    @Override
    public void hide() {
        stage.dispose();
        shapeRenderer.dispose();
    }
}

