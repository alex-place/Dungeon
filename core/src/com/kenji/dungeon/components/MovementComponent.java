package com.kenji.dungeon.components;

import com.badlogic.ashley.core.Component;

public class MovementComponent implements Component {

	private float x, y, elapsed, lifetime;

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

	public float getElapsed() {
		return elapsed;
	}

	public void setElapsed(float elapsed) {
		this.elapsed = elapsed;
	}

	public float getLifetime() {
		return lifetime;
	}

	public void setLifetime(float lifetime) {
		this.lifetime = lifetime;
	}

	public void reset() {
		x = 0;
		y = 0;
		elapsed = 0;
	}

}
