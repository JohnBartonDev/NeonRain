package com.libjam.neonrain.vabrant.util;

import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class VBAssetManager extends AssetManager{
	
	public VBAssetManager() {}
	
	public synchronized void loadTexture(String s, AssetLoaderParameters<Texture> parameter) {
		load(s, Texture.class, parameter);
	}
	
	public synchronized Texture getTexture(String s) {
		return get(s, Texture.class);
	}
	
	public synchronized void loadAtlas(String s, AssetLoaderParameters<TextureAtlas> parameter) {
		load(s, TextureAtlas.class, parameter);
	}
	
	public synchronized TextureAtlas getAtlas(String s) {
		return get(s, TextureAtlas.class);
	}
	
	public synchronized void loadFont(String s, AssetLoaderParameters<BitmapFont> parameter) {
		load(s, BitmapFont.class, parameter);
	}
	
	public synchronized BitmapFont getFont(String s) {
		return get(s, BitmapFont.class);
	}

}
