package com.libjam.neonrain.vabrant.action.actions;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;
import com.libjam.neonrain.vabrant.action.components.VBActionComponent;

@SuppressWarnings("rawtypes")
public class VBActionController {
	
	private Array<VBActionComponent> _actions;
	
	public VBActionController() {
		_actions = new Array<>(4);
	}
	
	public int getSize() {
		return _actions.size;
	}
	
	public void end() {
		for(int i = 0; i < _actions.size; i++) {
			_actions.get(i).end();
		}
	}
	
	public void add(VBActionComponent action) {
		if(!action.isRunning()) action.start();
		_actions.add(action);
	}
	
	public void reset() {
		if(_actions.size == 0) return;
		end();
		for(int i = _actions.size - 1; i > 0; i--) {
			free(_actions.removeIndex(i));
		}
	}

	public void update(float delta) {
		for(int i = _actions.size - 1; i >= 0; i--) {
			if(!_actions.get(i).update(delta)) {
				free(_actions.removeIndex(i));
			}
		}
	}
	
	private void free(VBActionComponent action) {
		if(action.getClass() == VBGroupAction.class) {
			Pools.freeAll(((VBGroupAction)action).getItems(), false);
		}
		else if(action.getClass() == VBRepeatAction.class) {
			VBActionComponent group = ((VBRepeatAction)action).getItem();
			if(group.getClass() == VBGroupAction.class) {
				Pools.freeAll(((VBGroupAction)group).getItems(), false);
			}
			Pools.free(group);
		}
		Pools.free(action);
	}
	
}
