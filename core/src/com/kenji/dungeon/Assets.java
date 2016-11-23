package com.kenji.dungeon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Assets {

	private AssetManager manager;
	private TextureAtlas atlas;

	public static Assets instance = new Assets();

	public AssetNakedMan nakedMan;
	public AssetParticle particles;

	public boolean loaded = false;

	private Assets() {
	}

	public void init() {
		manager = new AssetManager();
		manager.setLoader(TiledMap.class, new TmxMapLoader());
		manager.load(Constants.FIRST_DUNGEON, TiledMap.class);
		manager.load(Constants.SPRITE_ATLAS, TextureAtlas.class);
		ParticleEffectLoader parLoader = new ParticleEffectLoader(new InternalFileHandleResolver());
		manager.setLoader(ParticleEffect.class, parLoader);
		ParticleEffectParameter parParam = new ParticleEffectParameter();
		
		parLoader.load(manager, Constants.PARTICLE_FIRE, Gdx.files.internal(""), parParam);
//		manager.load(Constants.PARTICLE_FIRE, ParticleEffect.class);
	}

	public void create() {
		atlas = manager.get(Constants.SPRITE_ATLAS);
		nakedMan = new AssetNakedMan(atlas);
		particles = new AssetParticle(atlas);
		loaded = true;
	}

	public boolean update() {
		return manager.update();
	}

	public float getProgress() {
		return manager.getProgress();
	}

	public Object getAsset(String asset) {
		if (manager.isLoaded(asset)) {
			return manager.get(asset);
		}
		return null;
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}

	public static class AssetNakedMan {
		public AtlasRegion reg;

		public AssetNakedMan(TextureAtlas atlas) {
			reg = atlas.findRegion("nakedhero");
		}
	}

	public static class AssetParticle {
		private AtlasRegion gradient;
		public ParticleEffect fire;

		public AssetParticle(TextureAtlas atlas) {
			gradient = atlas.findRegion("gradient");
			fire = (ParticleEffect) Assets.instance.getAsset(Constants.PARTICLE_FIRE);
		}
	}

}
