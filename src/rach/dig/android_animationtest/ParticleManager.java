package rach.dig.android_animationtest;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.util.Log;

public class ParticleManager {

	private List<Particle> particles = new ArrayList<Particle>();
	private List<Particle>[] checkList;

	private int spawnFieldWidth;
	private int spawnFieldHeight;
	private int screenWidth;
	private int screenHeight;

	public ParticleManager(int spawnFieldWidth, int spawnFieldHeight, int screenWidth, int screenHeight) {
		this.spawnFieldWidth = spawnFieldWidth;
		this.spawnFieldHeight = spawnFieldHeight;
		this.screenWidth = screenWidth;
		this.screenHeight = screenHeight;
	}

	public void addParticle() {
		Random r = new Random();
		int randomx = r.nextInt((int) (spawnFieldWidth +1));
		int randomy = r.nextInt((int) (spawnFieldHeight +1));
		int randomTargetx = r.nextInt((int) (spawnFieldWidth +1));
		int randomTargety = r.nextInt((int) (spawnFieldHeight +1));
		Particle p = new Particle(randomx, randomy, randomTargetx, randomTargety, spawnFieldWidth, spawnFieldHeight);
		particles.add(p);
	}

	public List<Particle> updateAllParticles(int currentScreenWidth, int currentScreenHeight) {
		if (currentScreenHeight > screenHeight) {
			this.screenHeight = currentScreenHeight;
		}
		this.screenWidth = currentScreenWidth;
		spawnFieldWidth = currentScreenWidth;
		spawnFieldHeight = currentScreenHeight;

		updateParticles();
		checkForCollisions();
		return particles;
	}

	private void updateParticles() {

		checkList = new List[screenHeight];

		Iterator<Particle> i = particles.iterator();
		while (i.hasNext()) {
			Particle p = i.next();
			p.update(spawnFieldWidth, spawnFieldHeight);

			if (checkList[(int)p.getPosY()] == null) {
				checkList[(int)p.getPosY()] = new ArrayList<Particle>();
			}
			checkList[(int)p.getPosY()].add(p);

		}
	}

	private void checkForCollisions() {
		for (int x = 0; x < particles.size(); x++) {
			if (checkForColision(particles.get(x))) {
				particles.get(x).setSize(particles.get(x).getMaxCollisionSize());
			}
		}
	}

	private boolean checkForColision(Particle p) {
		boolean collision = false;

		int startRowIndex = (int) (p.getPosY() - p.getMaxCollisionSize());
		if (startRowIndex < 0) {
			startRowIndex = 0;
		}
		int endRowIndex = (int) (p.getPosY() + p.getMaxCollisionSize());
		if (endRowIndex > checkList.length - 1) {
			endRowIndex = checkList.length - 1;
		}
		for (int x = startRowIndex; x <= endRowIndex; x ++) {
			List<Particle> l = checkList[x]; 
			if (l != null) { 
				for (int i = 0; i < l.size(); i++ ) { 
					Particle cp = l.get(i);
					if (p != cp) {

						// Distance between particles on the x axis.
						int xDistance = (int) Math.abs(p.getPosX() - cp.getPosX() + cp.getSize());	
						// Distance between particles on the x axis.
						int yDistance = (int) Math.abs(p.getPosY() - cp.getPosY() + cp.getSize());					

						// Combined width of both particles.
						int combinedWidth = (int) (p.getSize() + cp.getSize());
						// Combined height of both particles.
						int combinedHeight = (int) (p.getSize() + cp.getSize());

						// If the distance on BOTH axis is less than or equal to the combined
						// width and height - a collision has occurred.
						if (xDistance<= combinedWidth && yDistance <= combinedHeight) {
							cp.setSize(cp.getMaxCollisionSize());
							collision = true;						
						}
					}
				}
			}
		}
		return collision;
	}

	public int getSpawnFieldWidth() {
		return spawnFieldWidth;
	}

	public int getSpawnFieldHeight() {
		return spawnFieldHeight;
	}

	public void setSpawnFieldWidth(int spawnFieldWidth) {
		this.spawnFieldWidth = spawnFieldWidth;
	}

	public void setSpawnFieldHeight(int spawnFieldHeight) {
		this.spawnFieldHeight = spawnFieldHeight;
	}

	public int getCurrentScreenHeight() {
		return screenHeight;
	}

	public void setCurrentScreenHeight(int currentScreenHeight) {
		this.screenHeight = currentScreenHeight;
	}

	public List<Particle> getParticles() {
		return particles;
	}

