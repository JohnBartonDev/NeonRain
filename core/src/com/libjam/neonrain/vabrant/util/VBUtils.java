package com.libjam.neonrain.vabrant.util;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class VBUtils {
	
	public static float map(float value, float min1, float max1, float min2, float max2){
		return min2 + (max2 - min2) * ((value - min1) / (max1 - min1));
	}
	
	public static void loadTexture(AssetManager manager, String... names) {
		loadTexure(manager, null, names);
	}
	
	public static void loadTexure(AssetManager manager, AssetLoaderParameters parameter, String... names) {
		for(int i = 0; i < names.length; i++) {
			manager.load(names[i], Texture.class);
		}
	}
	
	public static Texture getTexture(AssetManager manager, String textureName) {
		return manager.get(textureName, Texture.class);
	}
	
	public static void loadAtlas(AssetManager manager, String... names) {
		for(int i = 0; i < names.length; i++) {
			manager.load(names[i], TextureAtlas.class);
		}
	}
	
	public static TextureAtlas getAtlas(AssetManager manager, String atlasName) {
		return manager.get(atlasName, TextureAtlas.class);
	}
	
	public static TextureRegion getRegion(AssetManager manager, String atlasName, String regionName) {
		return getAtlas(manager, atlasName).findRegion(regionName);
	}
	
	public static void loadFont(AssetManager manager, String... names) {
		for(int i = 0; i < names.length; i++) {
			manager.load(names[i], BitmapFont.class);
		}
	}
	
	public static BitmapFont getFont(AssetManager manager, String fontName) {
		return manager.get(fontName, BitmapFont.class);
	}
	

}
