package com.kenji.dungeon.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;

public class ParentComponent implements Component{

	private Entity parent;

	public Entity getParent() {
		return parent;
	}

	public void setParent(Entity parent) {
		this.parent = parent;
	}

}