	public void setParticles(List<Particle> particles) {
		this.particles = particles;
	}

	public List<Particle>[] getCheckList() {
		return checkList;
	}

	public void setCheckList(List<Particle>[] checkList) {
		this.checkList = checkList;
	}

	public int getScreenWidth() {
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth) {
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight) {
		this.screenHeight = screenHeight;
	}




}

//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Random;
//
//import android.util.Log;
//
//public class ParticleManager {
//
//	private List<Particle> particles = new ArrayList<Particle>();
//	private List<Particle>[] checkList;
//
//	private int spawnFieldWidth;
//	private int spawnFieldHeight;
//	private int currentScreenHeight;
//
//	public ParticleManager(int spawnFieldWidth, int spawnFieldHeight, int currentScreenHeight) {
//		Log.d("------------ > ParticleManager ", " construct");
//		this.spawnFieldWidth = spawnFieldWidth;
//		this.spawnFieldHeight = spawnFieldHeight;
//		this.currentScreenHeight = currentScreenHeight;
//	}
//	
//	public void addParticle() {
//		Random r = new Random();
//		int randomx = r.nextInt((int) (spawnFieldWidth +1));
//		int randomy = r.nextInt((int) (currentScreenHeight +1));
//		int randomTargetx = r.nextInt((int) (spawnFieldWidth +1));
//		int randomTargety = r.nextInt((int) (currentScreenHeight +1));
//		Particle p = new Particle(randomx, randomy, randomTargetx, randomTargety, spawnFieldWidth, spawnFieldHeight);
//		particles.add(p);
//	}
//
//	public List<Particle> updateAllParticles() {
//		updateParticles();
//		checkForCollisions();
//		return particles;
//	}
//
//	private void updateParticles() {
//		checkList = new List[spawnFieldHeight];
//
//		Iterator<Particle> i = particles.iterator();
//		while (i.hasNext()) {
//			Particle p = i.next();
//			p.update(spawnFieldWidth,spawnFieldHeight);
//
//			if (checkList[(int)p.getPosY()] == null) {
//				checkList[(int)p.getPosY()] = new ArrayList<Particle>();
//			}
//			checkList[(int)p.getPosY()].add(p);
//
//		}
//	}
//
//	private void checkForCollisions() {
//		for (int x = 0; x < particles.size(); x++) {
//			if (checkForColision(particles.get(x))) {
//				particles.get(x).setSize(particles.get(x).getMaxCollisionSize());
//			}
//		}
//	}
//
//	private boolean checkForColision(Particle p) {
//		boolean collision = false;
//
//		int startRowIndex = (int) (p.getPosY() - p.getMaxCollisionSize());
//		if (startRowIndex < 0) {
//			startRowIndex = 0;
//		}
//		int endRowIndex = (int) (p.getPosY() + p.getMaxCollisionSize());
//		if (endRowIndex > checkList.length - 1) {
//			endRowIndex = checkList.length - 1;
//		}
//		for (int x = startRowIndex; x <= endRowIndex; x ++) {
//			List<Particle> l = checkList[x]; 
//			if (l != null) { 
//				for (int i = 0; i < l.size(); i++ ) { 
//					Particle cp = l.get(i);
//					if (p != cp) {
//
//						// Distance between particles on the x axis.
//						int xDistance = (int) Math.abs(p.getPosX() - cp.getPosX() + cp.getSize());	
//						// Distance between particles on the x axis.
//						int yDistance = (int) Math.abs(p.getPosY() - cp.getPosY() + cp.getSize());					
//
//						// Combined width of both particles.
//						int combinedWidth = (int) (p.getSize() + cp.getSize());
//						// Combined height of both particles.
//						int combinedHeight = (int) (p.getSize() + cp.getSize());
//
//						// If the distance on BOTH axis is less than or equal to the combined
//						// width and height - a collision has occurred.
//						if (xDistance<= combinedWidth && yDistance <= combinedHeight) {
//							cp.setSize(cp.getMaxCollisionSize());
//							collision = true;						
//						}
//					}
//				}
//			}
//		}
//		return collision;
//	}
//
//	public int getSpawnFieldWidth() {
//		return spawnFieldWidth;
//	}
//
//	public int getSpawnFieldHeight() {
//		return spawnFieldHeight;
//	}
//
//	public void setSpawnFieldWidth(int spawnFieldWidth) {
//		this.spawnFieldWidth = spawnFieldWidth;
//	}
//
//	public void setSpawnFieldHeight(int spawnFieldHeight) {
//		this.spawnFieldHeight = spawnFieldHeight;
//	}
//
//	public int getCurrentScreenHeight() {
//		return currentScreenHeight;
//	}
//
//	public void setCurrentScreenHeight(int currentScreenHeight) {
//		this.currentScreenHeight = currentScreenHeight;
//	}
//
//
//
//
//}
//////////////////////////////////////////////////////
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Random;
//
//import android.util.Log;
//
//public class ParticleManager {
//
//	private List<Particle> particles = new ArrayList<Particle>();
//	private List<Particle>[] checkList;
//
//	private int spawnFieldWidth;
//	private int spawnFieldHeight;
//
//	public ParticleManager(int spawnFieldWidth, int spawnFieldHeight) {
//		Log.d("------------ > ParticleManager ", " construct");
//		this.spawnFieldWidth = spawnFieldWidth;
//		this.spawnFieldHeight = spawnFieldHeight;
//	}
//
//	public void addParticle() {
//		Random r = new Random();
//		int randomx = r.nextInt((int) (spawnFieldWidth +1));
//		int randomy = r.nextInt((int) (spawnFieldHeight +1));
//		int randomTargetx = r.nextInt((int) (spawnFieldWidth +1));
//		int randomTargety = r.nextInt((int) (spawnFieldHeight +1));
//		Particle p = new Particle(randomx, randomy, randomTargetx, randomTargety, spawnFieldWidth, spawnFieldHeight);
//		particles.add(p);
//	}
//
//	public List<Particle> updateAllParticles() {
//		updateParticles();
//		checkForCollisions();
//		return particles;
//	}
//
//	private void updateParticles() {
//		checkList = new List[spawnFieldHeight];
//
//		Iterator<Particle> i = particles.iterator();
//		while (i.hasNext()) {
//			Particle p = i.next();
//			p.update(spawnFieldWidth,spawnFieldHeight);
//
//			if (checkList[(int)p.getPosY()] == null) {
//				checkList[(int)p.getPosY()] = new ArrayList<Particle>();
//			}
//			checkList[(int)p.getPosY()].add(p);
//
//		}
//	}
//
//	private void checkForCollisions() {
//		for (int x = 0; x < particles.size(); x++) {
//			if (checkForColision(particles.get(x))) {
//				particles.get(x).setSize(particles.get(x).getMaxCollisionSize());
//			}
//		}
//	}
//
//	private boolean particleInCurrentBounds(Particle p) {
//		return p.getPosY() < spawnFieldHeight;
//	}
//
//	private boolean checkForColision(Particle p) {
//		boolean collision = false;
//
//		int startRowIndex = (int) (p.getPosY() - p.getMaxCollisionSize());
//		if (startRowIndex < 0) {
//			startRowIndex = 0;
//		}
//		int endRowIndex = (int) (p.getPosY() + p.getMaxCollisionSize());
//		if (endRowIndex > checkList.length - 1) {
//			endRowIndex = checkList.length - 1;
//		}
//		for (int x = startRowIndex; x <= endRowIndex; x ++) {
//			List<Particle> l = checkList[x]; 
//			if (l != null) { 
//				for (int i = 0; i < l.size(); i++ ) { 
//					Particle cp = l.get(i);
//					if (p != cp) {
//
//						// Distance between particles on the x axis.
//						int xDistance = (int) Math.abs(p.getPosX() - cp.getPosX() + cp.getSize());	
//						// Distance between particles on the x axis.
//						int yDistance = (int) Math.abs(p.getPosY() - cp.getPosY() + cp.getSize());					
//
//						// Combined width of both particles.
//						int combinedWidth = (int) (p.getSize() + cp.getSize());
//						// Combined height of both particles.
//						int combinedHeight = (int) (p.getSize() + cp.getSize());
//
//						// If the distance on BOTH axis is less than or equal to the combined
//						// width and height - a collision has occurred.
//						if (xDistance<= combinedWidth && yDistance <= combinedHeight) {
//							cp.setSize(cp.getMaxCollisionSize());
//							collision = true;						
//						}
//					}
//				}
//			}
//		}
//		return collision;
//	}
//
//	public int getSpawnFieldWidth() {
//		return spawnFieldWidth;
//	}
//
//	public int getSpawnFieldHeight() {
//		return spawnFieldHeight;
//	}
//
//	public void setSpawnFieldWidth(int spawnFieldWidth) {
//		this.spawnFieldWidth = spawnFieldWidth;
//	}
//
//	public void setSpawnFieldHeight(int spawnFieldHeight) {
//		this.spawnFieldHeight = spawnFieldHeight;
//	}
//
//
//
//
//}

