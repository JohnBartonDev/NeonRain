package com.libjam.neonrain.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.libjam.neonrain.NeonRain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		
		config.width = 960;
		config.height = 640;
		config.resizable = false;
		
		new LwjglApplication(new NeonRain(), config);
		
//		packTextures();
	}
	
	public static void packTextures() {
		Settings settings = new Settings();
		settings.alias = false;
		
		TexturePacker.process(settings, "packImages", "packImages", "game");
	}
}
