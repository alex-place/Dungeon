package com.kenji.dungeon.systems;

import java.util.Comparator;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kenji.dungeon.components.Components;
import com.kenji.dungeon.components.PositionComponent;
import com.kenji.dungeon.components.RenderComponent;

public class RenderingSystem extends SortedIteratingSystem {
	private ComponentMapper<RenderComponent> rm;
	private SpriteBatch batch;
	private static ComponentMapper<PositionComponent> pm;

	@SuppressWarnings("unchecked")
	public RenderingSystem(SpriteBatch batch) {
		super(Family.all(RenderComponent.class, PositionComponent.class).get(), new ZComparator());
		this.batch = batch;
		pm = Components.instance.position;
		rm = Components.instance.render;
	}

	protected void processEntity(Entity entity, float deltaTime) {
		RenderComponent r = rm.get(entity);
		PositionComponent p = pm.get(entity);
		batch.draw(r.getRegion(), p.getX(), p.getY(), p.getWidth(), p.getHeight());

	}

	private static class ZComparator implements Comparator<Entity> {
		@Override
		public int compare(Entity e1, Entity e2) {
			return (int) Math.signum(pm.get(e1).getZ() - pm.get(e2).getZ());
		}
	}
}
