package com.kenji.dungeon.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;

public class MovementComponent implements Component {

	private float x, y;
	private boolean moving;

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void set(Vector2 location){
		setX(location.x);
		setY(location.y);
	}

	public void reset() {
		x = 0;
		y = 0;
	}

	public boolean isMoving() {
		return moving;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
