package com.kenji.dungeon.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kenji.dungeon.components.Components;
import com.kenji.dungeon.components.ParticleComponent;
import com.kenji.dungeon.components.PositionComponent;

public class ParticleSystem extends IteratingSystem {

	private SpriteBatch batch;
	private PositionComponent pos;
	private ParticleComponent par;

	public ParticleSystem(SpriteBatch batch) {
		super(Family.all(ParticleComponent.class, PositionComponent.class).get());
		this.batch = batch;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		pos = Components.instance.position.get(entity);
		par = Components.instance.particle.get(entity);
		par.getEffect().draw(batch, deltaTime);
	}

}
