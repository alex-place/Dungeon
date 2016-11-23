package com.kenji.dungeon.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kenji.dungeon.components.Components;
import com.kenji.dungeon.components.ParticleComponent;
import com.kenji.dungeon.components.PositionComponent;

public class ParticleSystem extends IteratingSystem {

	private SpriteBatch batch;
	private ParticleComponent par;

	public ParticleSystem(SpriteBatch batch) {
		super(Family.all(ParticleComponent.class).get());
		this.batch = batch;
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		par = Components.instance.particle.get(entity);
		par.getEffect().draw(batch, deltaTime);

		if (par.getEffect().isComplete()) {
			par.getEffect().free();
		}

	}

	@Override
	public void update(float deltaTime) {
		preProccess();
		super.update(deltaTime);
		postProcess();
	}

	private void preProccess() {
	}

	private void postProcess() {

	}

}
