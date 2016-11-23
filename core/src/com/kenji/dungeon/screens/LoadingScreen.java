package com.kenji.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kenji.dungeon.Assets;
import com.kenji.dungeon.Main;

public class LoadingScreen extends BaseScreen {

	public LoadingScreen(Main main) {
		super(main);
	}

	private SpriteBatch batch;
	private BitmapFont font;
	private String progress;

	public void show() {
		Assets.instance.init();

		batch = new SpriteBatch();
		font = new BitmapFont();
		progress = Assets.instance.getProgress() + "% Loading...";
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		if (Assets.instance.update()) {
			Assets.instance.create();
			if (Assets.instance.loaded) {
				progress = "TOUCH TO CONTINUE";
				// All done loading!
				if (Gdx.input.isTouched()) {
					main.setScreen(new MainDungeonScreen(main));
				}
			}
		} else {
			progress = Assets.instance.getProgress() + "% Loading...";
		}

		batch.begin();
		font.draw(batch, progress, 200, 200);
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

	}

}
