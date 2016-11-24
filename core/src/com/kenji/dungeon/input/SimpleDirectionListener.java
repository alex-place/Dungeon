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
	private MovementComponent vel;
	
	

	public SimpleDirectionListener(Entity entity) {
		this.entity = entity;
		pos = Components.instance.position.get(entity);
		vel = Components.instance.velocity.get(entity);
	}

	@Override
	public void onLeft() {
		createMovementComponent();
		if (!vel.isMoving()) {
			vel.setX(pos.getX() - speed);
			vel.setY(pos.getY());
			vel.setMoving(true);
		}
	}

	@Override
	public void onRight() {
		createMovementComponent();
		if (!vel.isMoving()) {
			vel.setX(pos.getX() + speed);
			vel.setY(pos.getY());
			vel.setMoving(true);
		}
	}

	@Override
	public void onUp() {
		createMovementComponent();
		if (!vel.isMoving()) {
			vel.setX(pos.getX());
			vel.setY(pos.getY() + speed);
			vel.setMoving(true);
		}
	}

	@Override
	public void onDown() {
		createMovementComponent();
		if (!vel.isMoving()) {
			vel.setX(pos.getX());
			vel.setY(pos.getY() - speed);
			vel.setMoving(true);
		}
	}

	public void createMovementComponent() {
		if (vel == null || entity.getComponent(MovementComponent.class) == null) {
			vel = Manager.instance.getEngine().createComponent(MovementComponent.class);
			vel.setMoving(false);
			entity.add(vel);
		}
	}
}
