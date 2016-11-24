package com.kenji.dungeon.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.kenji.dungeon.components.Components;
import com.kenji.dungeon.components.MovementComponent;
import com.kenji.dungeon.components.PositionComponent;

public class MovementSystem extends IteratingSystem {

	private float minDistance = 0.01f;
	private float speed = 2;

	public MovementSystem() {
		super(Family.all(MovementComponent.class, PositionComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float delta) {
		PositionComponent pos = Components.instance.position.get(entity);
		MovementComponent move = Components.instance.velocity.get(entity);

		Vector2 start = new Vector2(pos.getX(), pos.getY());
		Vector2 end = new Vector2(move.getX(), move.getY());

		Vector2 result = start.lerp(end, Math.max(speed * delta, 0.15f));

		pos.setX(result.x);
		pos.setY(result.y);

		if (start.dst(end) <= minDistance) {
			pos.set(end);
			entity.remove(MovementComponent.class);
		}
	}
}
