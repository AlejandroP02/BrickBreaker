package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * La clase GameScreen representa la pantalla
 * principal del juego donde ocurre la acción del juego.
 */
public class GameScreen extends ScreenAdapter implements InputProcessor {
    /**
     * El juego.
     */
    private BrickBreakerGame game;
    /**
     * Vidas que tiene el jugador.
     */
    private int vidas;
    /**
     * Fuente del juego.
     */
    private BitmapFont bigFont;
    /**
     * El escenario.
     */
    private Stage stage;
    /**
     * La paleta de control del jugador.
     */
    private Paddle paddle;
    /**
     * La pelota.
     */
    private Ball ball;
    /**
     * Los ladrillos.
     */
    private Brick[][] bricks;
    /**
     * La cámara.
     */
    OrthographicCamera camera;

    /**
     * Constructor de la clase GameScreen.
     * @param game La instancia de BrickBreakerGame.
     */
    public GameScreen(BrickBreakerGame game) {
        this.game = game;

        vidas=3;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        stage = new Stage();
        stage.getViewport().setCamera(camera);
        Gdx.input.setInputProcessor(stage);

        paddle = new Paddle((480 - Paddle.WIDTH) / 2, 300);
        ball = new Ball((Gdx.graphics.getWidth() - Ball.SIZE) / 2, Gdx.graphics.getHeight() / 2);
        ball.setPosition(paddle.getX() + (Paddle.WIDTH - Ball.SIZE) / 2, paddle.getY() + Paddle.HEIGHT);
        bricks = new Brick[10][10];
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                bricks[row][col] = new Brick(col * (Brick.WIDTH + 2) + (Gdx.graphics.getWidth() - (10 * (Brick.WIDTH + 2))) / 2, (Gdx.graphics.getHeight()) / 1.5f + row * (Brick.HEIGHT + 2));
            }
        }



        stage.addActor(paddle);
        stage.addActor(ball);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                stage.addActor(bricks[row][col]);
            }
        }

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("8bitOperatorPlus-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter params = new FreeTypeFontGenerator.FreeTypeFontParameter();
        params.borderColor = Color.BLACK;
        params.color = Color.WHITE;
        params.size = 50;
        params.borderWidth = 5;
        bigFont = generator.generateFont(params);
        generator.dispose();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
    }

    /**
     * Renderiza la pantalla del juego.
     * @param delta El tiempo transcurrido
     *             desde el último fotograma.
     */
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(ball.getBounds().overlaps(paddle.getBounds())) {
            ball.bounceOffPaddle(paddle);
        }
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                Brick brick = bricks[row][col];
                if (brick != null && ball.collidesWithBrick(brick)) {
                    // Colisión detectada, eliminar el bloque
                    bricks[row][col] = null;
                    stage.getActors().removeValue(brick, true);
                    // Rebotar la pelota
                    ball.bounceOffBrick(brick);
                }
            }
        }


        // Actualizar la lógica de movimiento de la pelota
        ball.clear();
        ball.act(delta);


        if (ball.getY() <= 40) {
            if (vidas>0){
                vidas--;
                ball.setPosition(paddle.getX() + (Paddle.WIDTH - Ball.SIZE) / 2, paddle.getY() + Paddle.HEIGHT);
            }else {
                game.setScreen(new GameOverScreen(game));
            }
        } else if(allBricksDestroyed()) {
            game.setScreen(new VictoryScreen(game));
            dispose();
        } else {
            stage.act(delta);
            stage.draw();
            game.batch.begin();
            bigFont.draw(game.batch, "Vidas: " + vidas, 50, 50);
            game.batch.end();
        }
    }

    /**
     * Verifica si todos los ladrillos han sido destruidos.
     * @return true si todos los ladrillos han sido destruidos,
     * false en caso contrario.
     */
    private boolean allBricksDestroyed() {
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (bricks[row][col] != null) {
                    return false; // Todavía hay ladrillos
                }
            }
        }
        return true; // No quedan ladrillos
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {
        stage.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }


    /**
     * Se llama cuando se arrastra el dedo sobre la pantalla.
     * @param screenX La coordenada X de la posición del puntero.
     * @param screenY La coordenada Y de la posición del puntero.
     * @param pointer El índice del puntero.
     * @return true si se procesó el evento, false de lo contrario.
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float touchX = stage.getViewport().unproject(new Vector3(screenX, screenY, 0)).x;
        paddle.setX(MathUtils.clamp(touchX - Paddle.WIDTH / 2, 0, stage.getViewport().getWorldWidth() - Paddle.WIDTH));
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
