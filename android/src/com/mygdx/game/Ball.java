package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * La clase Ball representa la pelota en el juego.
 */
public class Ball extends Actor {
    /**
     * Tamaño de la pelota.
     */
    static final float SIZE = 40f;
    /**
     * Velocidad de la bola en píxeles por segundo
     */
    private static final float SPEED = 500f;
    /**
     * Los límites de la pelota
     */
    private Rectangle bounds;
    /**
     *
     * La velocidad horizontal de la pelota.
     */
    private float velocityX;
    /**
     * La velocidad vertical de la pelota.
     */
    private float velocityY;
    /**
     * La textura de la pelota
     */
    private Texture texture;

    /**
     * Constructor de la clase Ball.
     * @param x La posición inicial en el eje x.
     * @param y La posición inicial en el eje y.
     */
    public Ball(float x, float y) {
        setWidth(SIZE);
        setHeight(SIZE);
        setX(x);
        setY(y);

        bounds = new Rectangle(getX(), getY(), SIZE, SIZE);
        // Inicializamos la velocidad de la bola
        velocityX = SPEED;
        velocityY = SPEED;
        texture = new Texture("ball.png");
    }

    /**
     * Dibuja la pelota en el lote especificado.
     * @param batch       El lote para dibujar.
     * @param parentAlpha La transparencia del padre de la pelota.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Actualiza la posición de la pelota en función del tiempo transcurrido.
     * @param delta El tiempo transcurrido desde el último acto.
     */
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

    /**
     * Obtiene los límites de la pelota.
     * @return Los límites de la pelota.
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Rebota la pelota fuera de un paddle.
     * @param paddle El paddle con el que la pelota colisionó.
     */
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

    /**
     * Comprueba si la pelota colisiona con un ladrillo.
     * @param brick El ladrillo con el que se comprueba la colisión.
     * @return true si la pelota colisiona con el ladrillo, false en caso contrario.
     */
    public boolean collidesWithBrick(Brick brick) {
        clear();
        return bounds.overlaps(brick.getBounds());
    }

    /**
     * Rebota la pelota fuera al tocar un ladrillo.
     * @param brick El ladrillo con el que la pelota colisionó.
     */
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

