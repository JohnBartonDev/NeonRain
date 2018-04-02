package com.libjam.neonrain.vabrant.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph;
import com.badlogic.gdx.utils.ObjectMap;

public class VBFont {
	
	private ObjectMap<Character, VBGlyph> _glyphs = new ObjectMap<>();
	
	public VBFont(BitmapFont font) {
		Glyph[][] gg = font.getData().glyphs;
		
		
		for(int i = 0; i < gg.length; i++) {
			if(gg[i] == null) continue;
			for(int j = 0; j < gg[i].length; j++) {
				if(gg[i][j] == null) continue;
				System.out.println(gg[i][j].id + ":" + gg[i][j].toString());
			}
		}
		
//		System.out.println(g[0].length);
	}
	
	public VBFont(BitmapFont font, String chars) {
		for(int i = 0; i < chars.length(); i++) {
		}
	}
	
	public void addGlyph(Glyph glyph) {
		
	}

}
