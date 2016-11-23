package com.kenji.dungeon.screens;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.kenji.dungeon.Assets;
import com.kenji.dungeon.Constants;
import com.kenji.dungeon.EntityFactory;
import com.kenji.dungeon.Main;
import com.kenji.dungeon.Manager;
import com.kenji.dungeon.input.MainDungeonInput;
import com.kenji.dungeon.input.SimpleDirectionGestureDetector;
import com.kenji.dungeon.input.SimpleDirectionListener;
import com.kenji.dungeon.systems.MovementSystem;
import com.kenji.dungeon.systems.ParticleSystem;
import com.kenji.dungeon.systems.RenderingSystem;

public class MainDungeonScreen extends BaseScreen {

	public MainDungeonScreen(Main main) {
		super(main);
	}

	private SpriteBatch batch;
	private OrthographicCamera camera;
	private BitmapFont font;
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer tiledMapRenderer;

	@Override
	public void show() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 16, 9);
		camera.update();
		tiledMap = (TiledMap) Assets.instance.getAsset(Constants.FIRST_DUNGEON);
		tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1 / 16f, batch);

		Manager.instance.init();
		EntityFactory.instance.init();
		Manager.instance.getEngine().addSystem(new RenderingSystem(batch));
		Manager.instance.getEngine().addSystem(new MovementSystem());
		Manager.instance.getEngine().addSystem(new ParticleSystem(batch));

		Entity nakedMan = EntityFactory.instance.createNakedMan(1, 1);
		Manager.instance.getEngine().addEntity(nakedMan);
		Entity fire = EntityFactory.instance.createLight(2.25f, 0.5f);
		Manager.instance.getEngine().addEntity(fire);

		MainDungeonInput input = new MainDungeonInput();
		SimpleDirectionGestureDetector detector = new SimpleDirectionGestureDetector(
				new SimpleDirectionListener(nakedMan));

		InputMultiplexer multi = new InputMultiplexer(input, detector);
		Gdx.input.setInputProcessor(multi);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();

		batch.begin();
		Manager.instance.getEngine().update(delta);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {
		batch.dispose();
		tiledMap.dispose();
		tiledMapRenderer.dispose();
		font.dispose();

	}

}
