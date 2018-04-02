package com.libjam.neonrain.vabrant.application;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.libjam.neonrain.vabrant.application.VBCamera.ShakeType;

public class VBCameraRenderer {
	
	public void draw(VBCamera camera, VBScreen screen, Texture texture, float x, float y, float width, float height, boolean drawToWorld, ShakeType shake) {
		draw(camera, screen, texture, x, y, 0, 0, width, height, 1, 1, 0, drawToWorld, shake);
	}
	
	public void draw(VBCamera camera, VBScreen screen, Texture texture, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, boolean drawToWorld, ShakeType shake) {
		float worldX = 0;
		float worldY = 0;
		float worldZoom = 1;
		float shakeX = 0;
		float shakeY = 0;
		float shakeAngle = 0;
		float screenX = 0;
		float screenY = 0;
		
		if(screen != null) {
			screenX = screen.getMoveComponent().getX();
			screenY = screen.getMoveComponent().getY();
		}
		
		if(drawToWorld) {
			worldX = camera.getWorldCamera().getX();
			worldY = camera.getWorldCamera().getY();
			worldZoom = camera.getWorldCamera().getZoom();
		}
		
		switch(shake) {
			case BIG:
				if(!camera.isBigShakeShaking()) break;
			case ALL:
				shakeX = camera.getShakeX();
				shakeY = camera.getShakeY();
				shakeAngle = camera.getShakeAngle();
				break;
		}
		
		camera.getBatch().draw(texture, x + worldX + shakeX + screenX, y + worldY + shakeY + screenY, originX, originY, width, height, scaleX * worldZoom, scaleY * worldZoom, rotation + shakeAngle, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
	}
	
	public void draw(VBCamera camera, VBScreen screen, TextureRegion region, float x, float y, float width, float height, boolean drawToWorld, ShakeType shake) {
		draw(camera, screen, region, x, y, 0, 0, width, height, 1, 1, 0, drawToWorld, shake);
	}
	
	public void draw(VBCamera camera, VBScreen screen, TextureRegion region, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, boolean drawToWorld, ShakeType shake) {
		float worldX = 0;
		float worldY = 0;
		float worldZoom = 1;
		float shakeX = 0;
		float shakeY = 0;
		float shakeAngle = 0;
		float screenX = 0;
		float screenY = 0;
		
		if(screen != null) {
			screenX = screen.getMoveComponent().getX();
			screenY = screen.getMoveComponent().getY();
		}
		
		if(drawToWorld) {
			worldX = camera.getWorldCamera().getX();
			worldY = camera.getWorldCamera().getY();
			worldZoom = camera.getWorldCamera().getZoom();
		}
		
		switch(shake) {
			case BIG:
				if(!camera.isBigShakeShaking()) break;
			case ALL:
				shakeX = camera.getShakeX();
				shakeY = camera.getShakeY();
				shakeAngle = camera.getShakeAngle();
				break;
		}
		
		camera.getBatch().draw(region, x + worldX + shakeX + screenX, y + worldY + shakeY + screenY, originX, originY, width, height, scaleX * worldZoom, scaleY * worldZoom, rotation + shakeAngle);
	}
	
	public void draw(VBCamera camera, VBScreen screen, NinePatch patch, float x, float y, float width, float height, boolean drawToWorld, ShakeType shake) {
		draw(camera, screen, patch, x, y, 0, 0, width, height, 1, 1, 0, drawToWorld, shake);
	}
	
	public void draw(VBCamera camera, VBScreen screen, NinePatch patch, float x, float y, float originX, float originY, float width, float height, float scaleX, float scaleY, float rotation, boolean drawToWorld, ShakeType shake) {
		float worldX = 0;
		float worldY = 0;
		float worldZoom = 1;
		float shakeX = 0;
		float shakeY = 0;
		float shakeAngle = 0;
		float screenX = 0;
		float screenY = 0;
		
		if(screen != null) {
			screenX = screen.getMoveComponent().getX();
			screenY = screen.getMoveComponent().getY();
		}
		
		if(drawToWorld) {
			worldX = camera.getWorldCamera().getX();
			worldY = camera.getWorldCamera().getY();
			worldZoom = camera.getWorldCamera().getZoom();
		}
		
		switch(shake) {
			case BIG:
				if(!camera.isBigShakeShaking()) break;
			case ALL:
				shakeX = camera.getShakeX();
				shakeY = camera.getShakeY();
				shakeAngle = camera.getShakeAngle();
				break;
		}
		
		patch.draw(camera.getBatch(), x + worldX + shakeX + screenX, y + worldY + shakeY + screenY, originX, originY, width, height, scaleX * worldZoom, scaleY * worldZoom, rotation + shakeAngle);
	}
	
	public void draw(VBCamera camera, VBScreen screen, BitmapFont font, GlyphLayout layout, float x, float y, boolean drawToWorld, ShakeType shake){
		float worldX = 0;
		float worldY = 0;
		float shakeX = 0;
		float shakeY = 0;
		float screenX = 0;
		float screenY = 0;
		
		if(screen != null) {
			screenX = screen.getMoveComponent().getX();
			screenY = screen.getMoveComponent().getY();
		}
		
		if(drawToWorld) {
			worldX = camera.getWorldCamera().getX();
			worldY = camera.getWorldCamera().getY();
		}
		
		switch(shake) {
			case BIG:
				if(!camera.isBigShakeShaking()) break;
			case ALL:
				shakeX = camera.getShakeX();
				shakeY = camera.getShakeY();
				break;
		}
		
		font.draw(camera.getBatch(), layout, x + worldX + shakeX + screenX, y + worldY + shakeY + screenY);
	}
	
	public void draw(VBCamera camera, VBScreen screen, BitmapFont font, float x, float y, String str, boolean drawToWorld, ShakeType shake) {
		float worldX = 0;
		float worldY = 0;
		float shakeX = 0;
		float shakeY = 0;
		float screenX = 0;
		float screenY = 0;
		
		if(screen != null) {
			screenX = screen.getMoveComponent().getX();
			screenY = screen.getMoveComponent().getY();
		}
		
		if(drawToWorld) {
			worldX = camera.getWorldCamera().getX();
			worldY = camera.getWorldCamera().getY();
		}
		
		switch(shake) {
			case BIG:
				if(!camera.isBigShakeShaking()) break;
			case ALL:
				shakeX = camera.getShakeX();
				shakeY = camera.getShakeY();
				break;
		}
		
		font.draw(camera.getBatch(), str, x + worldX + shakeX + screenX, y + worldY + shakeY + screenY);
	}


}
