package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Ball extends Actor {
    static final float SIZE = 40f;
    private static final float SPEED = 300f; // Velocidad de la bola en píxeles por segundo

    private Rectangle bounds;
    private float velocityX;
    private float velocityY;

    public Ball(float x, float y) {
        setWidth(SIZE);
        setHeight(SIZE);
        setX(x);
        setY(y);

        bounds = new Rectangle(getX(), getY(), SIZE, SIZE);
        // Inicializamos la velocidad de la bola
        velocityX = SPEED;
        velocityY = SPEED;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(new Texture("ball.png"), getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        clear();

        // Actualizamos la posición de la bola basada en su velocidad y el tiempo transcurrido
        setX(getX() + velocityX * delta);
        setY(getY() + velocityY * delta);

        // Actualizamos los límites de la bola
        bounds.setPosition(getX(), getY());

        if (getX() < 0) {
            setX(0);
            velocityX *= -1; // Invierte la dirección horizontal
        } else if (getX() + getWidth() > Gdx.graphics.getWidth()) {
            setX(Gdx.graphics.getWidth() - getWidth());
            velocityX *= -1; // Invierte la dirección horizontal
        }
        if (getY() < 0) {
            setY(0);
            velocityY *= -1; // Invierte la dirección vertical
        } else if (getY() + getHeight() > Gdx.graphics.getHeight()) {
            setY(Gdx.graphics.getHeight() - getHeight());
            velocityY *= -1; // Invierte la dirección vertical
        }

    }

    public Rectangle getBounds() {
        return bounds;
    }


    public void bounceOffPaddle(Paddle paddle) {
        clear();
        // Calcular la posición de la pelota en el eje y antes de la colisión
        float previousY = getY() - velocityY;

        // Verificar si la pelota estaba por debajo del paddle antes de la colisión
        boolean wasBelowPaddle = previousY + getHeight() < paddle.getY();

        // Ajustar la posición de la pelota
        if (velocityY > 0) {
            // Si la pelota se mueve hacia arriba, la ajustamos a la parte superior del paddle
            setY(paddle.getY() - getHeight());
        } else {
            // Si la pelota se mueve hacia abajo, la ajustamos a la parte inferior del paddle
            setY(paddle.getY() + paddle.getHeight());
        }

        // Invertir la dirección vertical de la pelota
        velocityY *= -1;

        // Si la pelota estaba por debajo del paddle antes de la colisión y ahora está por encima,
        // ajustamos su posición para evitar que atraviese el paddle
        if (wasBelowPaddle && getY() < paddle.getY()) {
            setY(paddle.getY() - getHeight());
        }
    }

    public boolean collidesWithBrick(Brick brick) {
        clear();
        return bounds.overlaps(brick.getBounds());
    }
    public void bounceOffBrick(Brick brick) {
        clear();
        float overlapX = Math.min(getX() + getWidth(), brick.getX() + brick.getWidth()) - Math.max(getX(), brick.getX());
        float overlapY = Math.min(getY() + getHeight(), brick.getY() + brick.getHeight()) - Math.max(getY(), brick.getY());

        if (overlapX > overlapY) {
            // Colisión vertical
            velocityY *= -1;
        } else {
            // Colisión horizontal
            velocityX *= -1;
        }
    }

}

