package rach.dig.android_animationtest;

import android.test.AndroidTestCase;



public class ParticleTest extends AndroidTestCase {

	
	public void bla() {
		int sWidth = 100;
		int sHeight = 50;
		int spawnWidth = 100;
		int spawnHeight = 50;
		ParticleManager pm = new ParticleManager(sWidth,sHeight,spawnWidth,spawnHeight);
		pm.addParticle();
		Particle p = pm.getParticles().get(0);
		assertTrue(p.getPosX() <= spawnWidth);
		
		
	}

}
