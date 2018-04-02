package com.libjam.neonrain.vabrant.action.actions;

import com.badlogic.gdx.utils.Array;
import com.libjam.neonrain.vabrant.action.backend.VBMultiBackEndAction;
import com.libjam.neonrain.vabrant.action.components.VBActionComponent;

@SuppressWarnings("rawtypes")
public class VBGroupAction extends VBActionComponent<VBMultiBackEndAction<VBActionComponent>, VBActionComponent, VBGroupAction>{

	private boolean _isGroup = true;
	private int _index;
	private int _maxIndex;
	private float _timer;
	private float _offset;
	
	public VBGroupAction() {
		super(new VBMultiBackEndAction<>());
	}
	
	public void setGroup(float offset) {
		_isGroup = true;
		_offset = offset;
	}
	
	public void setSequence() {
		_isGroup = false;
	}
	
	public Array<VBActionComponent> getItems(){
		return getBackEndAction().getItems();
	}
	
	@Override
	protected void startAction() {
		Array<VBActionComponent> actions = getItems();
		
		_timer = 0;
		_index = 0;
		_maxIndex = actions.size;
		
		if(_isGroup) {
			for(int i = 0; i < actions.size; i++) {
				actions.get(i).start();
			}
		}
		else {
			actions.get(_index).start();
		}
	}
	
	@Override
	protected void endAction() {
		Array<VBActionComponent> actions = getItems();
		for(int i = 0; i < actions.size; i++) {
			actions.get(i).end();
		}
	}
	
	@Override
	public void reset() {
		super.reset();
		getBackEndAction().reset();
		_isGroup = true;
	}

	@Override
	protected boolean updateAction(float delta) {
		if(_isGroup) {
			return updateGroup(delta);
		}
		else {
			return updateSequence(delta);
		}
	}
	
	private boolean updateSequence(float delta) {
		Array<VBActionComponent> actions = getItems();
		if(!actions.get(_index).update(delta)) {
			if(++_index == _maxIndex) {
				_index = 0;
				return false;
			}
			actions.get(_index).start();
			actions.get(_index).update(delta);
		}
		return true;
	}
	
	private boolean updateGroup(float delta) {
		Array<VBActionComponent> actions = getItems();
		_timer += delta;
		
		boolean finished = true;
		for(int i = 0; i < actions.size; i++) {
			float time = _timer - (_offset * i);
			
			if(time < 0) {
				continue;
			}
			
			if(!actions.get(i).update(delta)) {
				if(i == actions.size - 1) {
					if(finished) {
						return false;
					}
				}
			}
			else {
				finished = false;
			}
		}
		return true;
	}
}
