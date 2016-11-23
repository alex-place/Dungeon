package com.kenji.dungeon.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Interpolation;
import com.kenji.dungeon.Manager;
import com.kenji.dungeon.components.Components;
import com.kenji.dungeon.components.PositionComponent;
import com.kenji.dungeon.components.MovementComponent;
import com.kenji.dungeon.input.SimpleDirectionGestureDetector.DirectionListener;

public class SimpleDirectionListener implements DirectionListener {

	private static final float speed = 1f;
	Entity entity;
	PositionComponent pos;

	Interpolation interpol;
	float newPos;
	boolean moving = false;
	private MovementComponent vel;

	public SimpleDirectionListener(Entity entity) {
		this.entity = entity;
		pos = Components.instance.position.get(entity);
		vel = Components.instance.velocity.get(entity);
	}

	@Override
	public void onLeft() {
		vel.setX(1);
		vel.setY(0);
	}

	@Override
	public void onRight() {

	}

	@Override
	public void onUp() {

	}

	@Override
	public void onDown() {

	}

}