////////////////////////////////////////////////////////
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Random;
//
//public class ParticleManager {
//
//	private List<Particle> particles = new ArrayList<Particle>();
//	private List<Particle>[] checkList;
//
//	private int spawnFieldWidth;
//	private int spawnFieldHeight;
//
//	public ParticleManager(int spawnFieldWidth, int spawnFieldHeight) {
//		this.spawnFieldWidth = spawnFieldWidth;
//		this.spawnFieldHeight = spawnFieldHeight;
//	}
//
//	public void addParticle() {
//		Random r = new Random();
//		int randomx = r.nextInt((int) (spawnFieldWidth +1));
//		int randomy = r.nextInt((int) (spawnFieldHeight +1));
//		int randomTargetx = r.nextInt((int) (spawnFieldWidth +1));
//		int randomTargety = r.nextInt((int) (spawnFieldHeight +1));
//		//		Particle p = new Particle(randomx, randomy, randomTargetx, randomTargety, spawnFieldHeight);
//		Particle p = new Particle(randomx, randomy, randomTargetx, randomTargety);
//
//		particles.add(p);
//	}
//
//	public List<Particle> updateAllParticles() {
//		updateParticles();
//		checkForCollisions();
//		return particles;
//	}
//
//	private void updateParticles() {
//		checkList = new List[spawnFieldHeight];
//		Iterator<Particle> i = particles.iterator();
//		while (i.hasNext()) {
//			Particle p = i.next();
//			p.update();
//			// Check if the current particle's y does not yet exist as an index of the array
//			// and create a list of Particle at that index, if need be.
//			if (checkList[(int)p.getPosY()] == null) {
//				checkList[(int)p.getPosY()] = new ArrayList<Particle>();
//			}
//			// Add the current particle to the array of lists at the index of y.
//			checkList[(int)p.getPosY()].add(p);
//			//			if (p.isOnWayDown() && p.getPosY() >= p.getTargetY() - 1) {
//			//				i.remove();
//			//				System.out.println("Removed " + particles.size());
//			//			} else {
//			//				p.update();
//			//				// Check if the current particle's y does not yet exist as an index of the array
//			//				// and create a list of Particle at that index, if need be.
//			//				if (checkList[(int)p.getPosY()] == null) {
//			//					checkList[(int)p.getPosY()] = new ArrayList<Particle>();
//			//				}
//			//				// Add the current particle to the array of lists at the index of y.
//			//				checkList[(int)p.getPosY()].add(p);
//			//			}
//		}
//	}
//
//	private void checkForCollisions() {
//		for (int x = 0; x < particles.size(); x++) {
//			if (checkForColision(particles.get(x))) {
//				particles.get(x).setSize(particles.get(x).getMaxCollisionSize());
//			}
//		}
//	}
//
//	private boolean checkForColision(Particle p) {
//		boolean collision = false;
//
//		// Field to scan is the Particle's current y +/- max size.
//		int startRowIndex = (int) (p.getPosY() - p.getMaxCollisionSize());
//		if (startRowIndex < 0) {
//			startRowIndex = 0;
//		}
//		int endRowIndex = (int) (p.getPosY() + p.getMaxCollisionSize());
//		if (endRowIndex > checkList.length - 1) {
//			endRowIndex = checkList.length - 1;
//		}
//		for (int x = startRowIndex; x <= endRowIndex; x ++) {
//			List<Particle> l = checkList[x]; 
//			if (l != null) { 
//				for (int i = 0; i < l.size(); i++ ) { 
//					Particle cp = l.get(i);
//
//					if (p != cp) {
//
//						// Distance between particles on the x axis.
//						int xDistance = (int) Math.abs(p.getPosX() - cp.getPosX() + cp.getSize());	
//						// Distance between particles on the x axis.
//						int yDistance = (int) Math.abs(p.getPosY() - cp.getPosY() + cp.getSize());					
//
//						// Combined width of both particles.
//						int combinedWidth = (int) (p.getSize() + cp.getSize());
//						// Combined height of both particles.
//						int combinedHeight = (int) (p.getSize() + cp.getSize());
//
//						// If the distance on BOTH axis is less than or equal to the combined
//						// width and height - a collision has occurred.
//						if (xDistance<= combinedWidth && yDistance <= combinedHeight) {
//							cp.setSize(cp.getMaxCollisionSize());
//							collision = true;						
//						}
//					}
//				}
//			}
//		}
//		return collision;
//	}
//
//}
