package com.libjam.neonrain.vabrant.action.actions.move;

import com.badlogic.gdx.math.MathUtils;
import com.libjam.neonrain.vabrant.action.backend.VBBackEndActionable;
import com.libjam.neonrain.vabrant.action.components.VBActionableComponent;

public class VBBaseMoveAction<A extends VBBackEndActionable<VBMovable>, L extends VBBaseMoveAction> extends VBActionableComponent<A, VBMovable, L>{
	
	private boolean _moveX;
	private boolean _moveY;

	public VBBaseMoveAction(A base) {
		super(base);
	}
	
	public void moveBy(VBMovable move, float angle, float distance) {
		angle %= 360;
		float x = distance * MathUtils.cosDeg(angle);
		float y = distance * MathUtils.sinDeg(angle);
		setupMoveX(move, move.getX() + x);
		setupMoveY(move, move.getY() + y);
		add(move);
	}
	
	public void moveTo(VBMovable move, float angle, float distance) {
		angle %= 360;
		float x = distance * MathUtils.cosDeg(angle);
		float y = distance * MathUtils.sinDeg(angle);
		setupMoveX(move, x);
		setupMoveY(move, y);
		add(move);
	}
	
	public void moveXBy(VBMovable move, float amount) {
		setupMoveX(move, move.getX() + amount);
		add(move);
	}
	
	public void moveXTo(VBMovable move, float end) {
		setupMoveX(move, end);
		add(move);
	}
	
	public void moveYBy(VBMovable move, float amount) {
		setupMoveY(move, move.getY() + amount);
		add(move);
	}
	
	public void moveYTo(VBMovable move, float end) {
		setupMoveY(move, end);
		add(move);
	}
	
	public void setupMoveY(VBMovable move, float end) {
		_moveY = true;
		move.setStartY(move.getY());
		move.setEndY(end);
	}

	public void setupMoveX(VBMovable move, float end) {
		_moveX = true;
		move.setStartX(move.getX());
		move.setEndX(end);
	}
	
	public boolean moveX() {
		return _moveX;
	}
	
	public boolean moveY() {
		return _moveY;
	}
	
	@Override
	public void percent(VBMovable type, float percent) {
		if(_moveX) {
			float x = MathUtils.lerp(type.getStartX(), type.getEndX(), percent);
			type.setX(x);
		}
		
		if(_moveY) {
			float y = MathUtils.lerp(type.getStartY(), type.getEndY(), percent);
			type.setY(y);
		}
	}

	@Override
	public void itemOver(VBMovable type) {
		if(getBackEndAction().reverseBackToStart()) {
			if(_moveX) type.setX(type.getStartX());
			if(_moveY) type.setY(type.getStartY());
		}
		else {
			if(_moveX) type.setX(type.getEndX());
			if(_moveY) type.setY(type.getEndY());
		}
	}
	
	@Override
	public void reset() {
		super.reset();
		_moveX = false;
		_moveY = false;
	}

}
