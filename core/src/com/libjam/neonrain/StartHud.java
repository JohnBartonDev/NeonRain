package com.libjam.neonrain;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Pools;
import com.libjam.neonrain.vabrant.action.actions.VBActionListener;
import com.libjam.neonrain.vabrant.action.actions.VBDelayAction;
import com.libjam.neonrain.vabrant.action.actions.VBGroupAction;
import com.libjam.neonrain.vabrant.action.actions.color.VBDefaultColorable;
import com.libjam.neonrain.vabrant.action.actions.color.VBSingleColorAction;
import com.libjam.neonrain.vabrant.action.actions.move.VBDefaultMovable;
import com.libjam.neonrain.vabrant.action.actions.move.VBSingleMoveAction;
import com.libjam.neonrain.vabrant.application.VBCamera;
import com.libjam.neonrain.vabrant.application.VBScreen;
import com.libjam.neonrain.vabrant.application.VBCamera.ShakeType;
import com.libjam.neonrain.vabrant.util.VBUtils;

public class StartHud implements VBActionListener<VBGroupAction>{
	
	private int _readyOffset;
	private float _timer;
	private boolean _delay;
	private boolean _draw;
	private BitmapFont _font;
	private GlyphLayout _layout;
	private VBDefaultMovable _moveComponent;
	private VBDefaultColorable _colorComponent;
	private String _drawString = "";
	private StartHudListener _listener;
	
	public StartHud(GameScreen screen) {
		_listener = screen;
		_font = VBUtils.getFont(screen.getApplication().getAssetManager(), Constants.BUBBLE_FONT);
		_layout = new GlyphLayout();
		_moveComponent = new VBDefaultMovable();
		_colorComponent = new VBDefaultColorable();
		_colorComponent.setColor(Color.BLACK);
		_font.setFixedWidthGlyphs("readyst");
	}
	
	public void start() {
		_draw = true;
		_delay = true;
		resetText("ready");
		_readyOffset = 15;
	}
	
	public void resetText(String txt) {
		_moveComponent.setY(0);
		_colorComponent.getColor().a = 0f;
		_drawString = txt;
		_font.getData().setScale(0.5f);
		_layout.setText(_font, _drawString);
	}
	
	public void update(float delta) {
		if(_delay) {
			_timer += delta;
			if(_timer >= 1) {
				animate();
				_delay = false;
			}
		}
	}
	
	public void draw(VBScreen screen, VBCamera camera) {
		if(!_draw) return;
//		camera.drawBitmapFont(screen, _font, _layout, 480/2 - _layout.width/2 + _moveComponent.getX(), _moveComponent.getY(), true, ShakeType.NONE);
		_font.setColor(_colorComponent.getColor());
		_font.getData().setScale(0.5f);
		camera.drawBitmapFont(screen, _font, 480/2 - _layout.width/2 + 10, _moveComponent.getY(), _drawString, true, ShakeType.NONE);
		_font.setColor(Color.WHITE);
	}
	
	public void animate() {
		//move the text
		VBSingleMoveAction move = Pools.obtain(VBSingleMoveAction.class);
		move.set(0.5f, false, Interpolation.circleOut);
		move.moveYTo(_moveComponent, 200);
		
		//fade the text
		VBSingleColorAction color = Pools.obtain(VBSingleColorAction.class);
		color.set(0.5f, false, Interpolation.smooth2);
		color.fade(_colorComponent, 1f);
		
		//small delay so you have time to read the text
		VBDelayAction delay = Pools.obtain(VBDelayAction.class);
		delay.set(0.5f);
		
		VBGroupAction moveGroup = Pools.obtain(VBGroupAction.class);
		moveGroup.setGroup(0);
		moveGroup.add(move);
		moveGroup.add(color);
		
		VBGroupAction mainGroup = Pools.obtain(VBGroupAction.class);
		mainGroup.setSequence();
		mainGroup.add(moveGroup);
		mainGroup.add(delay);
		mainGroup.setListener(this);
		
		NeonRain.addAction(mainGroup);
	}
	
	@Override
	public void actionOver(VBGroupAction action) {
		if(_drawString.equals("set")) {
			_listener.startHudOver();
			_draw = false;
			return;
		}
		_readyOffset = 0;
		resetText("set");
		animate();
	}
	
	public interface StartHudListener{
		public void startHudOver();
	}

}
