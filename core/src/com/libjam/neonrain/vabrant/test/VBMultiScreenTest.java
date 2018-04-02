package com.libjam.neonrain.vabrant.test;

import com.badlogic.gdx.Input.Keys;
import com.libjam.neonrain.vabrant.application.VBBackground;
import com.libjam.neonrain.vabrant.application.VBMultiScreen;

public class VBMultiScreenTest extends VBMultiScreen<VBTestApplication, VBBackground>{
	
	public VBMultiScreenTest(VBTestApplication app) {
		super(new VBTestScreenOne(app), app);
//		addScreen(new VBTestScreenTwo(app));
	}
	
	@Override
	public void keyDown(int keycode) {
		if(keycode == Keys.SPACE) {
			System.out.println(getCurrentScreen().getClass());
			System.out.println(isScreen(VBTestScreenOne.class));
		}
		
		if(keycode == Keys.RIGHT) {
//			setScreen(VBTestScreenTwo.class);
		}
		
		if(keycode == Keys.LEFT) {
			setScreen(VBTestScreenOne.class);
		}
	}

}
