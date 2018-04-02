package com.libjam.neonrain.vabrant.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.libjam.neonrain.vabrant.action.actions.VBDefaultActionable;
import com.libjam.neonrain.vabrant.application.VBCamera;
import com.libjam.neonrain.vabrant.application.VBCamera.ShakeType;
import com.libjam.neonrain.vabrant.application.VBScreen;

public class VBGlyph extends VBDefaultActionable{
	
	private boolean _useInt;
	private float _staticX;
	private float _staticY;
	private final float _width;
	private final float _height;
	private char _char;
	private TextureRegion _region;
	
	public VBGlyph(Texture texture, Glyph glyph) {
		_width = glyph.width;
		_height = glyph.height;
		_char = (char)glyph.id;
		_region = new TextureRegion(texture, glyph.srcX, glyph.srcY, glyph.width, glyph.height);
	}
	
	public VBGlyph(char c) {
		_width = 0;
		_height = 0;
		_char = c;
	}
	
	public char getChar() {
		return _char;
	}
	
	public float getWidth() {
		return _width;
	}
	
	public float getHeight() {
		return _height;
	}
	
	public void draw(VBScreen screen, VBCamera camera, boolean drawToWorld, ShakeType type) {
		Color old = camera.getBatch().getColor();
		camera.getBatch().setColor(getColor());
		camera.drawRegion(screen, _region, getX(), getY(), _width/2, _height/2, _width, _height, getScaleY() * 0.75f, getScaleX() * 0.75f, getRotation(), drawToWorld, type);
		camera.getBatch().setColor(old);
	}
	
	

}
