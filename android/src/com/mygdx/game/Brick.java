package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Brick extends Actor {
    /**
     * Ancho del ladrillo.
     */
    static final float WIDTH = 100;
    /**
     * Alto del ladrillo.
     */
    static final float HEIGHT = 40;
    /**
     * Los límites del ladrillo.
     */
    private Rectangle bounds;
    /**
     * La textura del ladrillo.
     */
    private Texture texture;

    /**
     * Constructor de la clase Brick.
     * @param x La posición inicial en el eje x.
     * @param y La posición inicial en el eje y.
     */
    public Brick(float x, float y) {
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setX(x);
        setY(y);

        bounds = new Rectangle(getX(), getY(), WIDTH, HEIGHT);
        texture = new Texture("brick.png");
    }

    /**
     * Dibuja el ladrillo en el lote especificado.
     * @param batch       El lote para dibujar.
     * @param parentAlpha La transparencia del padre del ladrillo.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Actualiza la posición del ladrillo.
     * @param delta El tiempo transcurrido desde el último acto.
     */
    @Override
    public void act(float delta) {
        super.act(delta);

        // Verifica los límites de la pantalla
        if (getX() < 0) {
            setX(0);
        } else if (getX() + getWidth() > Gdx.graphics.getWidth()) {
            setX(Gdx.graphics.getWidth() - getWidth());
        }
        if (getY() < 0) {
            setY(0);
        } else if (getY() + getHeight() > Gdx.graphics.getHeight()) {
            setY(Gdx.graphics.getHeight() - getHeight());
        }
    }

    /**
     * Obtiene los límites del ladrillo.
     * @return Los límites del ladrillo.
     */
    public Rectangle getBounds() {
        return bounds;
    }

}

