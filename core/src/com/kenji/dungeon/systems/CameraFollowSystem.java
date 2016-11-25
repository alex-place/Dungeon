package com.kenji.dungeon.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.kenji.dungeon.components.CameraComponent;
import com.kenji.dungeon.components.Components;
import com.kenji.dungeon.components.ParentComponent;
import com.kenji.dungeon.components.PositionComponent;

public class CameraFollowSystem extends IteratingSystem {

	private float speed = 0.1f;
	private float minDistance = 0.001f;

	public CameraFollowSystem() {
		super(Family.all(CameraComponent.class).get());
	};

	@Override
	protected void processEntity(Entity entity, float delta) {
		CameraComponent camCom = Components.instance.camera.get(entity);
		Camera cam = camCom.getCamera();

		PositionComponent pos = entity.getComponent(ParentComponent.class).getParent()
				.getComponent(PositionComponent.class);

		if (pos != null) {

			Vector2 start = new Vector2(cam.position.x, cam.position.y);
			Vector2 end = new Vector2(pos.getX(), pos.getY());

			if (start.dst(end) < minDistance) {
				cam.position.set(pos.getX(), pos.getY(), 0);
			}else{
				Vector2 result = start.lerp(end, Math.max(speed, 0.01f));
				cam.position.set(result, 0);
			}



		}

		cam.update();
	}

}
