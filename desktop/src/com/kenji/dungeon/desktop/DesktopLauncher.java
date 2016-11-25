package com.kenji.dungeon.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tiledmappacker.TiledMapPacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.kenji.dungeon.Main;

public class DesktopLauncher {

	private static boolean rebuildAtlas = false;
	private static boolean rebuildMap = true;

	public static void main(String[] arg) {

		if (rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			TexturePacker.process(settings, "images", "../android/assets", "texpack");
		}

		if (rebuildMap) {
			String path = "maps";
			String output = "../android/assets";
			String verboseOpt = "-v";

			String[] args = { path, output, verboseOpt };
			try {
				TiledMapPacker.main(args);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Closing...");
		System.exit(0);

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		int scale = 1;
		config.width = 1600 * scale;
		config.height = 900 * scale;
		new LwjglApplication(new Main(), config);
	}
}
