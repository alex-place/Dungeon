package com.kenji.dungeon;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.kenji.dungeon.components.PositionComponent;

public class Utility {

	private Utility() {
	}

	public static Utility instance = new Utility();

	public void init(TiledMap map) {
		collision = new CollisionUtility(map);
	}

	public CollisionUtility collision;

	public class CollisionUtility {

		private MapLayer collisionObjectLayer;
		private TiledMap map;
		private String objectLayerId = "collision";

		public CollisionUtility(TiledMap map) {
			this.map = map;
			collisionObjectLayer = map.getLayers().get(objectLayerId);
		}

		private boolean overlaps(RectangleMapObject object, Rectangle rectangle) {
			return Intersector.overlaps(object.getRectangle(), rectangle);
		}

		private Rectangle scaleToWorldUnits(Rectangle rec) {
			float ratio = 1f / 16f;
			return new Rectangle(rec.x * ratio, rec.y * ratio, rec.width * ratio, rec.height * ratio);
		}

		public boolean overlaps(Rectangle pos) {
			boolean result = false;
			MapObjects objects = collisionObjectLayer.getObjects();
			for (RectangleMapObject rectangleObject : objects.getByType(RectangleMapObject.class)) {
				Rectangle rec = scaleToWorldUnits(rectangleObject.getRectangle());
				if (rec.overlaps(pos)) {
					result = true;
				}
			}
			return result;
		}

	}

}
