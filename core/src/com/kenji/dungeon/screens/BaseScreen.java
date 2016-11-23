package com.kenji.dungeon.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.kenji.dungeon.Main;

public abstract class BaseScreen implements Screen {

	protected Main main;

	public BaseScreen(Main main) {
		this.main = main;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
	}

}