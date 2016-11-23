package com.kenji.dungeon.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;

public class ParticleComponent implements Component{

	private PooledEffect effect;

	public PooledEffect getEffect() {
		return effect;
	}

	public void setEffect(PooledEffect effect) {
		this.effect = effect;
	}

}
