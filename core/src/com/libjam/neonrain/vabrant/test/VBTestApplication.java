package com.libjam.neonrain.vabrant.test;

import com.badlogic.gdx.assets.AssetManager;
import com.libjam.neonrain.vabrant.application.VBApplication;
import com.libjam.neonrain.vabrant.application.VBBackground;

public class VBTestApplication extends VBApplication<VBBackground, AssetManager>{

	@Override
	public void initApplication() {
		setScreen(new VBMultiScreenTest(this));
	}
}
