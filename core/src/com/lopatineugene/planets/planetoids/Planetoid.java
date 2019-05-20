package com.lopatineugene.planets.planetoids;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.FloatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.lopatineugene.planets.GameManager;
import com.lopatineugene.planets.util.Maths;

import java.util.ArrayList;
import java.util.Collection;

public class Planetoid {
    GameManager gameManager;
    Stage stage;
    private Planetoid parent;
    private Image img;
    private Group group;
    private float distance;

    protected Group getGroup() {
        return group;
    }

    private class SatelliteAction extends FloatAction {
        private float distance;
        public SatelliteAction(float start, float end, float duration, float distance) {
            super(start, end, duration);
            this.distance = distance;
        }

        @Override
        protected void begin() {
            super.begin();
        }

        @Override
        protected void update(float percent) {
            super.update(percent);
            getActor().setX(MathUtils.cos(getValue())*distance);
            getActor().setY(MathUtils.sin(getValue())*distance);
        }
    }

    /**
     *
     * @param gameManager -
     * @param parent - null for root
     * @param color -
     * @param revolutionDuration - duration of one full revolution
     */
    public Planetoid (GameManager gameManager, Planetoid parent, TextureRegion textureRegion, Color color, float size, float distance, float revolutionDuration) {
        this.gameManager = gameManager;
        stage = gameManager.getStage();
        this.parent = parent;
        group = new Group();
        img = new Image(textureRegion);
        img.setSize(size,size);
        img.setOrigin(size/2, size/2);
        img.setPosition(-size/2, -size/2);
        img.setColor(color);
        group.addActor(img);
        if (parent == null) {
            group.setPosition(stage.getWidth()/2, stage.getHeight()/2);
            stage.addActor(group);
        } else {
            group.setPosition(distance, 0);
            parent.getGroup().addActor(group);
            group.addAction(Actions.forever(new SatelliteAction(0, 2*MathUtils.PI, revolutionDuration, distance)));
        }
    }
}
