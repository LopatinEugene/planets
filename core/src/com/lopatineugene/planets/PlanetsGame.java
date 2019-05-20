package com.lopatineugene.planets;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.lopatineugene.planets.planetoids.Planetoid;
import com.lopatineugene.planets.util.ClickListenerFactory;

public class PlanetsGame extends ApplicationAdapter {
	private Stage stage;
	private Skin skin;
	private GameManager gameManager;
	private TextureAtlas textureAtlas;
	private TextureRegion textureRegion;
	Planetoid sun;

	@Override
	public void create() {
		skin = new Skin(Gdx.files.internal("uiskin.json"));

		textureAtlas = new TextureAtlas("Spritesheets/PlanetSprites.atlas");
		textureRegion = textureAtlas.findRegion("planetoid");

		stage = new Stage(new FitViewport(640,480));
		Gdx.input.setInputProcessor(stage);

		gameManager = new GameManager(stage);

		final TextButton button = new TextButton("pause", skin, "default");
		button.setSize(100, 40);
		button.setPosition(10, 10);
		button.addListener(ClickListenerFactory.clicked(this::clicked));

		stage.addActor(button);

		sun = new Planetoid(gameManager, null, textureRegion, Color.GOLD, 64, 0, 0);
		Planetoid planet1 = new Planetoid(gameManager, sun, textureRegion, Color.CYAN, 32, 96.0f, 2f);
		Planetoid planet2 = new Planetoid(gameManager, sun, textureRegion, Color.LIME, 32, 192.0f, 3f);
		Planetoid satellite = new Planetoid(gameManager, planet2, textureRegion, Color.MAGENTA, 16, 48.0f, 1f);
	}

	private void clicked(InputEvent event, float x, float y) {
		gameManager.setPaused(!gameManager.isPaused());
		((TextButton)event.getListenerActor()).setText(gameManager.isPaused() ? "resume" : "pause");
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (!gameManager.isPaused())
			stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		textureAtlas.dispose();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height);
	}
}
