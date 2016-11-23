package com.kenji.dungeon;

import com.badlogic.gdx.Game;
import com.kenji.dungeon.screens.BaseScreen;
import com.kenji.dungeon.screens.LoadingScreen;

public class Main extends Game{

	@Override
	public void create() {
		super.setScreen(new LoadingScreen(this));
	}
	
	public void setScreen(BaseScreen screen){
		super.setScreen(screen);
	}
	
}