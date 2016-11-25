package com.kenji.dungeon.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kenji.dungeon.Utility;
import com.kenji.dungeon.components.Components;
import com.kenji.dungeon.components.MovementComponent;
import com.kenji.dungeon.components.PositionComponent;

public class MovementSystem extends IteratingSystem {

	private float minDistance = 0.01f;
	private float speed = 15;

	private Vector2 start, end;

	public MovementSystem() {
		super(Family.all(MovementComponent.class, PositionComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float delta) {
		PositionComponent pos = Components.instance.position.get(entity);
		MovementComponent move = Components.instance.velocity.get(entity);

		start = new Vector2(pos.getX(), pos.getY());

		if (!move.isMoving()) {
			end = new Vector2(pos.getX() + move.getX(), pos.getY() + move.getY());
			move.setMoving(true);
		}
		
		if (Utility.instance.collision.overlaps(new Rectangle(end.x, end.y, pos.getWidth(), pos.getHeight()))) {
			entity.remove(MovementComponent.class);
			return;
		}
		
		Vector2 result = start.lerp(end, Math.max(speed * delta, 0.1f));

		pos.setX(result.x);
		pos.setY(result.y);

		if (start.dst(end) <= minDistance) {
			pos.set(end);
			if (move.isStopped()) {
				move.setMoving(false);
				entity.remove(MovementComponent.class);
			}else{
				end = new Vector2(pos.getX() + move.getX(), pos.getY() + move.getY());
			}
			
		}

	}
}
