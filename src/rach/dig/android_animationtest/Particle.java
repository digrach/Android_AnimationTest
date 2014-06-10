package rach.dig.android_animationtest;

import android.util.Log;

public class Particle {

	private double posY;
	private double posX;
	private double targetX;
	private double targetY;
	private double  size;
	private float[] color;
	private double randomEase;

	private double travelRate;
	private int maxCollisionSize;

	private double spawnFieldWidth;
	private double spawnFieldHeight;

	private boolean hasReachedTarget;

	public Particle(double posx, double posy, double targetx, double targety, double spawnFieldWidth, double spawnFieldHeight) {
		this.posY = posy;
		this.posX = posx;
		this.targetX = targetx;
		this.targetY = targety;
		this.spawnFieldWidth = spawnFieldWidth;
		this.spawnFieldHeight = spawnFieldHeight;
		size = 10;
		color = makeColor();
		randomEase = Math.random() * 0.03;
		travelRate = 0.03;
		maxCollisionSize = (int) (size * 2);
	}

	public void update(double spawnFieldWidth, double spawnFieldHeight) {
	
		if (spawnFieldHeight != this.spawnFieldHeight && hasReachedTarget == false) {
			if (targetX > spawnFieldWidth || targetY > spawnFieldHeight) {
				resetTarget(spawnFieldWidth,spawnFieldHeight);
			}
		} 
		update();
	}

	private void update() {

		double floorx = Math.floor(posX + 0.5);
		double floory = Math.floor(posY + 0.5);

		// If particle has reached the target.
		if (floorx == targetX && floory == targetY){
			hasReachedTarget = true;
		}

		posX += calculateXDistance(targetX) * (travelRate);
		posY += calculateYDistance(targetY) * (travelRate + randomEase);
	}

	private void resetTarget(double spawnFieldWidth, double spawnFieldHeight) {
		double widthChange;
		if (spawnFieldWidth < this.spawnFieldWidth) {
			widthChange = spawnFieldWidth / this.spawnFieldWidth;
			double diff = targetX * widthChange;
			targetX = diff;
		} else {
			widthChange = this.spawnFieldWidth / spawnFieldWidth;
			double diff = targetX * widthChange;
			targetX = diff;
		}
		double heightChange;
		if (spawnFieldHeight < this.spawnFieldHeight) {
			heightChange = spawnFieldHeight / this.spawnFieldHeight;
			double diff = targetY * heightChange;
			targetY = diff;
		} else {
			heightChange = this.spawnFieldHeight / spawnFieldHeight;
			double diff = targetY * heightChange;
			targetY = diff;
		}

		this.spawnFieldWidth = spawnFieldWidth;
		this.spawnFieldHeight = spawnFieldHeight;
	}

	private double  calculateYDistance(double yTarget) {
		return yTarget - posY;
	}

	private double  calculateXDistance(double xTarget) {
		return xTarget - posX;
	}

	private float[] makeColor() {
		float[] color = new float[3];
		color[0]=(float)(Math.random()*360);  
		color[1]=1;  
		color[2]=1; 
		return color;
	}

	// Get / Set

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getTargetX() {
		return targetX;
	}

	public void setTargetX(double targetX) {
		this.targetX = targetX;
	}

	public double getTargetY() {
		return targetY;
	}

