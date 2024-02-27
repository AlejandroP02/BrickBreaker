package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen extends ScreenAdapter implements InputProcessor {
    private BrickBreakerGame game;
    private Stage stage;
    private Paddle paddle;
    private Ball ball;
    private Brick[][] bricks;

    OrthographicCamera camera;

    public GameScreen(BrickBreakerGame game) {
        this.game = game;

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

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
    }


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


        if (ball.getY() <= 0) {
            game.setScreen(new GameOverScreen(game));
            dispose();
        } else if(allBricksDestroyed()) {
            game.setScreen(new VictoryScreen(game));
            dispose();
        } else {
            stage.act(delta);
            stage.draw();
        }
    }

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
