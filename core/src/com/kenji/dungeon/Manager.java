package com.kenji.dungeon;

import com.badlogic.ashley.core.PooledEngine;

public class Manager {
	
	private PooledEngine engine;
	
	private Manager() {
	}

	public static Manager instance = new Manager();
	
	public void init(){
		engine = new PooledEngine();
	}
	
	public PooledEngine getEngine() {
		return engine;
	}
	
}
