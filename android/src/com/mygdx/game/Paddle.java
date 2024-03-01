package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * La clase Paddle representa la paleta
 * de control del jugador en el juego.
 */
public class Paddle extends Actor {
    /**
     * Ancho del paddle.
     */
    static final float WIDTH = 200;
    /**
     * Alto del paddle.
     */
    static final float HEIGHT = 40;
    /**
     * Velocidad de movimiento del paddle en píxeles por segundo.
     */
    private static final float SPEED = 300f;
    /**
     * Los límites del paddle
     */
    private Rectangle bounds;
    /**
     * La textura del paddle.
     */
    private Texture texture;

    /**
     * Constructor de la clase Paddle.
     * @param x La posición inicial en el eje x.
     * @param y La posición inicial en el eje y.
     */
    public Paddle(float x, float y) {
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setX(x);
        setY(y);

        bounds = new Rectangle(getX(), getY(), WIDTH, HEIGHT);
        texture = new Texture("paddle.png");
    }

    /**
     * Dibuja el paddle en el lote especificado.
     * @param batch       El lote para dibujar.
     * @param parentAlpha La transparencia del padre del paddle.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Actualiza la posición del paddle basada
     * en la entrada del usuario.
     * @param delta El tiempo transcurrido
     *              desde el último acto.
     */
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

    /**
     * Obtiene los límites del paddle.
     * @return Los límites del paddle.
     */
    public Rectangle getBounds() {
        return bounds;
    }
}
