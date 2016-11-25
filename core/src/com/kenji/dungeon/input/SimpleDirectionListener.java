package com.kenji.dungeon.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Rectangle;
import com.kenji.dungeon.Manager;
import com.kenji.dungeon.Utility;
import com.kenji.dungeon.components.Components;
import com.kenji.dungeon.components.MovementComponent;
import com.kenji.dungeon.components.PositionComponent;
import com.kenji.dungeon.input.SimpleDirectionGestureDetector.DirectionListener;

public class SimpleDirectionListener implements DirectionListener {

	private static final float speed = 1f;
	private Entity entity;
	private PositionComponent pos;
	private MovementComponent vel;

	private Rectangle newPos;

	private boolean stopped = true;

	public SimpleDirectionListener(Entity entity) {
		this.entity = entity;
		pos = Components.instance.position.get(entity);
		vel = Components.instance.velocity.get(entity);
		newPos = new Rectangle(pos.getX(), pos.getY(), 1, 1);
	}

	@Override
	public void onLeft() {
		newPos.setX(pos.getX() - 1);
		newPos.setY(pos.getY());
		createMoveComponent();
		if (stopped) {
			if (!vel.isMoving()) {
				vel.setX(-speed);
				vel.setY(0);
				stopped = false;
			}
		}
	}

	@Override
	public void onRight() {
		newPos.setX(pos.getX() + 1);
		newPos.setY(pos.getY());
		createMoveComponent();
		if (stopped) {
			if (!vel.isMoving()) {
				vel.setX(speed);
				vel.setY(0);
				stopped = false;

			}
		}
	}

	@Override
	public void onUp() {
		newPos.setX(pos.getX());
		newPos.setY(pos.getY() + 1);
		createMoveComponent();
		if (stopped) {
			if (!vel.isMoving()) {
				vel.setX(0);
				vel.setY(speed);
				stopped = false;

			}
		}
	}

	@Override
	public void onDown() {
		newPos.setX(pos.getX());
		newPos.setY(pos.getY() - 1);
		createMoveComponent();
		if (stopped) {
			if (!vel.isMoving()) {
				vel.setX(0);
				vel.setY(-speed);
				stopped = false;

			}
		}
	}

	public void createMoveComponent() {
		if (vel == null || entity.getComponent(MovementComponent.class) == null) {
			vel = Manager.instance.getEngine().createComponent(MovementComponent.class);
			vel.reset();
			entity.add(vel);
		}
	}

	@Override
	public void stop() {
		vel.setStopped(true);
		stopped = true;
	}
}
