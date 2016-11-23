package com.kenji.dungeon;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.kenji.dungeon.components.MovementComponent;
import com.kenji.dungeon.components.ParticleComponent;
import com.kenji.dungeon.components.PositionComponent;
import com.kenji.dungeon.components.RenderComponent;

public class EntityFactory {

	private EntityFactory() {
	}

	public static EntityFactory instance = new EntityFactory();

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
		MovementComponent m = createComponent(MovementComponent.class);
		m.setX(0);
		m.setY(0);
		m.setElapsed(0);
		m.setLifetime(10);

		return createEntity(p, r, m);
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
//		par.setEffect(Assets.instance.particles.fire);
		ParticleEffect effect = new ParticleEffect();
		effect.load(Gdx.files.internal("fire.p"), Assets.instance.getAtlas());
		return createEntity(pos);
	}

}
