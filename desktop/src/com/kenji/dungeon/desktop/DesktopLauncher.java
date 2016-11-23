package com.kenji.dungeon.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.kenji.dungeon.Main;

public class DesktopLauncher {

	private static boolean rebuildAtlas = true;

	public static void main(String[] arg) {

		if (rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			TexturePacker.process(settings, "images", "../android/assets", "texpack");
		}

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		int scale = 1;
		config.width = 1600 * scale;
		config.height = 900 * scale;
		new LwjglApplication(new Main(), config);
	}
}
