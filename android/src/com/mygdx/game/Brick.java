package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Brick extends Actor {
    static final float WIDTH = 100;
    static final float HEIGHT = 40;

    private Rectangle bounds;
    private Texture texture;

    public Brick(float x, float y) {
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setX(x);
        setY(y);

        bounds = new Rectangle(getX(), getY(), WIDTH, HEIGHT);
        texture = new Texture("brick.png");
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

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


    public Rectangle getBounds() {
        return bounds;
    }

}

