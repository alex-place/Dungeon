package com.kenji.dungeon.components;

import com.badlogic.ashley.core.ComponentMapper;

public class Components {

	private Components() {
	}

	public static Components instance = new Components();

	public final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
	public final ComponentMapper<RenderComponent> render = ComponentMapper.getFor(RenderComponent.class);
	public final ComponentMapper<MovementComponent> velocity = ComponentMapper.getFor(MovementComponent.class);
	public final ComponentMapper<ParticleComponent> particle = ComponentMapper.getFor(ParticleComponent.class);
	public final ComponentMapper<CameraComponent> camera = ComponentMapper.getFor(CameraComponent.class);
}
