package com.kenji.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kenji.dungeon.Main;

public class EmptyScreen extends BaseScreen {
	public EmptyScreen(Main main) {
		super(main);
		// TODO Auto-generated constructor stub
	}

	private SpriteBatch batch;
	private ParticleEffect effect;

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		effect.draw(batch, delta);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		batch = new SpriteBatch();

		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("fire.p"), Gdx.files.internal(""));
		effect.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
		effect.start();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
		batch.dispose();
		effect.dispose();
	}

}
