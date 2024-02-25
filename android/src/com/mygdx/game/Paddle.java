package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Paddle extends Actor {
    static final float WIDTH = 200;
    static final float HEIGHT = 40;
    private static final float SPEED = 300f; // Velocidad de movimiento del paddle en píxeles por segundo

    private Rectangle bounds;

    public Paddle(float x, float y) {
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setX(x);
        setY(y);

        bounds = new Rectangle(getX(), getY(), WIDTH, HEIGHT);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(new Texture("paddle.png"), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        // Mover el paddle hacia la izquierda si la tecla izquierda está presionada
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            moveBy(-SPEED * delta, 0);
        }

        // Mover el paddle hacia la derecha si la tecla derecha está presionada
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            moveBy(SPEED * delta, 0);
        }

        // Restringir el paddle dentro de los límites de la pantalla
        if (getX() < 0) {
            setX(0);
        }
        if (getX() + getWidth() > Gdx.graphics.getWidth()) {
            setX(Gdx.graphics.getWidth() - getWidth());
        }

        // Actualizar los límites del paddle
        bounds.setPosition(getX(), getY());
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
