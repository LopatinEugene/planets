package com.lopatineugene.planets;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameManager {
    Stage stage;

    public Stage getStage() {
        return stage;
    }

    public GameManager(Stage stage) {
        isPaused = false;
        this.stage = stage;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    private boolean isPaused;

}