	public void setTargetY(double targetY) {
		this.targetY = targetY;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public float[] getColor() {
		return color;
	}

	public void setColor(float[] color) {
		this.color = color;
	}

	public double getRandomEase() {
		return randomEase;
	}

	public void setRandomEase(double randomEase) {
		this.randomEase = randomEase;
	}

	public double getTravelRate() {
		return travelRate;
	}

	public void setTravelRate(double travelRate) {
		this.travelRate = travelRate;
	}

	public int getMaxCollisionSize() {
		return maxCollisionSize;
	}

	public void setMaxCollisionSize(int maxCollisionSize) {
		this.maxCollisionSize = maxCollisionSize;
	}



}

//public class Particle {
//
//	private double posY;
//	private double posX;
//	private double targetX;
//	private double targetY;
//	private double  size;
//	private float[] color;
//	private double randomEase;
//	//private double alpha;
//	//private double fade;
//	//private double maxYCoordinate;
//	//private boolean onWayDown;
//	private double travelRate;
//	//private double originY;
//	//private double initialSize;
//	private int maxCollisionSize;
//
//	//	public Particle(double posx, double posy, double targetx, double targety,
//	//			double maxYCoordinate) {
//	public Particle(double posx, double posy, double targetx, double targety) {
//
//		this.posY = posy;
//		this.posX = posx;
//		this.targetX = targetx;
//		this.targetY = targety;
//		//this.maxYCoordinate = maxYCoordinate;
//
//		size = 10;
//		color = makeColor();
//		//initialSize = size;
//
//		randomEase = Math.random() * 0.03;
//		//alpha = 1;
//		//fade = Math.random() * 0.1;
//		//onWayDown = false;
//		travelRate = 0.03;
//		maxCollisionSize = (int) (size * 2);
//	}
//
//	//	public void update() {
//	//		double floorx = Math.floor(posX + 0.5);
//	//		double floory = Math.floor(posY + 0.5);
//	//
//	//		// If particle has reached the target.
//	//		if (floorx == targetX && floory == targetY) {
//	//			originY = targetY;
//	//			// Reset target
//	//			targetY = maxYCoordinate;
//	//			travelRate = 0.01;
//	//			onWayDown = true;
//	//		}
//	//
//	//		posX += calculateXDistance(targetX) * (travelRate);
//	//		posY += calculateYDistance(targetY) * (travelRate + randomEase);
//	//
//	//		if (onWayDown == true) {
//	//			// Decrement size of descending particle
//	//			double span = targetY - originY;
//	//			double currenty = posY - originY;
//	//			double ratio = (1.0 / (span  * 1.0)) * currenty;
//	//			size = initialSize * (1.0 - ratio);
//	//		}
//	//	}
//
//	public void update() {
//		//		double floorx = Math.floor(posX + 0.5);
//		//		double floory = Math.floor(posY + 0.5);
//
//		//		// If particle has reached the target.
//		//		if (floorx == targetX && floory == targetY) {
//		//			originY = targetY;
//		//			// Reset target
//		//			targetY = maxYCoordinate;
//		//			travelRate = 0.01;
//		//			onWayDown = true;
//		//		}
//
//		posX += calculateXDistance(targetX) * (travelRate);
//		posY += calculateYDistance(targetY) * (travelRate + randomEase);
//
//		//		if (onWayDown == true) {
//		//			// Decrement size of descending particle
//		//			double span = targetY - originY;
//		//			double currenty = posY - originY;
//		//			double ratio = (1.0 / (span  * 1.0)) * currenty;
//		//			size = initialSize * (1.0 - ratio);
//		//		}
//	}
//
//	private double  calculateYDistance(double yTarget) {
//		return yTarget - posY;
//	}
//
//	private double  calculateXDistance(double xTarget) {
//		return xTarget - posX;
//	}
//
//	private float[] makeColor() {
//		float[] color = new float[3];
//		color[0]=(float)(Math.random()*360);  
//		color[1]=1;  
//		color[2]=1; 
//		return color;
//	}
//
//	// Get / Set
//
//	public double getPosY() {
//		return posY;
//	}
//
//	public void setPosY(double posY) {
//		this.posY = posY;
//	}
//
//	public double getPosX() {
//		return posX;
//	}
//
//	public void setPosX(double posX) {
//		this.posX = posX;
//	}
//
//	public double getTargetX() {
//		return targetX;
//	}
//
//	public void setTargetX(double targetX) {
//		this.targetX = targetX;
//	}
//
//	public double getTargetY() {
//		return targetY;
//	}
//
//	public void setTargetY(double targetY) {
//		this.targetY = targetY;
//	}
//
//	public double getSize() {
//		return size;
//	}
//
//	public void setSize(double size) {
//		this.size = size;
//	}
//
//	public float[] getColor() {
//		return color;
//	}
//
//	public void setColor(float[] color) {
//		this.color = color;
//	}
//
//	public double getRandomEase() {
//		return randomEase;
//	}
//
//	public void setRandomEase(double randomEase) {
//		this.randomEase = randomEase;
//	}
//
//	//	public double getAlpha() {
//	//		return alpha;
//	//	}
//	//
//	//	public void setAlpha(double alpha) {
//	//		this.alpha = alpha;
//	//	}
//	//
//	//	public double getFade() {
//	//		return fade;
//	//	}
//	//
//	//	public void setFade(double fade) {
//	//		this.fade = fade;
//	//	}
//	//
//	//	public double getMaxYCoordinate() {
//	//		return maxYCoordinate;
//	//	}
//	//
//	//	public void setMaxYCoordinate(double maxYCoordinate) {
//	//		this.maxYCoordinate = maxYCoordinate;
//	//	}
//	//
//	//	public boolean isOnWayDown() {
//	//		return onWayDown;
//	//	}
//	//
//	//	public void setOnWayDown(boolean onWayDown) {
//	//		this.onWayDown = onWayDown;
//	//	}
//
//	public double getTravelRate() {
//		return travelRate;
//	}
//
//	public void setTravelRate(double travelRate) {
//		this.travelRate = travelRate;
//	}
//
//	//	public double getOriginY() {
//	//		return originY;
//	//	}
//	//
//	//	public void setOriginY(double originY) {
//	//		this.originY = originY;
//	//	}
//	//
//	//	public double getInitialSize() {
//	//		return initialSize;
//	//	}
//	//
//	//	public void setInitialSize(double initialSize) {
//	//		this.initialSize = initialSize;
//	//	}
//
//	public int getMaxCollisionSize() {
//		return maxCollisionSize;
//	}
//
//	public void setMaxCollisionSize(int maxCollisionSize) {
//		this.maxCollisionSize = maxCollisionSize;
//	}
//
//
//
//
//
//
//}
