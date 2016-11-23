package com.kenji.dungeon.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.kenji.dungeon.components.Components;
import com.kenji.dungeon.components.MovementComponent;
import com.kenji.dungeon.components.PositionComponent;

public class MovementSystem extends IteratingSystem {

	public MovementSystem() {
		super(Family.all(MovementComponent.class, PositionComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float delta) {
		PositionComponent pos = Components.instance.position.get(entity);
		MovementComponent move = Components.instance.velocity.get(entity);

		pos.setX(pos.getX() + move.getX());
		pos.setY(pos.getY() + move.getY());
		move.reset();

		// move.setElapsed(move.getElapsed() + delta);
		// if (move.getElapsed() >= move.getLifetime()) {
		// move.reset();
		// } else {
		// pos.setX(pos.getX() + (move.getX() * delta));
		// }
	}

}
