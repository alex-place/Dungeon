package com.kenji.dungeon;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.kenji.dungeon.components.MovementComponent;
import com.kenji.dungeon.components.ParticleComponent;
import com.kenji.dungeon.components.PositionComponent;
import com.kenji.dungeon.components.RenderComponent;

public class EntityFactory {

	private EntityFactory() {
	}

	public static EntityFactory instance = new EntityFactory();
	
	
	private ParticleEffectPool fire;
	
	public void init(){
		ParticleEffect effect = new ParticleEffect();
		effect.load(Gdx.files.internal("fire.p"), Gdx.files.internal(""));
		fire = new ParticleEffectPool(effect, 2, 10);
	}

	private Entity createEntity() {
		return Manager.instance.getEngine().createEntity();
	}

	private Entity createEntity(Component... components) {
		Entity e = createEntity();
		for (Component c : components) {
			if (c != null) {
				e.add(c);
			}
		}
		return e;
	}

	public Entity createNakedMan(float x, float y) {
		PositionComponent p = createComponent(PositionComponent.class);
		p.setX(x);
		p.setY(y);
		p.setZ(0);
		p.setWidth(1);
		p.setHeight(1);
		RenderComponent r = createComponent(RenderComponent.class);
		r.setRegion(Assets.instance.nakedMan.reg);
		

		return createEntity(p, r);
	}

	public <T extends Component> T createComponent(Class<T> componentType) {
		return Manager.instance.getEngine().createComponent(componentType);
	}

	public Entity createLight(float x, float y) {
		PositionComponent pos = createComponent(PositionComponent.class);
		pos.setX(x);
		pos.setY(y);
		pos.setZ(0);
		pos.setWidth(1);
		pos.setHeight(1);

		ParticleComponent par = createComponent(ParticleComponent.class);
		PooledEffect effect = fire.obtain();
		effect.setPosition(x, y);
		effect.start();
		par.setEffect(effect);
		
		return createEntity(pos, par);
	}

}
